package com.btg.accrualfeesv2.services

import com.btg.accrualfeesv2.aws.s3.service.S3BucketService
import com.btg.accrualfeesv2.client.service.MotorRegraClientService
import com.btg.accrualfeesv2.domains.Reversao
import com.btg.accrualfeesv2.domains.ReversaoAnexo
import com.btg.accrualfeesv2.entities.toDomain
import com.btg.accrualfeesv2.entities.toEntity
import com.btg.accrualfeesv2.enums.PermissaoEnum
import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum
import com.btg.accrualfeesv2.forms.ReversaoForm
import com.btg.accrualfeesv2.forms.toDomain
import com.btg.accrualfeesv2.repositories.*
import com.btg.accrualfeesv2.repositories.specification.builder.ReversaoSpecBuilder
import com.btg.accrualfeesv2.repositories.specification.filter.ReversaoSpecFilter
import com.btg.accrualfeesv2.views.ReversaoView
import com.btg.accrualfeesv2.views.toView
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ReversaoService(
    private val reversaoRepository: ReversaoRepository,
    private val reversaoSpecBuilder: ReversaoSpecBuilder,
    private val tipoOperacaoRepository: TipoOperacaoRepository,
    private val motivoRepository: MotivoRepository,
    private val tipoReversaoRepository: TipoReversaoRepository,
    private val filaAprovacaoRepository: FilaAprovacaoRepository,
    private val regraAprovacaoRepository: RegraAprovacaoRepository,
    private val perfilRepository: PerfilRepository,
    private val motorRegraClientService: MotorRegraClientService,
    private val s3BucketService: S3BucketService,
    private val reversaoAnexoRepository: ReversaoAnexoRepository
) {
    fun create(form: ReversaoForm, txLogin: String): ReversaoView {
        val tipoOperacao = tipoOperacaoRepository.findById(form.tipoOperacaoId).get().toDomain()
        val motivo = motivoRepository.findById(form.motivoId).get().toDomain()
        val tipoReversao = tipoReversaoRepository.findById(form.tipoReversaoId).get().toDomain()
        val filaAprovacaoNomeRegra = motorRegraClientService.validarRegra(motivo.nome, form.segmentoCliente)
        val filaAprovacao = filaAprovacaoRepository.findByNomeRegraIgnoreCase(filaAprovacaoNomeRegra).toDomain()
        return reversaoRepository.save(
            form.toDomain(txLogin, tipoOperacao, motivo, tipoReversao, filaAprovacao).toEntity()
        ).toDomain().toView()
    }
    
    fun findAllPageable(
        pageable: Pageable,
        txLogin: String,
        perfilNome: String,
        statuAprovacaoList: List<StatusAprovacaoEnum>?
    ): Page<ReversaoView> {
        val perfil = perfilRepository.findByNomeIgnoreCase(perfilNome)
            ?: throw RuntimeException("Perfil nao encontrado por nome $perfilNome")
        val regraAprovacaoList = regraAprovacaoRepository.findByPerfil(perfil).map { it.toDomain() }
        val filaVisualizarTodosList = regraAprovacaoList
            .filter { it.permissao == PermissaoEnum.VISUALIZAR_TODAS }
            .map { it.filaAprovacao.toEntity() }
            .distinct()
        val filaVisualizarPropriaRversaoCriadaList = regraAprovacaoList
            .filter { it.permissao == PermissaoEnum.VISUALIZAR_SOMENTE_PROPRIA_REVERSAO_CRIADA }
            .map { it.filaAprovacao.toEntity() }
            .distinct()
        val filter =
            ReversaoSpecFilter(statuAprovacaoList, filaVisualizarTodosList.plus(filaVisualizarPropriaRversaoCriadaList))
        val reversaoList = reversaoRepository.findAll(reversaoSpecBuilder.toSpec(filter)).map { it.toDomain() }
        val reversaoFinal = reversaoList.filter { reversao ->
            if (filaVisualizarPropriaRversaoCriadaList.map { it.toDomain() }.contains(reversao.filaAprovacao)) {
                return@filter reversao.txLoginCriador == txLogin
            }
            return@filter true
        }
        
        val start = pageable.offset.toInt()
        val end = (start + pageable.pageSize).coerceAtMost(reversaoFinal.size)
        val page: Page<Reversao> = PageImpl(reversaoFinal.subList(start, end), pageable, reversaoFinal.size.toLong())
        
        return page.map {
            val reversaoAnexo = reversaoAnexoRepository.findByReversao(it.toEntity())?.toDomain()
            var url: String? = null
            if (reversaoAnexo != null) url = s3BucketService.getPresignedUrl(reversaoAnexo.s3FileKey)
            it.toView(url)
        }
    }
    
    fun findById(id: Long): ReversaoView {
        val reversaoEntityOptional = reversaoRepository.findById(id)
        if (reversaoEntityOptional.isEmpty) throw RuntimeException("Reversao nao encontrado pelo id = $id")
        val reversaoAnexo = reversaoAnexoRepository.findByReversao(reversaoEntityOptional.get())?.toDomain()
        var url: String? = null
        if (reversaoAnexo != null) url = s3BucketService.getPresignedUrl(reversaoAnexo.s3FileKey)
        return reversaoEntityOptional.get().toDomain().toView(url)
    }
    
    fun updateById(id: Long, form: ReversaoForm): ReversaoView {
        val reversaoEncontrada = reversaoRepository.findById(id)
        if (reversaoEncontrada.isEmpty) throw RuntimeException("Reversao nao encontrado pelo id = $id")
        val tipoOperacao = tipoOperacaoRepository.findById(form.tipoOperacaoId).get().toDomain()
        val motivo = motivoRepository.findById(form.motivoId).get().toDomain()
        val tipoReversao = tipoReversaoRepository.findById(form.tipoReversaoId).get().toDomain()
        return reversaoRepository.save(
            form.toDomain(
                reversaoEncontrada.get().txLoginCriador,
                tipoOperacao,
                motivo,
                tipoReversao,
                reversaoEncontrada.get().filaAprovacao.toDomain(),
                id
            ).toEntity()
        ).toDomain().toView()
    }
    
    fun deleteById(id: Long) {
        reversaoRepository.deleteById(id)
    }
    
    fun approveById(id: Long, perfilNome: String, txLogin: String): ReversaoView {
        val perfil = perfilRepository.findByNomeIgnoreCase(perfilNome)
            ?: throw RuntimeException("Perfil nao encontrado por nome $perfilNome")
        val reversaoEntityOptional = reversaoRepository.findById(id)
        if (reversaoEntityOptional.isEmpty) throw RuntimeException("Reversao nao encontrado pelo id = $id")
        val reversao = reversaoEntityOptional.get().toDomain()
        if (reversao.txLoginCriador == txLogin) throw RuntimeException("Reversao nao pode ser aprovada pelo mesmo usuario que criou! Usuario criado = $txLogin")
        val regraAprovacaoList = regraAprovacaoRepository.findByPerfil(perfil).map { it.toDomain() }
        val filaAprovarList = regraAprovacaoList
            .filter { it.permissao == PermissaoEnum.APROVAR }
            .map { it.filaAprovacao }
            .distinct()
        if (!filaAprovarList.contains(reversao.filaAprovacao))
            throw RuntimeException("Perfil $perfilNome nao tem permissao para aprovar reversao com id = $id")
        reversao.statusAprovacao = StatusAprovacaoEnum.APROVADO
        return reversaoRepository.save(reversao.toEntity()).toDomain().toView()
    }
    
    fun reproveById(id: Long, perfilNome: String, txLogin: String): ReversaoView {
        val perfil = perfilRepository.findByNomeIgnoreCase(perfilNome)
            ?: throw RuntimeException("Perfil nao encontrado por nome $perfilNome")
        val reversaoEntityOptional = reversaoRepository.findById(id)
        if (reversaoEntityOptional.isEmpty) throw RuntimeException("Reversao nao encontrado pelo id = $id")
        val reversao = reversaoEntityOptional.get().toDomain()
        val regraAprovacaoList = regraAprovacaoRepository.findByPerfil(perfil).map { it.toDomain() }
        val filaAprovarList = regraAprovacaoList
            .filter {
                it.permissao == PermissaoEnum.REPROVAR_TODAS ||
                    (it.permissao == PermissaoEnum.REPROVAR_SOMENTE_PROPRIA_REVERSAO_CRIADA && reversao.txLoginCriador == txLogin)
            }
            .map { it.filaAprovacao }
            .distinct()
        if (!filaAprovarList.contains(reversao.filaAprovacao))
            throw RuntimeException("Perfil $perfilNome nao tem permissao para reprovar reversao com id = $id")
        reversao.statusAprovacao = StatusAprovacaoEnum.REPROVADO
        return reversaoRepository.save(reversao.toEntity()).toDomain().toView()
    }
    
    fun attachFile(id: Long, file: MultipartFile) {
        val reversaoEntityOptional = reversaoRepository.findById(id)
        if (reversaoEntityOptional.isEmpty) throw RuntimeException("Reversao nao encontrado pelo id = $id")
        val reversao = reversaoEntityOptional.get().toDomain()
        val fileKey = s3BucketService.upload(file)
        reversaoAnexoRepository.save(ReversaoAnexo(id = 0, s3FileKey = fileKey, reversao).toEntity())
    }
}
