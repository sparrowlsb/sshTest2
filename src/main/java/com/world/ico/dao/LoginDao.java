package com.world.ico.dao;

import com.world.ico.entity.UserPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

import java.security.Timestamp;
import java.util.List;


/**
 * Created by lsb on 2018/9/8.
 */

public interface LoginDao extends PagingAndSortingRepository<UserPo, Integer> {


    @Query(value = "select * from USER as t where t.email = :email and t.password = :password", nativeQuery = true)
    List<UserPo> getUserByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Query(value = "select user_id from USER as t where t.email = :email", nativeQuery = true)
    List<Integer> getUserIdByEmail( @Param("email") String email);

    @Modifying
    @Query(value = "INSERT INTO USER (email,password) values (:email,:password) ", nativeQuery = true)
    void insertUserByEmailNameAndPassword(@Param("email") String email,@Param("password") String password);

    @Modifying
    @Query(value = "INSERT INTO USER_INFO (user_id) values (:user_id) ", nativeQuery = true)
    void insertUserInfoByUserId(@Param("user_id") Integer user_id);

    @Modifying
    @Query(value = "INSERT INTO USER_WALLET (user_id,total_assets,rmb,fund_id,fund_money) values (:user_id,:total_assets,:rmb,:fund_id,:fund_money) ", nativeQuery = true)
    void insertUserWallet(@Param("user_id") Integer user_id, @Param("total_assets") Double total_assets,@Param("rmb") Double rmb,@Param("fund_id") Integer fund_id,@Param("fund_money") Double fund_money);


//    @Modifying
//    @Query(value = "INSERT INTO USER_LOG (user_id,log_date,active) values (:user_id,:log_date,:active) ", nativeQuery = true)
//    void insertUserLog(@Param("user_id") Integer user_id, @Param("log_date") Timestamp log_date, @Param("active") String active);

}
