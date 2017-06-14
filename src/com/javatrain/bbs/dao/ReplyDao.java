package com.javatrain.bbs.dao;

import java.util.List;

import com.javatrain.bbs.entity.Reply;

public interface ReplyDao {
    /**
     * 根据主题id，查找回复的信息
     * @param replyId
     * @return 回复
     */
    public Reply findReply(int replyId);
    
    /**
     * 增加回复信息，返回增加个数
     * @param reply
     * @return
     */
    public int addReply(Reply reply);
    
    /**
     * 根据回复id删除回复，返回删除个数
     * @param replyId
     * @return
     */
    public int deleteReply(int replyId);
    
    /**
     * 修改回复信息，返回修改个数
     * @param reply
     * @return
     */
    public int updateReply(Reply reply);
    
    /**
     * 查询回复，返回某主题第page页回复的列表
     * @param page
     * @param topicId
     * @return
     */
    @SuppressWarnings({  "rawtypes" })
	public List findListReply(int page, int topicId);
    
    /**
     * 根据主题id查询出该主题的回复条数
     * @param topicId 主题id
     * @return 回复条数
     */
    public int findCountReply(int topicId);
}
