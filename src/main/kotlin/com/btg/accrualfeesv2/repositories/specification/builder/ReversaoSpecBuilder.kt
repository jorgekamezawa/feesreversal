package com.btg.accrualfeesv2.repositories.specification.builder

import com.btg.accrualfeesv2.entities.FilaAprovacaoEntity
import com.btg.accrualfeesv2.entities.ReversaoEntity
import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum
import com.btg.accrualfeesv2.repositories.specification.filter.ReversaoSpecFilter
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ReversaoSpecBuilder {
    
    fun toSpec(filter: ReversaoSpecFilter): Specification<ReversaoEntity> {
        var spec: Specification<ReversaoEntity> = Specification.where { root, cq, cb -> cb.conjunction() }
        
        if (filter.statusAprovacaoList != null)
            spec = byStatusIn(filter.statusAprovacaoList)
        
        if (filter.filaAprovacaoList != null)
            spec = byFilaAprovacaoIn(filter.filaAprovacaoList)
        
        return spec
    }
    
    private fun byFilaAprovacaoIn(filaAprovacaoList: List<FilaAprovacaoEntity>): Specification<ReversaoEntity> {
        return Specification { root, _, _ ->
            root.get<FilaAprovacaoEntity>("filaAprovacao").`in`(filaAprovacaoList)
        }
    }
    
    private fun byStatusIn(statusList: List<StatusAprovacaoEnum>): Specification<ReversaoEntity> {
        return Specification { root, _, _ ->
            root.get<StatusAprovacaoEnum>("statusAprovacao").`in`(statusList)
        }
    }
    
    private fun byStatusIn2(statusList: String): Specification<ReversaoEntity> {
        return Specification { root, _, cb ->
            cb.equal(root.get<String>("status"), statusList)
        }
    }
    
    private fun byStatusIn3(v1: BigDecimal, v2: BigDecimal): Specification<ReversaoEntity> {
        return Specification { root, _, cb ->
            cb.between(root.get("v1"), v1, v2)
        }
    }
}