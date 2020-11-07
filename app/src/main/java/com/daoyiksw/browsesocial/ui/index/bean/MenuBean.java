package com.daoyiksw.browsesocial.ui.index.bean;

import androidx.annotation.NonNull;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/7
 * @details: '菜单bean'
 * @mails: '1966287146@qq.com'
 */
public class MenuBean {

    private Object url;// 图片地址
    private String name; // 菜单名称

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "MenuBean{" +
                "url=" + url +
                ", name='" + name + '\'' +
                '}';
    }
}
