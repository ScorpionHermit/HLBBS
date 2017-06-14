package com.javatrain.bbs.entity;
public class Topic extends Tip {
    private int topicId =  1;  // Ψһ��־�����id
    private int boardId =  1;  // ���ð���id��������ʾ���������ĸ�����

    /**
     * �����ǰ�������Ϣ
     *
     */
    public void getInfo(){
        System.out.println("====������Ϣ====");
        System.out.println("������⣺" + this.getTitle());
        System.out.println("�������ݣ�" + this.getContent());
        System.out.println("����ʱ�䣺" + this.getPublishTime() + "\n");
    }

    public int getTopicId() {
        return topicId;
    }
    
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    
    public int getBoardId() {
        return boardId;
    }
    
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    /**
     * ��������޲ι��췽������ʾ����Tip���޲ι��췽��
     */
    public Topic() {
        super();        // ��ʾ���ø����޲ι��췽��
        topicId =  2;
        boardId =  1;
//        System.out.println("��������޲ι��췽��");
    }
    
    /**
     * ��������вι��췽������ʽ����Tip���вι��췽��
     * @param pTitle
     * @param pContent
     * @param pTime
     * @param pTopicId
     * @param pBoardId
     */
    public Topic(String pTitle, String pContent, String pTime, int pTopicId, int pBoardId) {
        super(pTitle, pContent, pTime);
        topicId = pTopicId;
        boardId = pBoardId;
        System.out.println("��������вι��췽��");
    }
}
