package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.ReversaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ReversaoRepository : JpaRepository<ReversaoEntity, Long>, JpaSpecificationExecutor<ReversaoEntity> {

}
