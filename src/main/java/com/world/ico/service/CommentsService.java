package com.world.ico.service;

import com.world.ico.dto.Comments;

import java.util.List;
import java.util.Map;

/**
 * Created by lsb on 2017/3/5.
 */

public interface CommentsService {


    public List<Object[]> findByJson(String beginTime, String endTime);

    public List<Object[]> findByProductJson(String product, String beginTime, String endTime);

    public List<Object[]> findByCountryJson(String countryOrigin, String beginTime, String endTime);

    public List<Object[]> findByAllJson(String countryOrigin, String product, String beginTime, String endTime);

    public List<Comments> setList(List<Object[]> objects, List<Comments> list);

    public Map<String,Long> reduceList(List<Comments> list,String countryOrigin ,String product);

}