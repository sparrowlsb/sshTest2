package com.world.ico.dao;

import com.world.ico.entity.FundManagePo;
import com.world.ico.entity.FundPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lsb on 2018/11/1.
 */
public interface FundManageDao extends PagingAndSortingRepository<FundManagePo, Integer> {



    @Query(value = "SELECT manage_id ,manage_name,manage_sex,manage_age,manage_info,manage_speciality FROM FUND_MANAGE where manage_id=:manageId", nativeQuery = true)
    FundManagePo getFundManageInfo(@Param("manageId") Integer manageId);

}
