package com.cienet.bean;

/**
 * @ClassName: Message
 * @Description: 消息Bean
 * @author zhujiang
 * @date 2014年7月3日 下午2:17:26
 * 
 */
public class Message {

    /**
     * @Fields type : 消息类型
     */
    private String type;

    /**
     * @Fields content : 消息内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }

}
