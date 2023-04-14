package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.TipoOperacaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoOperacaoRepository : JpaRepository<TipoOperacaoEntity, Long> {

}
