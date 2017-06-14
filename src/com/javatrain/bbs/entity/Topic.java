package com.javatrain.bbs.entity;
public class Topic extends Tip {
    private int topicId =  1;  // 唯一标志主题的id
    private int boardId =  1;  // 引用板块的id，用来表示该帖子是哪个板块的

    /**
     * 输出当前主题的信息
     *
     */
    public void getInfo(){
        System.out.println("====主题信息====");
        System.out.println("主题标题：" + this.getTitle());
        System.out.println("主题内容：" + this.getContent());
        System.out.println("发表时间：" + this.getPublishTime() + "\n");
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
     * 主题类的无参构造方法，显示调用Tip类无参构造方法
     */
    public Topic() {
        super();        // 显示调用父类无参构造方法
        topicId =  2;
        boardId =  1;
//        System.out.println("主题类的无参构造方法");
    }
    
    /**
     * 主题类的有参构造方法，显式调用Tip类有参构造方法
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
        System.out.println("主题类的有参构造方法");
    }
}
