package com.javatrain.bbs.entity;
public class Tip {
    private String title        =  "我是新手，请大家指教";       // 帖子标题
    private String content      =  "我刚开始学java，请大家指教"; // 帖子内容
    private String publishTime  =  "2007-1-1 10:30:16";         // 发表时间
    private String modifyTime   =  "2007-1-1 10:30:16";         // 发表时间
    private int    uid          =  1;                          // 引用用户的id，用来表示该帖子是哪个用户发表的   
    
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
     * 帖子类的无参构造方法
     */
    public Tip(){
//        title        =  "re:我是新手，请大家指教";
//        content      =  "好的，我们一起学";
//        publishTime  =  "2007-1-1 10:30:20";
//        System.out.println("帖子类的无参构造方法");
    }
    
    /**
     * 帖子类的有参构造方法
     * @param pTitle
     * @param pContent
     * @param pTime
     */
    public Tip(String pTitle,String pContent,String pTime) {
        super();        // 显示调用父类无参构造方法
        title        =  pTitle;
        content      =  pContent;
        publishTime  =  pTime;
        System.out.println("帖子类的有参构造方法");
    }

    /**
     * 输出当前帖子的信息
     */
    public void getInfo(){
        System.out.println("====帖子信息====");
        System.out.println("帖子标题：" + title);
        System.out.println("帖子内容：" + content);
        System.out.println("发表时间：" + publishTime + "\n");
    }
    
    /**
     * 输出参数tip的信息
     * @param tip
     */
    public void getInfo(Tip tip){
        System.out.println("====参数tip的信息====");
        System.out.println("tip的标题："      + tip.getTitle());
        System.out.println("tip的内容："      + tip.getContent());
        System.out.println("tip的发表时间："  + tip.getPublishTime() + "\n");
    }
}
