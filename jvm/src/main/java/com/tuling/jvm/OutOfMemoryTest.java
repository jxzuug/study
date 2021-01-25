package com.tuling.jvm;

import com.tuling.jvm.classload.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/21
 * @Time: 16:23
 */
public class OutOfMemoryTest {

    public static void main(String[] args) {
        List <User> list = new ArrayList <>();
        while (true) {
            list.add(new User());
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
