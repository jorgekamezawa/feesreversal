package com.btg.accrualfeesv2.controllers

import com.btg.accrualfeesv2.forms.TipoOperacaoForm
import com.btg.accrualfeesv2.services.TipoOperacaoService
import com.btg.accrualfeesv2.views.TipoOperacaoView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tipo_operacao")
class TipoOperacaoController(
    private val tipoOperacaoService: TipoOperacaoService
) {
    
    @PostMapping
    fun create(@RequestBody form: TipoOperacaoForm): ResponseEntity<TipoOperacaoView> {
        return ResponseEntity.ok(tipoOperacaoService.create(form))
    }
    
    @GetMapping
    fun findAllPageable(
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): ResponseEntity<Page<TipoOperacaoView>> {
        return ResponseEntity.ok(tipoOperacaoService.findAllPageable(pageable))
    }
    
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TipoOperacaoView> {
        return ResponseEntity.ok(tipoOperacaoService.findById(id))
    }
    
    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody form: TipoOperacaoForm): ResponseEntity<TipoOperacaoView> {
        return ResponseEntity.ok(tipoOperacaoService.updateById(id, form))
    }
    
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        tipoOperacaoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}