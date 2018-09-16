package com.world.ico.dto;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by youmingwei on 16/4/26.
 */
public class ListUrlUnit implements Serializable {
    private String listUrl;
    private String source;
    private String rootUrl;
    private String listRule;
    private String newsRule;

    private HashSet<String> stringHashSet;

    public String getNewsRule() {
        return newsRule;
    }

    public void setNewsRule(String newsRule) {
        this.newsRule = newsRule;
    }

    public String getListUrl() {
        return listUrl;
    }

    public void setListUrl(String listUrl) {
        this.listUrl = listUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getListRule() {
        return listRule;
    }

    public void setListRule(String listRule) {
        this.listRule = listRule;
    }


    public HashSet<String> getStringHashSet() {
        return stringHashSet;
    }

    public void setStringHashSet(HashSet<String> stringHashSet) {
        this.stringHashSet = stringHashSet;
    }
}

