package com.javatrain.bbs.dao;

import com.javatrain.bbs.entity.User;

public interface UserDao {
    public static final int FEMALE = 1;   // ����Ů��
    public static final int MALE = 2;     // ��������

    /**
     * �����û���������̳�û�
     * @param uName
     * @return
     */
    public User findUser(String uName);
    
    /**
     * �����û���������̳�û�
     * @param uId
     * @return
     */
    public User findUser(int uId);
    
    /**
     * ������̳�û����������Ӹ���
     * @param user
     * @return
     */
    public int addUser(User user);
    
    /**
     * �޸���̳�û�����Ϣ�������޸ĸ���
     * @param user
     * @return
     * @throws Exception
     */
    public int updateUser(User user);
}
