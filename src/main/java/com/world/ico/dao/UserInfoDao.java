package com.world.ico.dao;

import com.world.ico.entity.UserInfoPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lsb on 2018/9/27.
 */
public interface UserInfoDao extends PagingAndSortingRepository<UserInfoPo, Integer> {

    @Modifying
    @Query(value = "UPDATE  USER_INFO t set t.bank_name = :bank_name ,t.bank_id= :bank_id where t.user_id=:user_id", nativeQuery = true)
    void updateBankInfo(@Param("bank_name") String bank_name, @Param("bank_id") String bank_id,@Param("user_id") Integer user_id);

    @Modifying
    @Query(value = "UPDATE  USER_INFO t set t.wechat_name = :wechat_name ,t.wechat_id= :wechat_id where t.user_id=:user_id", nativeQuery = true)
    void updateWechatInfo(@Param("wechat_name") String wechat_name, @Param("wechat_id") String wechat_id,@Param("user_id") Integer user_id);

    @Modifying
    @Query(value = "UPDATE  USER_INFO t set t.allipay_name = :allipay_name ,t.alipay_id= :alipay_id where t.user_id=:user_id", nativeQuery = true)
    void updateAlipayInfo(@Param("allipay_name") String allipay_name, @Param("alipay_id") String alipay_id,@Param("user_id") Integer user_id);

}
