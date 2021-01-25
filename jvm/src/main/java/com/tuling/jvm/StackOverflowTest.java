package com.tuling.jvm;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/21
 * @Time: 16:20
 */
public class StackOverflowTest {

    static int count = 0;

    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }
}
