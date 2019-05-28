package com.luojian.tools.converter;

import com.alibaba.fastjson.JSONObject;
import com.luojian.tools.bo.*;
import com.luojian.tools.bo.postman.Param;
import com.luojian.tools.common.FileUtils;
import com.luojian.tools.common.MDUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Created by luojian on 2019/5/27.
 */
public class Converter2Dot1 implements Converter {

    @Setter
    private String fileName;
    @Getter
    private String mdContent;

    private StringBuilder s;

    private PostmanDoc doc;


    public Converter2Dot1(String fileName){
        this.fileName = fileName;
    }


    /**
     * 执行入口
     * @return
     * @throws Exception
     */
    public String exec() throws Exception {
        this.s = new StringBuilder();

        String fileContent = FileUtils.readFile(this.fileName);
        doc = JSONObject.parseObject(fileContent, PostmanDoc.class);
        this.mdContent = convertDoc(doc);
        return this.mdContent;
    }

    /**
     * 转换doc数据
     * @param doc
     * @return
     */
    private String convertDoc(PostmanDoc doc){

        convertInfo(doc.getInfo());
        convertItemList(doc.getItem(), 1, "");

        return s.toString();
    }

    /**
     * 转换info节点
     * @param info
     */
    private void convertInfo(PostmanInfo info){
        s.append(MDUtils.getHeadPre(1));
        //文档名称
        s.append(info.getName() + " " + new Date().toString());
        s.append(System.lineSeparator());
        //文档描述
        if(info.getDescription() != null){
            s.append(info.getDescription());
            s.append(System.lineSeparator());
        }

    }


    /**
     * 处理item节点
     * @param itemList
     */
    private void convertItemList(List<PostmanItem> itemList, int deep, String numPre){
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
                    convertItemList(item.getItem(), deep+1, numPre + (i +1) + ".");
                }

            }else{
                convertItem(item, deep, numPre + (i+1));
            }
        }
    }

    /**
     * 转换item
     * @param item
     * @param deep
     * @param num
     */
    private void convertItem(PostmanItem item, int deep, String num){
        //接口名称
        s.append(System.lineSeparator());
        s.append(MDUtils.getHeadPre(deep+1));
        s.append(num  + " " + item.getName());

        convertRequest(item.getRequest());

        if (item.getResponse() != null && item.getResponse().size() > 0){
            convertResponse(item.getResponse());
        }
    }

    private void convertRequest(PostmanRequest request){
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
        s.append(MDUtils.block(request.getMethod()));

        //接口 Headers
        if(request.getHeader() != null && request.getHeader().size() > 0){
            s.append(System.lineSeparator());
            s.append(MDUtils.getHeadPre(6) + "Headers");
            s.append(System.lineSeparator());
            s.append(MDUtils.block(formatParam(request.getHeader()), "json"));
        }

        //接口 Auth
        if(request.getAuth() != null && request.getAuth().getBearer() != null
                && request.getAuth().getBearer().size() > 0){
            s.append(System.lineSeparator());
            s.append(MDUtils.getHeadPre(6) + "Auth");
            s.append(System.lineSeparator());
            s.append(MDUtils.block(formatParam(request.getAuth().getBearer()), "json"));
        }

        //接口 Params
        if(request.getUrl().getQuery() != null && request.getUrl().getQuery().size() > 0){
            s.append(System.lineSeparator());
            s.append(MDUtils.getHeadPre(6) + "Params");
            s.append(System.lineSeparator());
            s.append(MDUtils.block(formatParam(request.getUrl().getQuery()), "json"));
        }

        //接口 Body
        if(request.getBody() != null && request.getBody().getFormdata()!=null
                && request.getBody().getFormdata().size() > 0){
            s.append(System.lineSeparator());
            s.append(MDUtils.getHeadPre(6) + "Body");
            s.append(System.lineSeparator());
            s.append(MDUtils.block(formatParam(request.getBody().getFormdata()), "json"));
        }
    }

    private void convertResponse(List<PostmanResponse> responseList){
        s.append(System.lineSeparator());
        s.append(MDUtils.getHeadPre(6) + "Response");

        for(PostmanResponse response : responseList){
            s.append(System.lineSeparator());
            s.append(MDUtils.block(response.getName() + System.lineSeparator() + response.getBody(), "json"));

        }
    }

    private static String formatParam(List<Param> paramList){
        Map<String, String> map = new HashMap<String, String>();
        List<String> list = new ArrayList<String>();
        for (Param param : paramList){
            map.put(param.getKey(), param.getValue());
            if(param.getDescription() != null && !"".equals(param.getDescription())){
                list.add(param.getKey() + "=" + param.getDescription());
            }

        }

        return JSONObject.toJSONString(map, true) + System.lineSeparator() + JSONObject.toJSONString(list);
    }
}
