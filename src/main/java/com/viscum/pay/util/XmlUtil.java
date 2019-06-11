package com.viscum.pay.util;

import com.viscum.pay.base.Standard;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.util.*;

/**
 * xml工具类
 *
 * @author fenglei
 */
public class XmlUtil {
    private static HashMap<String, JAXBContext> jaxbContextMap = new HashMap();
    private static Logger logger = Logger.getLogger(XmlUtil.class);

    public static String modelToXml(Object object) throws JAXBException {
        return modelToXml(object, Standard.ENCODING_UTF8);
    }

    public static String modelToXml(Object object, String xmlEncoding) throws JAXBException {
        JAXBContext context = (JAXBContext) jaxbContextMap.get(object.getClass().getName());
        if (context == null) {
            context = JAXBContext.newInstance(object.getClass());
            jaxbContextMap.put(object.getClass().getName(), context);
        }

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.encoding", xmlEncoding);
        marshaller.setProperty("jaxb.formatted.output", true);
        marshaller.setProperty("jaxb.fragment", true);
        StringWriter writer = new StringWriter();
        StringBuffer resultSBBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"" + xmlEncoding + "\" ?>\n");
        marshaller.marshal(object, writer);
        resultSBBuffer.append(writer.getBuffer());
        return resultSBBuffer.toString();
    }

    public static <T> T xmlStreamToModel(InputStream xmlStream, String charSetName, Class<T> clazz, boolean ignoreNameSpace) throws JAXBException, SAXException, ParserConfigurationException, UnsupportedEncodingException {
        JAXBContext context = (JAXBContext) jaxbContextMap.get(clazz.getName());
        if (context == null) {
            context = JAXBContext.newInstance(clazz);
            jaxbContextMap.put(clazz.getName(), context);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object result = null;
        if (ignoreNameSpace) {
            Reader reader = new InputStreamReader(xmlStream, charSetName);
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            sax.setFeature("http://xml.org/sax/features/validation", false);
            sax.setNamespaceAware(false);
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(reader));
            result = unmarshaller.unmarshal(source);
        } else {
            result = unmarshaller.unmarshal(xmlStream);
        }

        return (T) result;
    }

    public static <T> T xmlStringToModel(String xmlString, Class<T> clazz, boolean ignoreNameSpace) throws JAXBException, SAXException, ParserConfigurationException {
        JAXBContext context = (JAXBContext) jaxbContextMap.get(clazz.getName());
        if (context == null) {
            context = JAXBContext.newInstance(clazz);
            jaxbContextMap.put(clazz.getName(), context);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader stringReader = new StringReader(xmlString);
        Object model = null;
        if (ignoreNameSpace) {
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            sax.setFeature("http://xml.org/sax/features/validation", false);
            sax.setNamespaceAware(false);
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(stringReader));
            model = unmarshaller.unmarshal(source);
        } else {
            model = unmarshaller.unmarshal(stringReader);
        }
        return (T) model;
    }

    /**
     * map转换成xml
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static Document parseMapToXml(Map<String, Object> map) {
        //暂时支持一级层
        Document doc = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement("xml");
        doc.setRootElement(root);

        Set<String> keySet = map.keySet();

        Iterator<String> it = keySet.iterator();

        while (it.hasNext()) {
            String key = it.next();
            Element e = DocumentHelper.createElement(key);
            e.setText((String) map.get(key));
            root.add(e);

        }
        logger.info(doc.getRootElement().asXML());
        return doc;
    }
}
