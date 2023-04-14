package com.btg.accrualfeesv2.controllers

import com.btg.accrualfeesv2.forms.TipoReversaoFrom
import com.btg.accrualfeesv2.services.TipoReversaoService
import com.btg.accrualfeesv2.views.TipoReversaoView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tipo_reversao")
class TipoReversaoController(
    private val tipoReversaoService: TipoReversaoService
) {
    
    @PostMapping
    fun create(@RequestBody form: TipoReversaoFrom): ResponseEntity<TipoReversaoView> {
        return ResponseEntity.ok(tipoReversaoService.create(form))
    }
    
    @GetMapping
    fun findAllPageable(
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): ResponseEntity<Page<TipoReversaoView>> {
        return ResponseEntity.ok(tipoReversaoService.findAllPageable(pageable))
    }
    
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TipoReversaoView> {
        return ResponseEntity.ok(tipoReversaoService.findById(id))
    }
    
    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody form: TipoReversaoFrom): ResponseEntity<TipoReversaoView> {
        return ResponseEntity.ok(tipoReversaoService.updateById(id, form))
    }
    
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        tipoReversaoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}