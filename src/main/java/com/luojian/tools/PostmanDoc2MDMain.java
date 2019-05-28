package com.luojian.tools;

import com.luojian.tools.common.FileUtils;
import com.luojian.tools.converter.Converter2Dot1;

import java.io.File;

/**
 * Created by luojian on 2019/5/27.
 */
public class PostmanDoc2MDMain {

    public static void main(String[] args) throws Exception {

        if(args.length <= 0){
            System.out.println("需要传一个文件名称参数,如:/usr/test.json");
        }

        String fileName = args[0];

        Converter2Dot1 converter = new Converter2Dot1(fileName);
        converter.exec();
        System.out.println(converter.getMdContent());

        FileUtils.writeFile(converter.getMdContent(), new File(fileName).getPath() + ".md");
    }
}
