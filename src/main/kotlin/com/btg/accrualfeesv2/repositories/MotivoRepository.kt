package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.MotivoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MotivoRepository : JpaRepository<MotivoEntity, Long> {

}
