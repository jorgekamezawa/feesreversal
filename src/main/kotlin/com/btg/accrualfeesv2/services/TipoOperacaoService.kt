package com.btg.accrualfeesv2.services

import com.btg.accrualfeesv2.entities.toDomain
import com.btg.accrualfeesv2.entities.toEntity
import com.btg.accrualfeesv2.forms.TipoOperacaoForm
import com.btg.accrualfeesv2.forms.toDomain
import com.btg.accrualfeesv2.repositories.TipoOperacaoRepository
import com.btg.accrualfeesv2.repositories.TipoReversaoRepository
import com.btg.accrualfeesv2.views.TipoOperacaoView
import com.btg.accrualfeesv2.views.toView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TipoOperacaoService(
    private val tipoOperacaoRepository: TipoOperacaoRepository,
    private val tipoReversaoRepository: TipoReversaoRepository
) {
    fun create(form: TipoOperacaoForm): TipoOperacaoView {
        val tipoReversao = tipoReversaoRepository.findById(form.tipoReversaoId).get().toDomain()
        return tipoOperacaoRepository.save(form.toDomain(tipoReversao).toEntity()).toDomain().toView()
    }
    
    fun findAllPageable(pageable: Pageable): Page<TipoOperacaoView> {
        return tipoOperacaoRepository.findAll(pageable).map { it.toDomain().toView() }
    }
    
    fun findById(id: Long): TipoOperacaoView {
        return tipoOperacaoRepository.findById(id).get().toDomain().toView()
    }
    
    fun updateById(id: Long, form: TipoOperacaoForm): TipoOperacaoView {
        if (tipoOperacaoRepository.findById(id).isEmpty) throw RuntimeException("Tipo Operacao nao encontrado pelo id = $id")
        val tipoReversao = tipoReversaoRepository.findById(form.tipoReversaoId).get().toDomain()
        return tipoOperacaoRepository.save(form.toDomain(tipoReversao, id).toEntity()).toDomain().toView()
    }
    
    fun deleteById(id: Long) {
        tipoOperacaoRepository.deleteById(id)
    }
}
