package com.world.ico.service;

import com.world.ico.dto.News;

import java.util.List;
import java.util.Map;

/**
 * Created by lsb on 2017/3/18.
 */
public interface NewsService {
    public List<Object[]> findByJson(Integer beginTime, Integer endTime);
    public List<News> setList(List<Object[]> objects, List<News> list);
    public Map<String,Long> reduceList(List<News> list);

}
