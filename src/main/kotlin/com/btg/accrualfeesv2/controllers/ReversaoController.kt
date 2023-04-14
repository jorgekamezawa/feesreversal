package com.btg.accrualfeesv2.controllers

import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum
import com.btg.accrualfeesv2.forms.ReversaoForm
import com.btg.accrualfeesv2.services.ReversaoService
import com.btg.accrualfeesv2.views.ReversaoView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/reversao")
class ReversaoController(
    private val reversaoService: ReversaoService
) {
    
    @PostMapping
    fun create(
        @RequestHeader txLogin: String,
        @RequestBody form: ReversaoForm
    ): ResponseEntity<ReversaoView> {
        return ResponseEntity.ok(reversaoService.create(form, txLogin))
    }
    
    @PostMapping("/{id}/anexo")
    fun attachFile(@PathVariable id: Long, @RequestParam file: MultipartFile): ResponseEntity<ReversaoView> {
        reversaoService.attachFile(id, file)
        return ResponseEntity.ok().build()
    }
    
    @GetMapping
    fun findAllPageable(
        @RequestHeader txLogin: String,
        @RequestHeader perfilNome: String,
        @RequestParam(name = "statusAprovacao") statuAprovacaoList: List<StatusAprovacaoEnum>?,
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): ResponseEntity<Page<ReversaoView>> {
        return ResponseEntity.ok(reversaoService.findAllPageable(pageable, txLogin, perfilNome, statuAprovacaoList))
    }
    
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ReversaoView> {
        return ResponseEntity.ok(reversaoService.findById(id))
    }
    
    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody form: ReversaoForm): ResponseEntity<ReversaoView> {
        return ResponseEntity.ok(reversaoService.updateById(id, form))
    }
    
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        reversaoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
    
    
    @PatchMapping("/{id}/aprovar")
    fun approveById(
        @RequestHeader txLogin: String,
        @RequestHeader perfilNome: String,
        @PathVariable id: Long
    ): ResponseEntity<ReversaoView> {
        return ResponseEntity.ok(reversaoService.approveById(id, perfilNome, txLogin))
    }
    
    @PatchMapping("/{id}/reprovar")
    fun reproveById(
        @RequestHeader txLogin: String,
        @RequestHeader perfilNome: String,
        @PathVariable id: Long
    ): ResponseEntity<ReversaoView> {
        return ResponseEntity.ok(reversaoService.reproveById(id, perfilNome, txLogin))
    }
}