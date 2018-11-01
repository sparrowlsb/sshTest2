package com.world.ico.dao;

import com.world.ico.entity.UserPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
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
    @Query(value = "INSERT INTO USER_WALLET (user_id,money ,type,count) values (:user_id,:money,:type,:count) ", nativeQuery = true)
    void insertUserWallet(@Param("user_id") Integer user_id, @Param("money") BigDecimal money, @Param("type") String type, @Param("count") BigDecimal count);


//    @Modifying
//    @Query(value = "INSERT INTO USER_LOG (user_id,log_date,active) values (:user_id,:log_date,:active) ", nativeQuery = true)
//    void insertUserLog(@Param("user_id") Integer user_id, @Param("log_date") Timestamp log_date, @Param("active") String active);

}
