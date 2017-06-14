package com.javatrain.bbs.dao;

import java.util.List;

import com.javatrain.bbs.entity.Reply;

public interface ReplyDao {
    /**
     * ��������id�����һظ�����Ϣ
     * @param replyId
     * @return �ظ�
     */
    public Reply findReply(int replyId);
    
    /**
     * ���ӻظ���Ϣ���������Ӹ���
     * @param reply
     * @return
     */
    public int addReply(Reply reply);
    
    /**
     * ���ݻظ�idɾ���ظ�������ɾ������
     * @param replyId
     * @return
     */
    public int deleteReply(int replyId);
    
    /**
     * �޸Ļظ���Ϣ�������޸ĸ���
     * @param reply
     * @return
     */
    public int updateReply(Reply reply);
    
    /**
     * ��ѯ�ظ�������ĳ�����pageҳ�ظ����б�
     * @param page
     * @param topicId
     * @return
     */
    @SuppressWarnings({  "rawtypes" })
	public List findListReply(int page, int topicId);
    
    /**
     * ��������id��ѯ��������Ļظ�����
     * @param topicId ����id
     * @return �ظ�����
     */
    public int findCountReply(int topicId);
}
