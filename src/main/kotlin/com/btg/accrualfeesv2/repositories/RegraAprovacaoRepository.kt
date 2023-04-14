package com.btg.accrualfeesv2.repositories

import com.btg.accrualfeesv2.entities.PerfilEntity
import com.btg.accrualfeesv2.entities.RegraAprovacaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegraAprovacaoRepository : JpaRepository<RegraAprovacaoEntity, Long> {
    fun findByPerfil(perfil: PerfilEntity): List<RegraAprovacaoEntity>
}
