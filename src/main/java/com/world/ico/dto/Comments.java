package com.world.ico.dto;

/**
 * Created by lsb on 2017/3/18.
 */
public class Comments {


    private int id;

    private String keyWord;

    private String[] tag;

    private String tagKeyWord;

    private int level;

    private String  countryOrigin;

    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public String getTagKeyWord() {
        return tagKeyWord;
    }

    public void setTagKeyWord(String tagKeyWord) {
        this.tagKeyWord = tagKeyWord;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
