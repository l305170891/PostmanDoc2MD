package com.luojian.tools.common;

/**
 * Created by luojian on 2019/5/27.
 */
public class MDUtils {

    /**
     * 获取 h1 到 h6
     * @param head
     * @return
     */
    public static String getHeadPre(int head){
        switch (head){
            case 1:
                return "# ";
            case 2:
                return "## ";
            case 3:
                return "### ";
            case 4:
                return "#### ";
            case 5:
                return "##### ";
            case 6:
                return "###### ";
            default:
                return "###### ";
        }
    }

    public static String block(String content){
        return block(content, "");
    }

    public static String block(String content, String type){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("```");
        stringBuilder.append(type);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(content);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("```");
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
