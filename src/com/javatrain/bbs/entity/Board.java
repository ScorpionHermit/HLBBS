package com.javatrain.bbs.entity;

public class Board {
    private int    boardId    =  1;      // 用来唯一标识版块
    private String boardName  =  "Jsp";  // 版块名称
    private int    parentId   =  0;      // 主版块id

    public int getBoardId() {
        return boardId;
    }
    
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    
    public String getBoardName() {
        return boardName;
    }
    
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
    
    public int getParentId() {
        return parentId;
    }
    
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    
    /**
     * 输出版块信息
     */
    public void getBoardInfo() {
        System.out.println("====板块信息====");
        System.out.println("板块名称：" + boardName + "\n");
    }
}
