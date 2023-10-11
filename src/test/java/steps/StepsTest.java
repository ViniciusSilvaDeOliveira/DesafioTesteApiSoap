package steps;

import conexaoBancoDeDados.DatabaseConnectionsTest;
import conexaoBancoDeDados.DatabaseConnectionsTestDivide;
import conexaoBancoDeDados.DatabaseConnectionsTestMultiply;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.example.Endpoints;
import org.example.EndpointsDivide;
import org.example.EndpointsMultiply;
import org.junit.Assert;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.containsString;


public class StepsTest {
    Response response;
    List<String> namespaceUri;
    List<String> namespacePrefix;
    List<String> localName;
    List<String> localPart;
    List<String> textNode;
    List<String> localPart2;
    List<String> textNode2;
    List<String> resposta;
    DatabaseConnectionsTest conexao;
    DatabaseConnectionsTestDivide conexaoDivide;
    DatabaseConnectionsTestMultiply conexaoMultiply;

    public StepsTest() throws IOException {
        conexao = new DatabaseConnectionsTest();
        conexaoDivide = new DatabaseConnectionsTestDivide();
        conexaoMultiply = new DatabaseConnectionsTestMultiply();
    }

    //CT001
    @Dado("enviar a requisicao")
    public void enviarARequisicao() throws SOAPException {
        namespaceUri = conexao.getNamespaceUri();
        namespacePrefix = conexao.getNamespacePrefix();
        localName = conexao.getLocalName();
        localPart = conexao.getLocalPart();
        textNode = conexao.getTextNode();
        resposta = conexao.getResposta();

        //for (int i = 0; i < textNode.size(); i++){
            response = Endpoints.enviarRequisicao(namespaceUri.get(0), namespacePrefix.get(0), localName.get(0),
                    localPart.get(0), textNode.get(0));
        //}
    }

    @Entao("valido o status da minha resposta")
    public void validoOStatusDaMinhaResposta() {
        response.then().log().all()
                .assertThat().statusCode(200)
                .body(containsString(resposta.get(0)));
    }

    //CT002
    @Dado("enviar a requisicao words")
    public void enviarARequisicaoWords() throws SOAPException {
        namespaceUri = conexao.getNamespaceUri();
        namespacePrefix = conexao.getNamespacePrefix();
        localName = conexao.getLocalName();
        localPart = conexao.getLocalPart();
        textNode = conexao.getTextNode();
        resposta = conexao.getResposta();

        response = Endpoints.enviarRequisicao(namespaceUri.get(1), namespacePrefix.get(1), localName.get(1),
                localPart.get(1), textNode.get(1));
    }

    @Entao("valido a resposta da requisicao")
    public void validoARespostaDaRequisicao() {
        response.then().log().all()
                .assertThat().statusCode(200)
                .body(containsString(resposta.get(1)));
    }

    //CT003
    @Dado("enviar a requisicao add")
    public void enviarARequisicaoAdd() throws SOAPException {
        namespaceUri = conexao.getNamespaceUri();
        namespacePrefix = conexao.getNamespacePrefix();
        localName = conexao.getLocalName();
        localPart = conexao.getLocalPart();
        textNode = conexao.getTextNode();
        resposta = conexao.getResposta();

        response = Endpoints.enviarRequisicao(namespaceUri.get(2), namespacePrefix.get(2), localName.get(2),
                localPart.get(2), textNode.get(2));
    }

    @Entao("valido a resposta da requisicao add")
    public void validoARespostaDaRequisicaoAdd() {
        response.then().log().all()
                .assertThat().statusCode(200)
                .body(containsString(resposta.get(2)));
    }

    //CT004
    @Dado("enviar a requisicao divide")
    public void enviarARequisicaoDivide() throws SOAPException {
        namespaceUri = conexaoDivide.getNamespaceUri();
        localName = conexaoDivide.getLocalName();
        localPart = conexaoDivide.getLocalPart();
        textNode = conexaoDivide.getTextNode();
        localPart2 = conexaoDivide.getLocalPart2();
        textNode2 = conexaoDivide.getTextNode2();
        resposta = conexaoDivide.getResposta();

        for (int i = 0; i < textNode2.size(); i++){
            response = EndpointsDivide.enviarRequisicaoDivide(namespaceUri.get(i), localName.get(i), localPart.get(i), textNode.get(i),
                                                                localPart2.get(i), textNode2.get(i));
        }
    }

    @Entao("valido a resposta da requisicao divide")
    public void validoARespostaDaRequisicaoDivide() {
        for (int i = 0; i < resposta.size(); i++){
            response.then().log().all()
                    .assertThat().statusCode(200)
                    .body(containsString(resposta.get(i)));
        }
    }

    //CT005
    @Dado("enviar a requisicao multiply")
    public void enviarARequisicaoMultiply() throws SOAPException {
        namespaceUri = conexaoMultiply.getNamespaceUri();
        localName = conexaoMultiply.getLocalName();
        localPart = conexaoMultiply.getLocalPart();
        textNode = conexaoMultiply.getTextNode();
        localPart2 = conexaoMultiply.getLocalPart2();
        textNode2 = conexaoMultiply.getTextNode2();
        resposta = conexaoMultiply.getResposta();

        for (int i = 0; i < textNode2.size(); i++){
            response = EndpointsMultiply.enviarRequisicaoMultiply(namespaceUri.get(i), localName.get(i), localPart.get(i), textNode.get(i),
                    localPart2.get(i), textNode2.get(i));
        }
    }

    @Entao("valido a resposta da requisicao multiply")
    public void validoARespostaDaRequisicaoMultiply() {
        for (int i = 0; i < resposta.size(); i++){
            response.then().log().all()
                    .assertThat().statusCode(200)
                    .body(containsString(resposta.get(i)));
        }
    }

    //CT006
    @Dado("enviar a requisicao Subtract")
    public void enviarARequisicaoSubtract() {

    }
}
