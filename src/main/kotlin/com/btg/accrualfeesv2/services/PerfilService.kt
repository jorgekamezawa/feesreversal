package com.btg.accrualfeesv2.services

import com.btg.accrualfeesv2.entities.toDomain
import com.btg.accrualfeesv2.entities.toEntity
import com.btg.accrualfeesv2.forms.PerfilForm
import com.btg.accrualfeesv2.forms.toDomain
import com.btg.accrualfeesv2.repositories.MotivoRepository
import com.btg.accrualfeesv2.repositories.PerfilRepository
import com.btg.accrualfeesv2.views.PerfilView
import com.btg.accrualfeesv2.views.toView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PerfilService(
    private val perfilRepository: PerfilRepository,
    private val motivoRepository: MotivoRepository
) {
    fun create(form: PerfilForm): PerfilView {
        val motivoList = form.motivoIdList.map { motivoRepository.findById(it).get().toDomain() }
        return perfilRepository.save(form.toDomain(motivoList).toEntity()).toDomain().toView()
    }
    
    fun findAllPageable(pageable: Pageable): Page<PerfilView> {
        return perfilRepository.findAll(pageable).map { it.toDomain().toView() }
    }
    
    fun findById(id: Long): PerfilView {
        return perfilRepository.findById(id).get().toDomain().toView()
    }
    
    fun updateById(id: Long, form: PerfilForm): PerfilView {
        if (perfilRepository.findById(id).isEmpty) throw RuntimeException("Perfil nao encontrado pelo id = $id")
        val motivoList = form.motivoIdList.map { motivoRepository.findById(it).get().toDomain() }
        return perfilRepository.save(form.toDomain(motivoList, id).toEntity()).toDomain().toView()
    }
    
    fun deleteById(id: Long) {
        perfilRepository.deleteById(id)
    }
}
