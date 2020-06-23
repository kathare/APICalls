import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ReadJson {

    private static HttpURLConnection connection;
    public static void main (String args []){

        StringBuffer readContent = new StringBuffer();
        String line;
        URL url;

        {
            try {
                url = new URL("https://jsonplaceholder.typicode.com/albums");
                connection =(HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(500000);
                connection.setReadTimeout(500000);

                int status = connection.getResponseCode();
                //System.out.println (status);
                if (status>299){
                    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getErrorStream()));
                    while ((line = reader.readLine()) != null){
                            readContent.append(line);
                    }
                    reader.close();
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream()));
                    while ((line = reader.readLine()) != null){
                        readContent.append(line);
                    }
                    reader.close();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                connection.disconnect();
            }
        }


    }
}
