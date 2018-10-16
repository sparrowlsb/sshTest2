package com.world.ico.dao;

import com.world.ico.entity.UserPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lsb on 2018/10/16.
 */
public interface PasswordDao extends PagingAndSortingRepository<UserPo, Integer> {

    @Modifying
    @Query(value = "UPDATE  USER t set t.password = :password where t.email=:email", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("email") String email);



    @Modifying
    @Query(value = "UPDATE  USER t set t.passwordFinance = :passwordFinance where t.email=:email", nativeQuery = true)
    void updateFinancePassword(@Param("passwordFinance") String password, @Param("email") String email);


}
