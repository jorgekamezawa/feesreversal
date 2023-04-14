package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.ReversaoAnexoEntity
import com.btg.accrualfeesv2.entities.ReversaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReversaoAnexoRepository : JpaRepository<ReversaoAnexoEntity, Long> {
    fun findByReversao(reversao: ReversaoEntity): ReversaoAnexoEntity?
}