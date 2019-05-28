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
    public static String readFile(String fileName) throws Exception {
        return readFile(new File(fileName));
    }

    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String readFile(File file) throws Exception{
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String s = null;
        while((s = br.readLine())!=null){
            result.append(System.lineSeparator()+s);
        }
        br.close();

        return result.toString();
    }


    public static boolean writeFile(String content, String fileName) throws Exception {
        FileOutputStream o = null;
        boolean result=false;
        try {
            o = new FileOutputStream(fileName,true);
            o.write(content.getBytes());
            result=true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (o != null) {
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


}
