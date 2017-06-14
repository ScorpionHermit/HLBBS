package com.javatrain.bbs.entity;
public class User {
    private int    uId     =  1;       // 用来唯一标识用户
    private String uName   =  "accp";  // 用户名
    private String uPass   =  "accp";  // 用户密码
    private int    gender  =  2;      // 性别,1是女，2是男
    private String  head   =  "";      // 头像，地址形式
    private String  regTime = "";      // 注册时间    
    
    /**
     * @return head
     */
    public String getHead() {
        return head;
    }
    
    /**
     * @param head 要设置的 head
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
     * @param regTime 要设置的 regTime
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
     * 输出当前用户的信息
     */
    public void getUserInfo() {
        System.out.println("====用户信息====");
        System.out.println("用户名："   + uName);
        System.out.println("用户密码：" + uPass);
        char sex = gender==1 ? '女':'男';     // 判断性别
        System.out.println("性别："     + sex + "\n");
    }
}
