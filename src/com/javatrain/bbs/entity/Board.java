package com.javatrain.bbs.entity;

public class Board {
    private int    boardId    =  1;      // ����Ψһ��ʶ���
    private String boardName  =  "Jsp";  // �������
    private int    parentId   =  0;      // �����id

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
     * ��������Ϣ
     */
    public void getBoardInfo() {
        System.out.println("====�����Ϣ====");
        System.out.println("������ƣ�" + boardName + "\n");
    }
}
