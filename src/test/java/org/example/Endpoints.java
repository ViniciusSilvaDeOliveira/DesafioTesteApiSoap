package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static io.restassured.RestAssured.given;

public class Endpoints {

    static MessageFactory factory;
    static SOAPMessage message;

//    public Endpoints() throws SOAPException {
//        factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
//        message = factory.createMessage();
//    }

//    public static String body() throws SOAPException {
//        factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
//        message = factory.createMessage();
//        SOAPBody body = message.getSOAPBody(); //obtem o corpo de uma mensagem soap
//        QName bodyName = new QName("http://www.dataaccess.com/webservicesserver/", "NumberToWords");
//        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
//
//        QName name = new QName("ubiNum");
//        SOAPElement symbol = bodyElement.addChildElement(name);
//        symbol.addTextNode("500");
//
//        return message.toString();
//    }

    public static String body() throws SOAPException {
        factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        message = factory.createMessage();
        SOAPBody body = message.getSOAPBody();

        // Namespace e prefixo do elemento principal
        String namespaceURI = "http://www.dataaccess.com/webservicesserver/";
        String namespacePrefix = "web";

        // Nome do elemento principal
        String localName = "NumberToDollars";
        QName bodyName = new QName(namespaceURI, localName, namespacePrefix);
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

        // Elemento dentro do elemento principal
        QName name = new QName(namespaceURI, "dNum", namespacePrefix);
        SOAPElement symbol = bodyElement.addChildElement(name);
        symbol.addTextNode("500");

        try {
            // Converter a mensagem SOAP para uma string
            StringWriter sw = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(message.getSOAPPart()), new StreamResult(sw));
            return sw.toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response enviarRequisicao() throws SOAPException {
        Response response = given()
                    .contentType("text/xml; charset=utf-8")
                    .body(body())
                .when()
                    .post("https://www.dataaccess.com/webservicesserver/NumberConversion.wso")
                .then()
                    .extract().response();

        return response;
    }
}
