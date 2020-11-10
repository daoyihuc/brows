package com.daoyiksw.browsesocial.ui.user.bean;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class CommentBean
{
    private Object userUrl;
    private String name;
    private String time;
    private String details;
    private Object commentUrl;
    private String commentTitel;

    public Object getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(Object userUrl) {
        this.userUrl = userUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getCommentUrl() {
        return commentUrl;
    }

    public void setCommentUrl(Object commentUrl) {
        this.commentUrl = commentUrl;
    }

    public String getCommentTitel() {
        return commentTitel;
    }

    public void setCommentTitel(String commentTitel) {
        this.commentTitel = commentTitel;
    }
}
