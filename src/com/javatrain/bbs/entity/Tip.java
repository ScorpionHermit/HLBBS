package com.javatrain.bbs.entity;
public class Tip {
    private String title        =  "�������֣�����ָ��";       // ���ӱ���
    private String content      =  "�Ҹտ�ʼѧjava������ָ��"; // ��������
    private String publishTime  =  "2007-1-1 10:30:16";         // ����ʱ��
    private String modifyTime   =  "2007-1-1 10:30:16";         // ����ʱ��
    private int    uid          =  1;                          // �����û���id��������ʾ���������ĸ��û������   
    
    public String getPublishTime() {
        return publishTime;
    }
    
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getUid() {
        return uid;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * ��������޲ι��췽��
     */
    public Tip(){
//        title        =  "re:�������֣�����ָ��";
//        content      =  "�õģ�����һ��ѧ";
//        publishTime  =  "2007-1-1 10:30:20";
//        System.out.println("��������޲ι��췽��");
    }
    
    /**
     * ��������вι��췽��
     * @param pTitle
     * @param pContent
     * @param pTime
     */
    public Tip(String pTitle,String pContent,String pTime) {
        super();        // ��ʾ���ø����޲ι��췽��
        title        =  pTitle;
        content      =  pContent;
        publishTime  =  pTime;
        System.out.println("��������вι��췽��");
    }

    /**
     * �����ǰ���ӵ���Ϣ
     */
    public void getInfo(){
        System.out.println("====������Ϣ====");
        System.out.println("���ӱ��⣺" + title);
        System.out.println("�������ݣ�" + content);
        System.out.println("����ʱ�䣺" + publishTime + "\n");
    }
    
    /**
     * �������tip����Ϣ
     * @param tip
     */
    public void getInfo(Tip tip){
        System.out.println("====����tip����Ϣ====");
        System.out.println("tip�ı��⣺"      + tip.getTitle());
        System.out.println("tip�����ݣ�"      + tip.getContent());
        System.out.println("tip�ķ���ʱ�䣺"  + tip.getPublishTime() + "\n");
    }
}
