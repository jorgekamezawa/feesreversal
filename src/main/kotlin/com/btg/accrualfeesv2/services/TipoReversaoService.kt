package com.btg.accrualfeesv2.services

import com.btg.accrualfeesv2.entities.toDomain
import com.btg.accrualfeesv2.entities.toEntity
import com.btg.accrualfeesv2.forms.TipoReversaoFrom
import com.btg.accrualfeesv2.forms.toDomain
import com.btg.accrualfeesv2.repositories.TipoReversaoRepository
import com.btg.accrualfeesv2.views.TipoReversaoView
import com.btg.accrualfeesv2.views.toView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TipoReversaoService(
    private val tipoReversaoService: TipoReversaoRepository
) {
    fun create(form: TipoReversaoFrom): TipoReversaoView {
        return tipoReversaoService.save(form.toDomain().toEntity()).toDomain().toView()
    }
    
    fun findAllPageable(pageable: Pageable): Page<TipoReversaoView> {
        return tipoReversaoService.findAll(pageable).map { it.toDomain().toView() }
    }
    
    fun findById(id: Long): TipoReversaoView {
        return tipoReversaoService.findById(id).get().toDomain().toView()
    }
    
    fun updateById(id: Long, form: TipoReversaoFrom): TipoReversaoView {
        if (tipoReversaoService.findById(id).isEmpty) throw RuntimeException("Tipo Reversao nao encontrado pelo id = $id")
        return tipoReversaoService.save(form.toDomain(id).toEntity()).toDomain().toView()
    }
    
    fun deleteById(id: Long) {
        tipoReversaoService.deleteById(id)
    }
}
