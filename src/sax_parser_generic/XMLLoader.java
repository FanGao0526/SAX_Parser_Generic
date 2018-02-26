/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax_parser_generic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author GaoFan
 */
public class XMLLoader {
    public static List<List<String>> load(File xmlCourseFile) throws Exception{
       //Set<List<String>> set=new Set<List<String>>();
        List<List<String>> result=new ArrayList<>();
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                
               List<String> node=new ArrayList<>();
               // List<String> node;
                String title;
                String withAtt;
                
            @Override
            public void startDocument() throws SAXException {
                
                System.out.println("Start Reading File....");
            }
            @Override
            public void endDocument() throws SAXException {
                System.out.println("End Reading File....");
            }
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                System.out.println("An Element Begins....");
                title=qName;
                String att=attributes.getValue(0);
                System.out.println(att);
                if(att!=null){
                    withAtt=qName;
                    node.add(att);
                }
            }
            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                //System.out.println(head);
                if(qName==withAtt){
                    result.add(node);
                    withAtt=null;
                    //node.removeAll(node);
                    List<String> ll=new ArrayList<>();
                    node=ll;
                }else if(qName==title){
                    result.add(node);
                    title=null;
                    List<String> ll=new ArrayList<>();
                    node=ll;
                }
                System.out.println("An Element Ends....");
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                  String tmp=new String(ch, start, length);
                  node.add(tmp);
            }
                
            };
            saxParser.parse(xmlCourseFile, handler);
        }catch(Exception e){
            throw e;
        }
        return result;
        
    }
}
