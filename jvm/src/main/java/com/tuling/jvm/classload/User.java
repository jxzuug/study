package com.tuling.jvm.classload;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/19
 * @Time: 14:29
 */
public class User {
//    OFFSET  SIZE               TYPE DESCRIPTION                                           VALUE
//      0     4                    (object header)       mark word   标记字段          01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//      4     4                    (object header)       mark word    标记字段          00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//      8     4                    (object header)      klass pointer  类型指针
//      12     4                      int User.id                 对象指针                      0
//      16     4                    java.lang.String User.name         对象指针                     null
//      20     4                    (loss due to the next object alignment)

    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sout() {
        System.out.println("=======自己的加载器加载类调用方法=======");
    }

    //User类需要重写finalize方法
    /*@Override
    protected void finalize() throws Throwable {
        OOMTest.list.add(this);
        System.out.println("关闭资源，userid=" + id + "即将被回收");
    }*/
}
