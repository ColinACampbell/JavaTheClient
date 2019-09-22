package HttpClientBoy;

public interface ClientInterface {
    String getResultStringFromUrl(String url);
    String pushDataSet(String url);
    String pushDataSet(String url,String urlParameters);
}
