package com.javatrain.bbs.entity;
public class Reply extends Tip {
    private int replyId  =  1;  // 唯一标志回复的id
    private int topicId  =  1;  // 引用主题的id，用来表示该回复是哪个主题的

    /**
     * 输出当前回复的信息
     */
    public void getInfo(){
        System.out.println("====回复信息====");
        System.out.println("回复标题：" + this.getTitle());
        System.out.println("回复内容：" + this.getContent());
        System.out.println("发表时间：" + this.getPublishTime() + "\n");
    }

    public int getReplyId() {
        return replyId;
    }
    
    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }
    
    public int getTopicId() {
        return topicId;
    }
    
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
