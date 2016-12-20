package com.ping.terminal;

/**
 * 聊天数据类
 * Created by Mr.sorrow on 2016/10/24.
 */
public class MessageInfo {

    private String name;
    private String time;
    private String content;

    public MessageInfo(){}

    public MessageInfo(String time,String name,String content){
        super();
        this.name = name;
        this.time = time;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
