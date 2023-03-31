package com.aigt.code.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.ByteArrayOutputStream;

/**
 * @description 转化xml
 * @author shibowen Email:shibowen@co-mall.com
 * @company 北京科码先锋有限公司@版权所有
 * @date 2018/2/2
 */
public class XmlUtil {

    /**
     * 转换生成xml
     * @param
     * @author shibowen Email:shibowen@co-mall.com
     * @date 2018/2/2
     */
    public static String TransformXML(ReplyTextMsg msg) {
        try {
            JAXBContext context = JAXBContext.newInstance(ReplyTextMsg.class);//获取上下文对象
            Marshaller marshaller = context.createMarshaller();//根据上下文获取Marshaller对象
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

            marshaller.marshal(msg,System.out);

            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            marshaller.marshal(msg,bas);
            String xml = new String(bas.toByteArray());
            return xml;
        } catch (PropertyException e) {
            return "";
        }catch (JAXBException e){
            return "";
        }
    }

    public static void main(String[] args){
        ReplyTextMsg msg = new ReplyTextMsg("12342","3523",(int)System.currentTimeMillis()/1000,"text");
        msg.setContent("wwwaaasssdd");
        TransformXML(msg);
    }
}
