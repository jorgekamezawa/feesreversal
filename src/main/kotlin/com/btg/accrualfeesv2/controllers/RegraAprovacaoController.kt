package com.btg.accrualfeesv2.controllers

import com.btg.accrualfeesv2.forms.RegraAprovacaoForm
import com.btg.accrualfeesv2.services.RegraAprovacaoService
import com.btg.accrualfeesv2.views.RegraAprovacaoView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/regra_aprovacao")
class RegraAprovacaoController(
    private val regraAprovacaoService: RegraAprovacaoService
) {
    
    @PostMapping
    fun create(@RequestBody form: RegraAprovacaoForm): ResponseEntity<RegraAprovacaoView> {
        return ResponseEntity.ok(regraAprovacaoService.create(form))
    }
    
    @GetMapping
    fun findAllPageable(
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): ResponseEntity<Page<RegraAprovacaoView>> {
        return ResponseEntity.ok(regraAprovacaoService.findAllPageable(pageable))
    }
    
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<RegraAprovacaoView> {
        return ResponseEntity.ok(regraAprovacaoService.findById(id))
    }
    
    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody form: RegraAprovacaoForm): ResponseEntity<RegraAprovacaoView> {
        return ResponseEntity.ok(regraAprovacaoService.updateById(id, form))
    }
    
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        regraAprovacaoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}