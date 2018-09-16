package com.world.ico.dao;

import com.world.ico.entity.CommentsPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lsb on 2017/3/18.
 */
public interface NewsDAO extends PagingAndSortingRepository<CommentsPo, Integer> {
    @Query(value = "select id,level,riskType,date_format(t.`create_time`,'%Y%m%d') from t_crawler_content as t where UNIX_TIMESTAMP(create_time)>=:beginTime and UNIX_TIMESTAMP(create_time)<:endTime", nativeQuery = true)
    List<Object[]> getByJson(@Param("beginTime") Integer beginTime, @Param("endTime") Integer endTime);

}
