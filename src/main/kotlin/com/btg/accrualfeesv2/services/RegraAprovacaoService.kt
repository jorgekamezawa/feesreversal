package com.btg.accrualfeesv2.services

import com.btg.accrualfeesv2.entities.toDomain
import com.btg.accrualfeesv2.entities.toEntity
import com.btg.accrualfeesv2.forms.RegraAprovacaoForm
import com.btg.accrualfeesv2.forms.toDomain
import com.btg.accrualfeesv2.repositories.FilaAprovacaoRepository
import com.btg.accrualfeesv2.repositories.PerfilRepository
import com.btg.accrualfeesv2.repositories.RegraAprovacaoRepository
import com.btg.accrualfeesv2.views.RegraAprovacaoView
import com.btg.accrualfeesv2.views.toView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RegraAprovacaoService(
    private val regraAprovacaoRepository: RegraAprovacaoRepository,
    private val perfilRepository: PerfilRepository,
    private val filaAprovacaoRepository: FilaAprovacaoRepository
) {
    fun create(form: RegraAprovacaoForm): RegraAprovacaoView {
        val perfil = perfilRepository.findById(form.perfilId).get().toDomain()
        val filaAprovacao = filaAprovacaoRepository.findById(form.filaAprovacaoId).get().toDomain()
        return regraAprovacaoRepository.save(form.toDomain(perfil, filaAprovacao).toEntity()).toDomain().toView()
    }
    
    fun findAllPageable(pageable: Pageable): Page<RegraAprovacaoView> {
        return regraAprovacaoRepository.findAll(pageable).map { it.toDomain().toView() }
    }
    
    fun findById(id: Long): RegraAprovacaoView {
        return regraAprovacaoRepository.findById(id).get().toDomain().toView()
    }
    
    fun updateById(id: Long, form: RegraAprovacaoForm): RegraAprovacaoView {
        if (regraAprovacaoRepository.findById(id).isEmpty) throw RuntimeException("Regra Aprovacao nao encontrado pelo id = $id")
        val perfil = perfilRepository.findById(form.perfilId).get().toDomain()
        val filaAprovacao = filaAprovacaoRepository.findById(form.filaAprovacaoId).get().toDomain()
        return regraAprovacaoRepository.save(form.toDomain(perfil, filaAprovacao, id).toEntity()).toDomain().toView()
    }
    
    fun deleteById(id: Long) {
        regraAprovacaoRepository.deleteById(id)
    }
}
