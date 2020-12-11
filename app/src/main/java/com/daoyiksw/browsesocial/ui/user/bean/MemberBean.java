package com.daoyiksw.browsesocial.ui.user.bean;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/13
 * @details: '权益列表'
 * @mails: '1966287146@qq.com'
 */
public class MemberBean {

    private Object url; // 图片
    private String name; // 名字
    private String details; // 详情
    private int isOR;// 是否有这个权益

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getIsOR() {
        return isOR;
    }

    public void setIsOR(int isOR) {
        this.isOR = isOR;
    }
}
