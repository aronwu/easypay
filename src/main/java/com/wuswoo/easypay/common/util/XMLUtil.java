package com.wuswoo.easypay.common.util;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxinjun on 16/11/11.
 */
public class XMLUtil {
    private static Map<Class<?>, Unmarshaller> uMap = new HashMap();
    private static Map<Class<?>, Marshaller> mMap = new HashMap();

    public XMLUtil() {
    }

    public static String convertToXML(Object object) {
        try {
            if(!mMap.containsKey(object.getClass())) {
                JAXBContext e = JAXBContext.newInstance(new Class[]{object.getClass()});
                Marshaller marshaller = e.createMarshaller();
                marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
                marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
                    public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
                        writer.write(ac, i, j);
                    }
                });
                mMap.put(object.getClass(), marshaller);
            }

            StringWriter e1 = new StringWriter();
            ((Marshaller)mMap.get(object.getClass())).marshal(object, e1);
            return e1.getBuffer().toString();
        } catch (JAXBException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
