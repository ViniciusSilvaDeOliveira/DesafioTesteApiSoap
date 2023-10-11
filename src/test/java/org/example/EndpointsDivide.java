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

            String nomeURI = namespaceUri;
            String nomeMetodo = localNameBanco;
            QName bodyDivide = new QName(nomeURI, nomeMetodo);
            SOAPBodyElement bodyElement = body.addBodyElement(bodyDivide);

            // Dentro do divide cria as variaves para o calculo
            QName intAName = new QName(nomeURI, localPart);
            SOAPElement intAElement = bodyElement.addChildElement(intAName);
            intAElement.addTextNode(textNode);

            QName intBName = new QName(nomeURI, localPart2);
            SOAPElement intBElement = bodyElement.addChildElement(intBName);
            intBElement.addTextNode(textNode2);
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

//        factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
//        message = factory.createMessage();
//        SOAPPart soapPart = message.getSOAPPart();
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        envelope.removeNamespaceDeclaration("ns");
//
//        //corpo da mensagem soap
//        SOAPBody body = envelope.getBody();
//
//        // elemento principal no corpo
//        QName bodyName = new QName(namespaceUri, localNameBanco, "ns");
//        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
//
//        // primeiro elemento dentro do elemento principal
//        QName name = new QName(namespaceUri, "intA", "ns");
//        SOAPElement element = bodyElement.addChildElement(name);
//        element.addTextNode(textNode);
//
//        // segundo elemento dentro do elemento principal
//        QName name2 = new QName(namespaceUri, "intB", "ns");
//        SOAPElement element2 = bodyElement.addChildElement(name2);
//        element2.addTextNode(textNode2);
//
//        try {
//            // Converter a mensagem SOAP para uma String
//            StringWriter sw = new StringWriter();
//            TransformerFactory.newInstance().newTransformer().transform(
//                    new DOMSource(message.getSOAPBody()), new StreamResult(sw));
//            return sw.toString();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public static Response enviarRequisicaoDivide(String namespaceUri, String localNameBanco,
                                                  String localPart, String textNode, String localPart2, String textNode2) throws SOAPException {

        Response response = given()
                    .contentType("text/xml; charset=utf-8")
                    .header("SOAPAction", "http://tempuri.org/Divide")
                    .body(bodyDivide(namespaceUri, localNameBanco, localPart, textNode, localPart2, textNode2))
                .when()
                    .post("http://www.dneonline.com/calculator.asmx")
                .then()
                    .extract().response();

        return response;
    }
}
