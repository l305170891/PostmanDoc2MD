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
        convertItemList(md, doc.getItem(), 1, "");

        return md.toString();
    }

    /**
     * 转换info节点
     * @param s
     * @param info
     */
    private void convertInfo(StringBuilder s, PostmanInfo info){
        s.append(MDUtils.getHeadPre(1));
        //文档名称
        s.append(info.getName());
        s.append(System.lineSeparator());
        //文档描述
        if(info.getDescription() != null){
            s.append(info.getDescription());
            s.append(System.lineSeparator());
        }

    }


    /**
     * 处理item节点
     * @param s
     * @param itemList
     */
    private void convertItemList(StringBuilder s, List<PostmanItem> itemList, int deep, String numPre){
        for (int i=0; i<itemList.size(); i++ ) {

            PostmanItem item = itemList.get(i);

            if(item.isFolder()){
                //文件夹名称
                s.append(System.lineSeparator());
                s.append(MDUtils.getHeadPre(deep+1));
                s.append(numPre + (i+1) + " " + item.getName());

                //文件夹描述
                if(item.getDescription() != null){
                    s.append(System.lineSeparator());
                    s.append(item.getDescription());
                }


                if(item.getItem() != null && item.getItem().size() > 0){
                    //请求
                    convertItemList(s, item.getItem(), deep+1, numPre + (i +1) + ".");
                }

            }else{
                convertItem(s, item, deep, numPre + (i+1));
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
    private void convertItem(StringBuilder s, PostmanItem item, int deep, String num){
        //接口名称
        s.append(System.lineSeparator());
        s.append(MDUtils.getHeadPre(deep+1));
        s.append(num  + " " + item.getName());

        convertRequest(s, item.getRequest());
    }

    private void convertRequest(StringBuilder s, PostmanRequest request){
        //接口描述
        if(request.getDescription() != null){
            s.append(System.lineSeparator());
            s.append(request.getDescription());
        }

        //接口url
        s.append(System.lineSeparator());
        s.append(MDUtils.block(request.getUrl().getRaw()));
        //接口 method
        s.append(System.lineSeparator());
        s.append(MDUtils.getHeadPre(6) + "Method");
        s.append(System.lineSeparator());
        s.append(MDUtils.block(JSONObject.toJSONString(request.getHeader(), true), "json"));

        //接口 Headers
        if(request.getHeader() != null && request.getHeader().size() > 0){
            s.append(System.lineSeparator());
            s.append(MDUtils.getHeadPre(6) + "Headers");
            s.append(System.lineSeparator());
            s.append(MDUtils.block(JSONObject.toJSONString(request.getHeader(), true), "json"));
        }

        //接口 Params
        if(request.getUrl().getQuery() != null && request.getUrl().getQuery().size() > 0){
            s.append(System.lineSeparator());
            s.append(MDUtils.getHeadPre(6) + "Params");
            s.append(System.lineSeparator());
            s.append(MDUtils.block(JSONObject.toJSONString(request.getUrl().getQuery(), true), "json"));
        }

        //接口 Body
        if(request.getBody() != null && request.getBody().getFormdata()!=null
                && request.getBody().getFormdata().size() > 0){
            s.append(System.lineSeparator());
            s.append(MDUtils.getHeadPre(6) + "Body");
            s.append(System.lineSeparator());
            s.append(MDUtils.block(JSONObject.toJSONString(request.getBody().getFormdata(), true), "json"));
        }
    }
}
