package conexaoBancoDeDados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnectionsTestMultiply {
    List<String> namespaceUri = new ArrayList<>();
    List<String> localName = new ArrayList<>();
    List<String> localPart = new ArrayList<>();
    List<String> textNode = new ArrayList<>();
    List<String> localPart2 = new ArrayList<>();
    List<String> textNode2 = new ArrayList<>();
    List<String> resposta = new ArrayList<>();

    public DatabaseConnectionsTestMultiply() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\tivin\\Desktop\\Estudos\\EstagioBN\\DesafioTesteApiSoap\\src\\test\\resources\\properties\\conexao.properties"));

        String jdbcUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try{
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM multiply";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()){
                namespaceUri.add(resultSet.getString("namespaceURI"));
                localName.add(resultSet.getString("localName"));
                localPart.add(resultSet.getString("localPart"));
                textNode.add(resultSet.getString("textNode"));
                localPart2.add(resultSet.getString("localPart2"));
                textNode2.add(resultSet.getString("textNode2"));
                resposta.add(resultSet.getString("resposta"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getNamespaceUri() {
        return namespaceUri;
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

    public List<String> getLocalPart2() {
        return localPart2;
    }

    public List<String> getTextNode2() {
        return textNode2;
    }

    public List<String> getResposta() {
        return resposta;
    }
}
