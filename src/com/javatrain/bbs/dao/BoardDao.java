package com.javatrain.bbs.dao;

import java.util.*;

import com.javatrain.bbs.entity.Board;

public interface BoardDao {
    
    /**
     * 查找版块map，key是父版块号，value是子级版块对象集合
     * @return 封装了版块信息的Map
     */
    @SuppressWarnings({  "rawtypes" })
	public Map findBoard();
    
    /**
     * 根据版块id查找版块
     * @param boardId
     * @return
     */
    public Board findBoard(int boardId);
}
