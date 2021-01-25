package com.tuling.jvm.classlayout;

import com.tuling.jvm.classload.User;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: Liu Chang
 * @Description: 计算对象大小
 * @Date: 2021/1/22
 * @Time: 11:21
 */
public class JOLSample {

    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        System.out.println();
        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        System.out.println();
        ClassLayout layout2 = ClassLayout.parseInstance(new User());
        System.out.println(layout2.toPrintable());
    }



}
