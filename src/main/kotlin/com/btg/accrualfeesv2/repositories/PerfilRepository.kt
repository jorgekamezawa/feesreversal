package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.PerfilEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerfilRepository : JpaRepository<PerfilEntity, Long> {
    fun findByNomeIgnoreCase(nome: String): PerfilEntity?
}
