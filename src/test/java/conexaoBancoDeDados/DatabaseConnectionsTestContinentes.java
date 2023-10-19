package conexaoBancoDeDados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnectionsTestContinentes {

    List<String> namespaceUri = new ArrayList<>();
    List<String> localName = new ArrayList<>();

    public DatabaseConnectionsTestContinentes() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\tivin\\Desktop\\Estudos\\EstagioBN\\DesafioTesteApiSoap\\src\\test\\resources\\properties\\conexao.properties"));

        String jdbcUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try{
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM continents";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                namespaceUri.add(resultSet.getString("namespaceuri"));
                localName.add(resultSet.getString("localname"));
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
}
