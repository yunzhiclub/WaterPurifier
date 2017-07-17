package com.mengyunzhi.waterPurifierApi.util;

import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.util.*;
/**
 * Created by chuhang on 2017/7/17.
 */
public class CommonUtil {
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                //增加CDATA标记
                boolean cdata = true;
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static String UnifiedorderParamsToXML(WechatCustomerController.UnifiedorderParams pi) {
        xstream.alias("xml", pi.getClass());
        return xstream.toXML(pi);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(String xml) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        return map;
    }
}
