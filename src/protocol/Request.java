package protocol;

import java.io.Serializable;
import java.util.Properties;

public class Request implements Serializable {
    private RequestType requestType;
    private Properties properties;

    public Request(RequestType requestType, Properties properties) {
        this.requestType = requestType;
        this.properties = properties;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Properties getProperties() {
        return properties;
    }
}
