package conexaoBancoDeDados;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnectionsTest {

    List<String> namespaceUri = new ArrayList<>();
    List<String> namespacePrefix = new ArrayList<>();
    List<String> localName = new ArrayList<>();
    List<String> localPart = new ArrayList<>();
    List<String> textNode = new ArrayList<>();
    List<String> resposta = new ArrayList<>();

    public DatabaseConnectionsTest() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\tivin\\Desktop\\Estudos\\EstagioBN\\DesafioTesteApiSoap\\src\\test\\resources\\properties\\conexao.properties"));

        String jdbcUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try{
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM dados_soap";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                namespaceUri.add(resultSet.getString("namespaceuri"));
                namespacePrefix.add(resultSet.getString("namespaceprefix"));
                localName.add(resultSet.getString("localname"));
                localPart.add(resultSet.getString("localpart"));
                textNode.add(resultSet.getString("textnode"));
                resposta.add(resultSet.getString("resposta"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getNamespaceUri() {
        return namespaceUri;
    }

    public List<String> getNamespacePrefix() {
        return namespacePrefix;
    }

    public List<String> getLocalName() {
        return localName;
    }

    public List<String> getLocalPart() {
        return localPart;
    }

    public List<String> getTextNode() {
        return textNode;
    }

    public List<String> getResposta() {
        return resposta;
    }
}
