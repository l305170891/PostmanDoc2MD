package com.luojian.tools;

import com.luojian.tools.conversioner.Conversioner2dot1;

/**
 * Created by luojian on 2019/5/27.
 */
public class PostmanDoc2MD {
    public static void main(String[] args) throws Exception {
//        String fileName = "/Users/luo-mac/Desktop/测试项目.postman_collection.json";
        String fileName = "/Users/luo-mac/Desktop/jczb.postman_collection.json";
        Conversioner2dot1 conversioner = new Conversioner2dot1(fileName);
        conversioner.exec();
        System.out.println(conversioner.getMdContent());
    }
}
