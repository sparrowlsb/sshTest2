package com.world.ico.dao;

import com.world.ico.entity.FundTransactionPo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lsb on 2018/10/8.
 */
public interface FundTransactionDao  extends PagingAndSortingRepository<FundTransactionPo, Integer> {
}
