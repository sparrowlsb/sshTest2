package com.world.ico.dao;

import com.world.ico.entity.ManagementFeePo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;

/**
 * Created by lsb on 2018/10/18.
 */
public interface ManagementFeeDao extends PagingAndSortingRepository<ManagementFeePo, Integer> {

    @Query(value = "SELECT management_fee from MANAGEMENT_FEE ORDER BY date limit 1", nativeQuery = true)
    BigDecimal getManagementFee();

}
