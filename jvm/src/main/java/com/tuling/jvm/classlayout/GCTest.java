package com.tuling.jvm.classlayout;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/22
 * @Time: 14:16
 */
//添加运行JVM参数： -XX:+PrintGCDetails
public class GCTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
        allocation1 = new byte[60000*1024]; // 60M

        allocation2 = new byte[8000*1024];

          allocation3 = new byte[1000*1024];
         allocation4 = new byte[1000*1024];
         allocation5 = new byte[1000*1024];
         allocation6 = new byte[1000*1024];


    }


}
