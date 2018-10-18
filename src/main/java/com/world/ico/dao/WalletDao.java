package com.world.ico.dao;

import com.world.ico.entity.UserPo;
import com.world.ico.entity.UserWalletPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lsb on 2018/10/16.
 */
public interface WalletDao extends PagingAndSortingRepository<UserWalletPo, Integer> {

    @Query(value = "select t.count from USER_WALLET t where user_id=:user_id and type=:type ", nativeQuery = true)
    Double totalCount(@Param("user_id") Integer user_id, @Param("type") String type);

    @Modifying
    @Query(value = "update USER_WALLET set count=count-:count where user_id=:user_id and type=:type ", nativeQuery = true)
    void sellCount(@Param("user_id") Integer user_id, @Param("type") String type, @Param("count") Double count);

    @Modifying
    @Query(value = "update USER_WALLET set money=money+:money where user_id=:user_id and type=:type ", nativeQuery = true)
    void updateMoney(@Param("user_id") Integer user_id, @Param("type") String type, @Param("money") Double money);

}
