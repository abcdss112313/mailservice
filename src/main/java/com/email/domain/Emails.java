package com.email.domain;

/**
 * Created by windsorl on 2017/6/21.
 */
public class Emails {
    private String from;
    private String to;
    private String password;
    private String subject;
    private String content;
    private String fromNikeName;
    private Integer hours;

    public String getFromNikeName() {
        return fromNikeName;
    }

    public void setFromNikeName(String fromNikeName) {
        this.fromNikeName = fromNikeName;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
