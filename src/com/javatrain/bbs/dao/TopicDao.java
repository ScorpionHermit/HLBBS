package com.javatrain.bbs.dao;

import java.util.*;

import com.javatrain.bbs.entity.Topic;

public interface TopicDao {
    /**
     * 根据主题id，查找主题的信息
     * @param topicId
     * @return 主题
     */
    public Topic findTopic(int topicId);
    
    /**
     * 查询帖子，返回第page页帖子的列表
     * @param page
     * @param boardId
     * @return 主题列表
     */
    @SuppressWarnings({  "rawtypes" })
	public List findListTopic(int page, int boardId);
    
    /**
     * 增加主题，返回增加个数
     * @param topic
     * @return 增加个数
     */
    public int addTopic(Topic topic);
    
    /**
     * 根据主题id删除主题，返回删除个数
     * @param topicId
     * @return 删除个数
     */
    public int deleteTopic(int topicId);
    
    /**
     * 更新一个主题的信息，返回更新个数
     * @param topic
     * @return 更新个数
     */
    public int updateTopic(Topic topic);
    
    /**
     * 根据版块id取得该版块的主题数
     * @param boardId
     * @return 主题数
     */
    public int findCountTopic(int boardId);
}
