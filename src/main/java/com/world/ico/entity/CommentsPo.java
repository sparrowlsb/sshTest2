package com.world.ico.entity;

import javax.persistence.*;

/**
 * Created by lsb on 2017/3/18.
 */
@Entity
@Table(name="t_base_product_comments")
public class CommentsPo {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "KEYWORD")

    private String keyWord;

    @Column(name = "TAG")

    private String[] tag;

    @Column(name = "TAG_KEYWORD")

    private String tagKeyWord;

    @Column(name = "LEVEL")

    private int level;

    @Column(name = "COUNTRY_ORIGIN")

    private String  countryOrigin;

    @Column(name = "CREATE_TIME")

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
