package com.luojian.tools.common;

import java.io.*;

/**
 * Created by luojian on 2019/5/27.
 */
public class FileUtils {

    /**
     * 根据文件名称获取文件内容
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getContent(String fileName) throws Exception {
        return txt2String(new File(fileName));
    }

    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file) throws Exception{
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String s = null;
        while((s = br.readLine())!=null){
            result.append(System.lineSeparator()+s);
        }
        br.close();

        return result.toString();
    }


}
