package com.javatrain.bbs.entity;
public class Reply extends Tip {
    private int replyId  =  1;  // Ψһ��־�ظ���id
    private int topicId  =  1;  // ���������id��������ʾ�ûظ����ĸ������

    /**
     * �����ǰ�ظ�����Ϣ
     */
    public void getInfo(){
        System.out.println("====�ظ���Ϣ====");
        System.out.println("�ظ����⣺" + this.getTitle());
        System.out.println("�ظ����ݣ�" + this.getContent());
        System.out.println("����ʱ�䣺" + this.getPublishTime() + "\n");
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
