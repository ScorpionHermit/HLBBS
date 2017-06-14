package com.javatrain.bbs.dao;

import java.util.*;

import com.javatrain.bbs.entity.Board;

public interface BoardDao {
    
    /**
     * ���Ұ��map��key�Ǹ����ţ�value���Ӽ������󼯺�
     * @return ��װ�˰����Ϣ��Map
     */
    @SuppressWarnings({  "rawtypes" })
	public Map findBoard();
    
    /**
     * ���ݰ��id���Ұ��
     * @param boardId
     * @return
     */
    public Board findBoard(int boardId);
}
