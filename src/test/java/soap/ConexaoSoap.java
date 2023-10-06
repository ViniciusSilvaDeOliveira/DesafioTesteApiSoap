package soap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexaoSoap {

    static HttpURLConnection connection;
    URL url;
    public ConexaoSoap(String corpo) throws IOException {
        //Url
        url = new URL("https://www.dataaccess.com/webservicesserver/NumberConversion.wso");

        //Abrir uma conexão HTTP
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        connection.setDoOutput(true);

        String requestBody = corpo;

        //enviar corpo
        try (OutputStream os = connection.getOutputStream()){
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        //fechar conexão
        //connection.disconnect();
    }

    public String resposta(){
        //resposta da requisição
        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null){
                response.append(responseLine.trim());
            }
            return response.toString();

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
