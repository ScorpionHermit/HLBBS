package com.javatrain.bbs.dao;

import java.util.*;

import com.javatrain.bbs.entity.Topic;

public interface TopicDao {
    /**
     * ��������id�������������Ϣ
     * @param topicId
     * @return ����
     */
    public Topic findTopic(int topicId);
    
    /**
     * ��ѯ���ӣ����ص�pageҳ���ӵ��б�
     * @param page
     * @param boardId
     * @return �����б�
     */
    @SuppressWarnings({  "rawtypes" })
	public List findListTopic(int page, int boardId);
    
    /**
     * �������⣬�������Ӹ���
     * @param topic
     * @return ���Ӹ���
     */
    public int addTopic(Topic topic);
    
    /**
     * ��������idɾ�����⣬����ɾ������
     * @param topicId
     * @return ɾ������
     */
    public int deleteTopic(int topicId);
    
    /**
     * ����һ���������Ϣ�����ظ��¸���
     * @param topic
     * @return ���¸���
     */
    public int updateTopic(Topic topic);
    
    /**
     * ���ݰ��idȡ�øð���������
     * @param boardId
     * @return ������
     */
    public int findCountTopic(int boardId);
}
