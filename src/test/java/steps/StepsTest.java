package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.example.Endpoints;
import org.junit.Assert;
import soap.ConexaoSoap;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.List;

public class StepsTest {
    private static List<String> list;
    Response teste;

    @Dado("enviar a requisicao")
    public void enviarARequisicao() throws SOAPException {
        teste = Endpoints.enviarRequisicao();
    }

    @Entao("valido o status da minha resposta")
    public void validoOStatusDaMinhaResposta() {
        teste.then().log().all();
        Assert.assertEquals(teste.statusCode(), 200);
    }
}
