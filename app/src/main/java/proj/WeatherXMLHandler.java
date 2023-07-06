package proj;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WeatherXMLHandler {
    private DocumentBuilder builder;
    private Document doc;

    public WeatherXMLHandler() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("WeatherXMLHandler() ParserConfigurationException: " + e.getMessage());
        }
    }

    public void parse(InputStream is) {
        try {
            doc = builder.parse(is);
        } catch (SAXException | IOException e) {
            System.err.println("parse() exception:" + e.getMessage());
        }
    }

    public String getCurrentWeather() {
        NodeList itemList = doc.getElementsByTagName("item");
        // LocalDateTime은 Comparable하므로 날짜가 빠른 순으로 정렬됨
        TreeMap<LocalDateTime, String> skyMap = new TreeMap<>();
        TreeMap<LocalDateTime, String> ptyMap = new TreeMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMddHHmm");

        for (int i = 0; i < itemList.getLength(); i++) {
            Element item = (Element) itemList.item(i);
            NodeList elementList = item.getChildNodes();
            String category = null;
            String fcstValue = null;
            String fcstDate = null;
            String fcstTime = null;

            for (int j = 0; j < elementList.getLength(); j++) {
                Element child = (Element) elementList.item(j);
                String tagName = child.getTagName();
                if (tagName.equals("category")) {
                    category = child.getTextContent();
                } else if (tagName.equals("fcstValue")) {
                    fcstValue = child.getTextContent();
                } else if (tagName.equals("fcstDate")) {
                    fcstDate = child.getTextContent();
                } else if (tagName.equals("fcstTime")) {
                    fcstTime = child.getTextContent();
                }
            }

            if (category.equals("SKY")) {
                skyMap.put(LocalDateTime.parse(fcstDate + fcstTime, formatter), fcstValue);
            } else if (category.equals("PTY")) {
                ptyMap.put(LocalDateTime.parse(fcstDate + fcstTime, formatter), fcstValue);
            }
        }

        switch (ptyMap.firstEntry().getValue()) {
            case "1":
                return "비";
            case "2":
                return "비/눈";
            case "3":
                return "눈";
            case "5":
                return "빗방울";
            case "6":
                return "빗방울/눈날림";
            case "7":
                return "눈날림";
        }

        switch (skyMap.firstEntry().getValue()) {
            case "1":
                return "맑음";
            case "3":
                return "구름많음";
            case "4":
                return "흐림";
        }

        return null;
    }
}
