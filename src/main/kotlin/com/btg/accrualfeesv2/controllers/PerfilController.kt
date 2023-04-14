package com.btg.accrualfeesv2.controllers

import com.btg.accrualfeesv2.forms.PerfilForm
import com.btg.accrualfeesv2.services.PerfilService
import com.btg.accrualfeesv2.views.PerfilView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/perfil")
class PerfilController(
    private val perfilService: PerfilService
) {
    
    @PostMapping
    fun create(@RequestBody form: PerfilForm): ResponseEntity<PerfilView> {
        return ResponseEntity.ok(perfilService.create(form))
    }
    
    @GetMapping
    fun findAllPageable(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Page<PerfilView>> {
        return ResponseEntity.ok(perfilService.findAllPageable(pageable))
    }
    
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<PerfilView> {
        return ResponseEntity.ok(perfilService.findById(id))
    }
    
    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody form: PerfilForm): ResponseEntity<PerfilView> {
        return ResponseEntity.ok(perfilService.updateById(id, form))
    }
    
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        perfilService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}