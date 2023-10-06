package conexaoBancoDeDados;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConnectionsTest {
    public DatabaseConnectionsTest() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("properties/conexao.properties"));

        String jdbcUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try{
            Class.forName("");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
