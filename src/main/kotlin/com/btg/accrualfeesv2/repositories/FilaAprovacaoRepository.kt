package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.FilaAprovacaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilaAprovacaoRepository : JpaRepository<FilaAprovacaoEntity, Long> {
    fun findByNomeRegraIgnoreCase(nomeRegra: String): FilaAprovacaoEntity
}
