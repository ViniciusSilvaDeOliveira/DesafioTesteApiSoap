package org.example;

import io.restassured.response.Response;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

public class EndpointsDivide {
    static MessageFactory factory;
    static SOAPMessage message;

    public static String bodyDivide(String namespaceUri, String localNameBanco,
                                    String localPart, String textNode, String localPart2, String textNode2) throws SOAPException {
        factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        message = factory.createMessage();
        SOAPBody body = message.getSOAPBody();

        //Namespace e prefixo do elemento principal
        String namespaceURI = namespaceUri;
        //String namespacePrefix = namespacePrefixBanco;

        // Nome do elemento principal
        String localName = localNameBanco;
        QName bodyName = new QName(namespaceURI, localName);
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

        // Elemento dentro do elemento principal
        QName name = new QName(namespaceURI, localPart);
        SOAPElement symbol = bodyElement.addChildElement(name);
        symbol.addTextNode(textNode);

        // Elemento dentro do elemento principal
        QName name2 = new QName(namespaceURI, localPart2);
        SOAPElement symbol2 = bodyElement.addChildElement(name2);
        symbol2.addTextNode(textNode2);

        try {
            // Converter a mensagem SOAP para uma String
            StringWriter sw = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(message.getSOAPBody()), new StreamResult(sw));
            return sw.toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response enviarRequisicaoDivide(String namespaceUri, String localNameBanco,
                                                  String localPart, String textNode, String localPart2, String textNode2) throws SOAPException {

        Response response = given()
                    .contentType("text/xml; charset=utf-8")
                    .body(bodyDivide(namespaceUri, localNameBanco, localPart, textNode, localPart2, textNode2))
                .when()
                    .post("http://www.dneonline.com/calculator.asmx")
                .then()
                    .extract().response();

        return response;
    }
}
