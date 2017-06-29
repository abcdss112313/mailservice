package com.email.domain;

/**
 * Created by windsorl on 2017/6/21.
 */
public class Emails {
    /**
     *发件人
     **/
    private String from;
    /**
     *收件人
     **/
    private String to;
    /**
     * password
     */
    private String password;
    /**
     * 主题
     */
    private String subject;
    /**
     * 邮箱内容
     */
    private String content;
    /**
     * 发送人昵称
     */
    private String fromNikeName;

    private String mailType;

    private Integer hours;
    private Integer overtimehour;
    private Integer askforleave;
    private Integer restHour;

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public Integer getRestHour() {
        return restHour;
    }
    public void setRestHour(Integer restHour) {
        this.restHour = restHour;
    }

    public Integer getOvertimehour() {
        return overtimehour;
    }

    public void setOvertimehour(Integer overtimehour) {
        this.overtimehour = overtimehour;
    }

    public Integer getAskforleave() {
        return askforleave;
    }

    public void setAskforleave(Integer askforleave) {
        this.askforleave = askforleave;
    }

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
