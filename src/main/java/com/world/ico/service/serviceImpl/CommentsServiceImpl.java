package com.world.ico.service.serviceImpl;

import com.world.ico.service.CommentsService;
import com.world.ico.dao.CommentsDAO;
import com.world.ico.dto.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsb on 2017/3/18.
 */
@Service("CommentsService")
@Transactional
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDAO commentsDAO;

    @Override
    public List<Object[]> findByJson(String beginTime, String endTime) {
        return commentsDAO.getByJson(beginTime, endTime);
    }

    @Override
    public List<Object[]> findByProductJson(String product, String beginTime, String endTime) {
        return commentsDAO.getProductByJson(product, beginTime, endTime);
    }

    @Override
    public List<Object[]> findByCountryJson(String countryOrigin, String beginTime, String endTime) {
        return commentsDAO.getCountryByJson(countryOrigin, beginTime, endTime);
    }

    @Override
    public List<Object[]> findByAllJson(String countryOrigin, String product, String beginTime, String endTime) {
        return commentsDAO.getAllByJson(countryOrigin, product, beginTime, endTime);
    }

    @Override
    public List<Comments> setList(List<Object[]> objects, List<Comments> list) {
        for (Object[] objs : objects) {
            Comments record = new Comments();
            int id = Integer.parseInt(objs[0].toString());
            int level = Integer.parseInt(objs[5].toString());
            record.setId(id);
            if (objs[1] != null)
                record.setKeyWord(objs[1].toString());
            if (objs[2] != null)
                record.setTagKeyWord(objs[2].toString());
            if (objs[3] != null)
                record.setTag(objs[3].toString().split("#"));
            if (objs[4] != null)
                record.setCreateTime(objs[4].toString());
            record.setLevel(level);
            if (objs[6] != null)
                record.setCountryOrigin(objs[6].toString());
            list.add(record);
        }
        return list;
    }

    @Override
    public Map<String, Long> reduceList(List<Comments> list, String countryOrigin, String product) {
        Map<String, Long> map = new HashMap<>();
        Map<String, Long> endMap = new HashMap<>();
        if (countryOrigin != null && product != null) {
            for (Comments c : list) {
                map.put(product + "-" + countryOrigin + "-" + c.getCreateTime(), 0L);
            }
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                Long attitude = 0L;
                Long quality = 0L;
                Long packing = 0L;
                Long price = 0L;
                Long taste = 0L;
                Long appearance = 0L;
                Long effect = 0L;
                for (Comments c : list) {
                    if (entry.getKey().equals(c.getKeyWord() + "-" + c.getCountryOrigin() + "-" + c.getCreateTime())) {
                        if (c.getTag() != null) {
                            String[] tag = c.getTag();
                            if (tag.length >= 1) {
                                for (String s : tag) {
                                    if (s.contains("态度")) {
                                        attitude++;
                                    }
                                    if (s.contains("质量")) {
                                        quality++;
                                    }
                                    if (s.contains("包装")) {
                                        packing++;
                                    }
                                    if (s.contains("价格")) {
                                        price++;
                                    }
                                    if (s.contains("味道")) {
                                        taste++;
                                    }
                                    if (s.contains("外观")) {
                                        appearance++;
                                    }
                                    if (s.contains("副作用")) {
                                        effect++;
                                    }
                                }
                            }
                        }
                    }
                }
                endMap.put(entry.getKey() + " attitude", attitude);
                endMap.put(entry.getKey() + " quality", quality);
                endMap.put(entry.getKey() + " packing", packing);
                endMap.put(entry.getKey() + " price", price);
                endMap.put(entry.getKey() + " taste", taste);
                endMap.put(entry.getKey() + " appearance", appearance);
                endMap.put(entry.getKey() + " effect", effect);
            }

            return endMap;
        }
        if (countryOrigin != null && product == null) {
            for (Comments c : list) {
                map.put(countryOrigin + "-" + c.getCreateTime(), 0L);
            }
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                Long attitude = 0L;
                Long quality = 0L;
                Long packing = 0L;
                Long price = 0L;
                Long taste = 0L;
                Long appearance = 0L;
                Long effect = 0L;
                for (Comments c : list) {
                    if (entry.getKey().equals(c.getCountryOrigin() + "-" + c.getCreateTime())) {
                        if (c.getTag() != null) {
                            String[] tag = c.getTag();
                            if (tag.length >= 1) {
                                for (String s : tag) {
                                    if (s.contains("态度")) {
                                        attitude++;
                                    }
                                    if (s.contains("质量")) {
                                        quality++;
                                    }
                                    if (s.contains("包装")) {
                                        packing++;
                                    }
                                    if (s.contains("价格")) {
                                        price++;
                                    }
                                    if (s.contains("味道")) {
                                        taste++;
                                    }
                                    if (s.contains("外观")) {
                                        appearance++;
                                    }
                                    if (s.contains("副作用")) {
                                        effect++;
                                    }
                                }
                            }
                        }
                    }
                }
                endMap.put(entry.getKey() + " attitude", attitude);
                endMap.put(entry.getKey() + " quality", quality);
                endMap.put(entry.getKey() + " packing", packing);
                endMap.put(entry.getKey() + " price", price);
                endMap.put(entry.getKey() + " taste", taste);
                endMap.put(entry.getKey() + " appearance", appearance);
                endMap.put(entry.getKey() + " effect", effect);
            }

            return endMap;
        }
        if (countryOrigin == null && product != null) {
            for (Comments c : list) {
                map.put(product + "-" + c.getCreateTime(), 0L);
            }
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                Long attitude = 0L;
                Long quality = 0L;
                Long packing = 0L;
                Long price = 0L;
                Long taste = 0L;
                Long appearance = 0L;
                Long effect = 0L;
                for (Comments c : list) {
                    if (entry.getKey().equals(c.getKeyWord() + "-" + c.getCreateTime())) {
                        if (c.getTag() != null) {
                            String[] tag = c.getTag();
                            if (tag.length >= 1) {
                                for (String s : tag) {
                                    if (s.contains("态度")) {
                                        attitude++;
                                    }
                                    if (s.contains("质量")) {
                                        quality++;
                                    }
                                    if (s.contains("包装")) {
                                        packing++;
                                    }
                                    if (s.contains("价格")) {
                                        price++;
                                    }
                                    if (s.contains("味道")) {
                                        taste++;
                                    }
                                    if (s.contains("外观")) {
                                        appearance++;
                                    }
                                    if (s.contains("副作用")) {
                                        effect++;
                                    }
                                }
                            }
                        }
                    }
                }
                endMap.put(entry.getKey() + " attitude", attitude);
                endMap.put(entry.getKey() + " quality", quality);
                endMap.put(entry.getKey() + " packing", packing);
                endMap.put(entry.getKey() + " price", price);
                endMap.put(entry.getKey() + " taste", taste);
                endMap.put(entry.getKey() + " appearance", appearance);
                endMap.put(entry.getKey() + " effect", effect);
            }

            return endMap;
        }
        if (countryOrigin == null && product == null) {
            for (Comments c : list) {
                map.put("-"+c.getCreateTime(), 0L);
            }
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                Long attitude = 0L;
                Long quality = 0L;
                Long packing = 0L;
                Long price = 0L;
                Long taste = 0L;
                Long appearance = 0L;
                Long effect = 0L;
                for (Comments c : list) {
                    if (entry.getKey().equals("-"+c.getCreateTime())) {
                        if (c.getTag() != null) {
                            String[] tag = c.getTag();
                            if (tag.length >= 1) {
                                for (String s : tag) {
                                    if (s.contains("态度")) {
                                        attitude++;
                                    }
                                    if (s.contains("质量")) {
                                        quality++;
                                    }
                                    if (s.contains("包装")) {
                                        packing++;
                                    }
                                    if (s.contains("价格")) {
                                        price++;
                                    }
                                    if (s.contains("味道")) {
                                        taste++;
                                    }
                                    if (s.contains("外观")) {
                                        appearance++;
                                    }
                                    if (s.contains("副作用")) {
                                        effect++;
                                    }
                                }
                            }
                        }
                    }
                }
                endMap.put(entry.getKey() + " attitude", attitude);
                endMap.put(entry.getKey() + " quality", quality);
                endMap.put(entry.getKey() + " packing", packing);
                endMap.put(entry.getKey() + " price", price);
                endMap.put(entry.getKey() + " taste", taste);
                endMap.put(entry.getKey() + " appearance", appearance);
                endMap.put(entry.getKey() + " effect", effect);
            }

            return endMap;
        }
        return null;
    }

}
