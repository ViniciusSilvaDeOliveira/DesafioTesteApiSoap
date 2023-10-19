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

public class EndpointsContinents {
    static MessageFactory factory;
    static SOAPMessage message;

    public static String body(String namespaceUri, String localName) throws SOAPException {
        factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        message = factory.createMessage();
        SOAPBody body = message.getSOAPBody();

        String namespaceuri = namespaceUri;
        String localNameBanco = localName;

        QName bodyName = new QName(namespaceuri, localNameBanco);
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

        try{
            StringWriter sw = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(message.getSOAPPart()), new StreamResult(sw)
            );
            return sw.toString();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static Response enviarRequisicaoContinents(String namespaceUri, String localName) throws SOAPException {
        Response response = given()
                    .contentType("text/xml; charset=utf-8")
                    .body(body(namespaceUri, localName))
                .when()
                    .post("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso")
                .then()
                    .extract().response();

        return response;
    }
}
