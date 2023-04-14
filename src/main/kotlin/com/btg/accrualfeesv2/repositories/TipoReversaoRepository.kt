package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.TipoReversaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoReversaoRepository : JpaRepository<TipoReversaoEntity, Long> {

}
