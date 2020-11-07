package com.daoyiksw.browsesocial.ui.index.bean;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-03-07
 * @params home banner  数据结构
 **/
public class image_banner_home {

    private Object res_id;
    private String name;
    private Object url;
    private String share_title;//标题
    private String share_content;//详情

    public Object getRes_id() {
        return res_id;
    }

    public void setRes_id(Object res_id) {
        this.res_id = res_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getUrl() {
        return url ==null?"": url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }
}
