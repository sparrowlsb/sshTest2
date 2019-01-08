package com.world.ico.dao;

import com.world.ico.entity.UserPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lsb on 2018/9/27.
 */
public interface UserInfoDao extends PagingAndSortingRepository<UserPo, Integer> {


    @Query(value = "select user_id,password,email,name,usdt_address,person_code,status from USER as t where t.email = :email", nativeQuery = true)
    UserPo getUserInfoByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "update USER set name=:name,person_code=:personCode,id_card_on=:idCardOn,id_card_under=:idCardUnder,usdt_address=:usdtAddress,status=1,update_date=now() where email = :email", nativeQuery = true)
    void updateUserInfoByEmail(@Param("email") String email,@Param("name") String name,@Param("personCode") String personCode,@Param("idCardOn") String idCardOn,@Param("idCardUnder") String idCardUnder,@Param("usdtAddress") String usdtAddress);


}
