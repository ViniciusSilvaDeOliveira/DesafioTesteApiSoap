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

    public static String body(String namespaceUri, String namespacePrefixBanco, String localNameBanco, String localPart, String textNode) throws SOAPException {
        factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        message = factory.createMessage();
        SOAPBody body = message.getSOAPBody();

        // Namespace e prefixo do elemento principal
        String namespaceURI = namespaceUri;
        String namespacePrefix = namespacePrefixBanco;

        // Nome do elemento principal
        String localName = localNameBanco;
        QName bodyName = new QName(namespaceURI, localName, namespacePrefix);
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

        // Elemento dentro do elemento principal
        QName name = new QName(namespaceURI, localPart, namespacePrefix);
        SOAPElement symbol = bodyElement.addChildElement(name);
        symbol.addTextNode(textNode);

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

    public static Response enviarRequisicao(String namespaceUri, String namespacePrefixBanco, String localNameBanco, String localPart, String textNode) throws SOAPException {
        Response response = given()
                    .contentType("text/xml; charset=utf-8")
                    .body(body(namespaceUri, namespacePrefixBanco, localNameBanco, localPart, textNode))
                .when()
                    .post("https://www.dataaccess.com/webservicesserver/NumberConversion.wso")
                .then()
                    .extract().response();

        return response;
    }
}
