package protocol;

import java.util.Properties;

public class Response {
    private ResponseType responseType;

    private Properties properties;

    public Response(ResponseType responseType) {
        this.responseType = responseType;
        this.properties = new Properties();

    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Properties getProperties() {
        return properties;
    }
}
