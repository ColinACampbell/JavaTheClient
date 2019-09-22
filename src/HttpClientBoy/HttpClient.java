package HttpClientBoy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class HttpClient implements ClientInterface {



    private StringBuilder result = new StringBuilder();
    private BufferedReader rd;
    @Override
    public String getResultStringFromUrl(String link) {

        try
        {
            link = link.replaceAll("\\s","%20"); // replace all space with %20 that is recognizable by http
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try
            {
                rd =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } catch (ConnectException e)
            {
                e.printStackTrace();
            }

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * This can be use to send data through GET
     * @param link string
     * @return string
     */
    @Override
    public String pushDataSet(String link) {
        try
        {
            link = link.replaceAll("\\s","%20");
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

    }


    /**
     * Only use this to post information, given a  url www.foogle.com, where I have parameters id and page to get per say a page
     * Use : String urlParameters = "id="+id+"&page="+page;
     */
    private static HttpURLConnection con;
    @Override
    public String pushDataSet(String urlPath,String urlParameters)
    {

        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            URL url = new URL(urlPath);
            con = (HttpURLConnection) url.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            return content.toString().trim();


        } catch (Exception e)
        {
            e.printStackTrace();
        } finally {

            con.disconnect();
        }
        return null;
    }
}