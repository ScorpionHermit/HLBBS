package com.javatrain.bbs.entity;
public class User {
    private int    uId     =  1;       // ����Ψһ��ʶ�û�
    private String uName   =  "accp";  // �û���
    private String uPass   =  "accp";  // �û�����
    private int    gender  =  2;      // �Ա�,1��Ů��2����
    private String  head   =  "";      // ͷ�񣬵�ַ��ʽ
    private String  regTime = "";      // ע��ʱ��    
    
    /**
     * @return head
     */
    public String getHead() {
        return head;
    }
    
    /**
     * @param head Ҫ���õ� head
     */
    public void setHead(String head) {
        this.head = head;
    }
    
    /**
     * @return regTime
     */
    public String getRegTime() {
        return regTime;
    }
    
    /**
     * @param regTime Ҫ���õ� regTime
     */
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }    
    
    public int getUId() {
        return uId;
    }
    
    public void setUId(int id) {
        uId = id;
    }
    
    public String getUName() {
        return uName;
    }
    
    public void setUName(String name) {
        uName = name;
    }
    
    public String getUPass() {
        return uPass;
    }
    
    public void setUPass(String pass) {
        uPass = pass;
    }

    /**
     * �����ǰ�û�����Ϣ
     */
    public void getUserInfo() {
        System.out.println("====�û���Ϣ====");
        System.out.println("�û�����"   + uName);
        System.out.println("�û����룺" + uPass);
        char sex = gender==1 ? 'Ů':'��';     // �ж��Ա�
        System.out.println("�Ա�"     + sex + "\n");
    }
}
