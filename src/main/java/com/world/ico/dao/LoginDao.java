package com.world.ico.dao;

import com.world.ico.entity.UserPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * Created by lsb on 2018/9/8.
 */

public interface LoginDao extends PagingAndSortingRepository<UserPo, Integer> {

    @Query(value = "select * from USER as t where t.user_name = :user and t.user_password = :password", nativeQuery = true)
    List<Object[]> getUserByUserAndPassword(@Param("user") String user,@Param("password") String password);

    @Query(value = "select * from USER as t where t.email = :email and t.user_password = :password", nativeQuery = true)
    List<Object[]> getUserByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Query(value = "select * from USER as t where t.email = :email", nativeQuery = true)
    List<Object[]> getUserByEmail( @Param("email") String email);

    @Query(value = "select * from USER as t where t.user_name = :user", nativeQuery = true)
    List<Object[]> getUserByUser( @Param("user") String user);


    @Modifying
    @Query(value = "INSERT INTO USER (email,user_name,user_password) values (:email,:user,:password) ", nativeQuery = true)
    void insertUserByEmailUserAndPassword(@Param("email") String email, @Param("user") String user,@Param("password") String password);

}
