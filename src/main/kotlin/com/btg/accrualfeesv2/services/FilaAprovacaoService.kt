package com.btg.accrualfeesv2.services

import com.btg.accrualfeesv2.entities.toDomain
import com.btg.accrualfeesv2.entities.toEntity
import com.btg.accrualfeesv2.forms.FilaAprovacaoForm
import com.btg.accrualfeesv2.forms.toDomain
import com.btg.accrualfeesv2.repositories.FilaAprovacaoRepository
import com.btg.accrualfeesv2.views.FilaAprovacaoView
import com.btg.accrualfeesv2.views.toView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FilaAprovacaoService(
    private val filaAprovacaoRepository: FilaAprovacaoRepository
) {
    fun create(form: FilaAprovacaoForm): FilaAprovacaoView {
        return filaAprovacaoRepository.save(form.toDomain().toEntity()).toDomain().toView()
    }
    
    fun findAllPageable(pageable: Pageable): Page<FilaAprovacaoView> {
        return filaAprovacaoRepository.findAll(pageable).map { it.toDomain().toView() }
    }
    
    fun findById(id: Long): FilaAprovacaoView {
        return filaAprovacaoRepository.findById(id).get().toDomain().toView()
    }
    
    fun updateById(id: Long, form: FilaAprovacaoForm): FilaAprovacaoView {
        if (filaAprovacaoRepository.findById(id).isEmpty) throw RuntimeException("Fila Aprovacao nao encontrado pelo id = $id")
        return filaAprovacaoRepository.save(form.toDomain(id).toEntity()).toDomain().toView()
    }
    
    fun deleteById(id: Long) {
        filaAprovacaoRepository.deleteById(id)
    }
}
