package com.btg.accrualfeesv2.controllers

import com.btg.accrualfeesv2.forms.FilaAprovacaoForm
import com.btg.accrualfeesv2.services.FilaAprovacaoService
import com.btg.accrualfeesv2.views.FilaAprovacaoView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fila_aprovacao")
class FilaAprovacaoController(
    private val filaAprovacaoService: FilaAprovacaoService
) {
    
    @PostMapping
    fun create(@RequestBody form: FilaAprovacaoForm): ResponseEntity<FilaAprovacaoView> {
        return ResponseEntity.ok(filaAprovacaoService.create(form))
    }
    
    @GetMapping
    fun findAllPageable(
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): ResponseEntity<Page<FilaAprovacaoView>> {
        return ResponseEntity.ok(filaAprovacaoService.findAllPageable(pageable))
    }
    
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<FilaAprovacaoView> {
        return ResponseEntity.ok(filaAprovacaoService.findById(id))
    }
    
    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody form: FilaAprovacaoForm): ResponseEntity<FilaAprovacaoView> {
        return ResponseEntity.ok(filaAprovacaoService.updateById(id, form))
    }
    
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        filaAprovacaoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}