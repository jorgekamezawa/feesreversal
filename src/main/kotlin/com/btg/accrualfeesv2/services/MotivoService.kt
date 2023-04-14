package com.btg.accrualfeesv2.services

import com.btg.accrualfeesv2.entities.toDomain
import com.btg.accrualfeesv2.entities.toEntity
import com.btg.accrualfeesv2.forms.MotivoForm
import com.btg.accrualfeesv2.forms.toDomain
import com.btg.accrualfeesv2.repositories.MotivoRepository
import com.btg.accrualfeesv2.views.MotivoView
import com.btg.accrualfeesv2.views.toView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MotivoService(
    private val motivoRepository: MotivoRepository
) {
    fun create(form: MotivoForm): MotivoView {
        return motivoRepository.save(form.toDomain().toEntity()).toDomain().toView()
    }
    
    fun findAllPageable(pageable: Pageable): Page<MotivoView> {
        return motivoRepository.findAll(pageable).map { it.toDomain().toView() }
    }
    
    fun findById(id: Long): MotivoView {
        return motivoRepository.findById(id).get().toDomain().toView()
    }
    
    fun updateById(id: Long, form: MotivoForm): MotivoView {
        if (motivoRepository.findById(id).isEmpty) throw RuntimeException("Motivo nao encontrado pelo id = $id")
        return motivoRepository.save(form.toDomain(id).toEntity()).toDomain().toView()
    }
    
    fun deleteById(id: Long) {
        motivoRepository.deleteById(id)
    }
}
