package com.tuling.jvm.classlayout;

import com.tuling.jvm.classload.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/22
 * @Time: 14:44
 */
public class OOMTest {

    public static List <Object> list = new ArrayList <>();

    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }



}
