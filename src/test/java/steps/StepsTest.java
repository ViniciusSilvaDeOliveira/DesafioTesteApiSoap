package steps;

import conexaoBancoDeDados.DatabaseConnectionsTest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.example.Endpoints;
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
    List<String> resposta;
    DatabaseConnectionsTest conexao;

    public StepsTest() throws IOException {
        conexao = new DatabaseConnectionsTest();
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
}
