package com.world.ico.dao;

import com.world.ico.entity.AdminPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lsb on 2017/3/5.
 */
public interface AdminDAO  extends PagingAndSortingRepository<AdminPo, Integer> {

    @Query(value = "select count(0) from admin ", nativeQuery = true)
    Long getTodayCount();

}