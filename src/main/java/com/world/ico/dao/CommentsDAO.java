package com.world.ico.dao;

import com.world.ico.entity.CommentsPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lsb on 2017/3/18.
 */
public interface CommentsDAO extends PagingAndSortingRepository<CommentsPo, Integer> {

    @Query(value = "select ID,KEYWORD,TAG_KEYWORD,TAG,date_format(t.`CREATE_TIME`,'%Y%m%d'),LEVEL,COUNTRY_ORIGIN from t_base_product_comments as t where KEYWORD=:keyword and COUNTRY_ORIGIN like :countryOrigin and UNIX_TIMESTAMP(CREATE_TIME)>=:beginTime and UNIX_TIMESTAMP(CREATE_TIME)<:endTime", nativeQuery = true)
    List<Object[]> getAllByJson(@Param("countryOrigin") String countryOrigin, @Param("keyword") String keyword,@Param("beginTime") String beginTime, @Param("endTime") String endTime);


    @Query(value = "select ID,KEYWORD,TAG_KEYWORD,TAG,date_format(t.`CREATE_TIME`,'%Y%m%d'),LEVEL,COUNTRY_ORIGIN from t_base_product_comments as t where KEYWORD=:keyword  and UNIX_TIMESTAMP(CREATE_TIME)>=:beginTime and UNIX_TIMESTAMP(CREATE_TIME)<:endTime", nativeQuery = true)
    List<Object[]> getProductByJson( @Param("keyword") String keyword,@Param("beginTime") String beginTime, @Param("endTime") String endTime);


    @Query(value = "select ID,KEYWORD,TAG_KEYWORD,TAG,date_format(t.`CREATE_TIME`,'%Y%m%d'),LEVEL,COUNTRY_ORIGIN from t_base_product_comments as t where COUNTRY_ORIGIN like :countryOrigin and UNIX_TIMESTAMP(CREATE_TIME)>=:beginTime and UNIX_TIMESTAMP(CREATE_TIME)<:endTime", nativeQuery = true)
    List<Object[]> getCountryByJson(@Param("countryOrigin") String countryOrigin, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    @Query(value = "select ID,KEYWORD,TAG_KEYWORD,TAG,date_format(t.`CREATE_TIME`,'%Y%m%d'),LEVEL,COUNTRY_ORIGIN from t_base_product_comments as t where UNIX_TIMESTAMP(CREATE_TIME)>=:beginTime and UNIX_TIMESTAMP(CREATE_TIME)<:endTime", nativeQuery = true)
    List<Object[]> getByJson(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
