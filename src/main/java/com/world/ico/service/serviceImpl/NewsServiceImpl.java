package com.world.ico.service.serviceImpl;

import com.world.ico.dto.News;
import com.world.ico.dao.NewsDAO;
import com.world.ico.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsb on 2017/3/18.
 */
@Service("NewsService")
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDAO newsDAO;

    @Override
    public List<Object[]> findByJson(Integer beginTime, Integer endTime) {
        return newsDAO.getByJson(beginTime,endTime);
    }

    @Override
    public List<News> setList(List<Object[]> objects, List<News> list) {
        for (Object[] objs : objects) {
            News record = new News();
            int id = Integer.parseInt(objs[0].toString());
            record.setId(id);
            if (objs[1] != null)
                record.setLevel(Integer.parseInt(objs[1].toString()));
            if (objs[2] != null)
                record.setRiskType(objs[2].toString().split("#"));
            if (objs[3] != null)
                record.setCreateTime(objs[3].toString());

            list.add(record);
        }
        return list;
    }

    @Override
    public Map<String, Long> reduceList(List<News> list) {
        Map<String, Long> map = new HashMap<>();
        Map<String, Long> endMap = new HashMap<>();
        for (News c : list) {
            map.put("-" + c.getCreateTime(), 0L);
        }
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            Long safe = 0L;
            Long quality = 0L;
            Long other = 0L;
            Long level1 = 0L;
            Long level2 = 0L;
            Long level3 = 0L;
            for (News c : list) {
                if (entry.getKey().equals("-" + c.getCreateTime())) {
                    if (c.getRiskType() != null) {
                        String[] tag = c.getRiskType();
                        if (tag.length >= 1) {
                            for (String s : tag) {
                                if (s.contains("安全")) {
                                    safe++;
                                }
                                if (s.contains("质量")) {
                                    quality++;
                                }
                                if (s.contains("其他")) {
                                    other++;
                                }
                            }
                        }
                    }
                    if (c.getLevel()==0) {
                        level1++;
                    }
                    if(c.getLevel()==2){
                        level2++;
                    }
                    if(c.getLevel()==3){
                        level3++;
                    }
                }
            }
            endMap.put(entry.getKey() + " safe", safe);
            endMap.put(entry.getKey() + " quality", quality);
            endMap.put(entry.getKey() + " other", other);
            endMap.put(entry.getKey() + " level1", level1);
            endMap.put(entry.getKey() + " level2", level2);
            endMap.put(entry.getKey() + " level3", level3);
        }

        return endMap;
    }
}
