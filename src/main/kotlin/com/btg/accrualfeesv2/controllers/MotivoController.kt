package com.btg.accrualfeesv2.controllers

import com.btg.accrualfeesv2.forms.MotivoForm
import com.btg.accrualfeesv2.services.MotivoService
import com.btg.accrualfeesv2.views.MotivoView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/motivo")
class MotivoController(
    private val motivoService: MotivoService
) {
    
    @PostMapping
    fun create(@RequestBody form: MotivoForm): ResponseEntity<MotivoView> {
        return ResponseEntity.ok(motivoService.create(form))
    }
    
    @GetMapping
    fun findAllPageable(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Page<MotivoView>> {
        return ResponseEntity.ok(motivoService.findAllPageable(pageable))
    }
    
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<MotivoView> {
        return ResponseEntity.ok(motivoService.findById(id))
    }
    
    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody form: MotivoForm): ResponseEntity<MotivoView> {
        return ResponseEntity.ok(motivoService.updateById(id, form))
    }
    
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        motivoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}