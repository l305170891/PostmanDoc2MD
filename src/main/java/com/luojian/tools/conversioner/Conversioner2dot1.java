package com.luojian.tools.conversioner;

import com.alibaba.fastjson.JSONObject;
import com.luojian.tools.bo.PostmanDoc;
import com.luojian.tools.bo.PostmanInfo;
import com.luojian.tools.bo.PostmanItem;
import com.luojian.tools.bo.PostmanRequest;
import com.luojian.tools.common.FileUtils;
import com.luojian.tools.common.MDUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by luojian on 2019/5/27.
 */
public class Conversioner2dot1 implements Conversioner {

    @Setter
    private String fileName;
    @Getter
    private String mdContent;


    public Conversioner2dot1(String fileName){
        this.fileName = fileName;
    }


    /**
     * 执行入口
     * @return
     * @throws Exception
     */
    public String exec() throws Exception {
        String fileContent = FileUtils.getContent(this.fileName);
        PostmanDoc doc = JSONObject.parseObject(fileContent, PostmanDoc.class);
        this.mdContent = convertDoc(doc);
        return this.mdContent;
    }

    /**
     * 转换doc数据
     * @param doc
     * @return
     */
    private String convertDoc(PostmanDoc doc){
        StringBuilder md = new StringBuilder();

        convertInfo(md, doc.getInfo());
        convertItemList(md, doc.getItem(), 1);

        return md.toString();
    }

    /**
     * 转换info节点
     * @param s
     * @param info
     */
    private void convertInfo(StringBuilder s, PostmanInfo info){
        s.append(MDUtils.getHeadPre(1));
        s.append(info.getName());
        s.append(System.lineSeparator());
        s.append(info.getDescription());
        s.append(System.lineSeparator());
    }


    /**
     * 处理item节点
     * @param s
     * @param itemList
     */
    private void convertItemList(StringBuilder s, List<PostmanItem> itemList, int deep){
        for (int i=0; i<itemList.size(); i++ ) {

            PostmanItem item = itemList.get(i);

            if(item.isFolder()){
                s.append(System.lineSeparator());
                s.append(MDUtils.getHeadPre(deep+1));
                s.append((i+1) + "." + item.getName());

                s.append(System.lineSeparator());
                s.append(item.getDescription());

                if(item.getItem() != null && item.getItem().size() > 0){
                    convertItemList(s, item.getItem(), deep+1);
                }

            }else{
                convertItem(s, item, deep, i+1);
            }
        }
    }

    /**
     * 转换item
     * @param s
     * @param item
     * @param deep
     * @param num
     */
    private void convertItem(StringBuilder s, PostmanItem item, int deep, int num){
        s.append(System.lineSeparator());
        s.append(MDUtils.getHeadPre(deep+1));
        s.append((num+1) + "." + item.getName());
        s.append(System.lineSeparator());
        s.append(MDUtils.getContent(item.getDescription()));
    }

    private void convertRequest(StringBuilder s, PostmanRequest request){

    }
}
