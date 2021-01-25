package com.tuling.jvm.classload;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/21
 * @Time: 16:18
 */
public class Math {

    public static final int initData = 666;
    public static User user = new User();

    public int compute() {  //一个方法对应一块栈帧内存区域
        Integer z = new Integer(1300);
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        int x = c * z;
        return x;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}
