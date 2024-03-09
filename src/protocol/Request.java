package protocol;

import java.io.Serializable;

public class Request implements Serializable {
    private RequestType requestType;
    private Object requestData;

    public Request(RequestType requestType, Object requestData) {
        this.requestType = requestType;
        this.requestData = requestData;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Object getRequestData() {
        return requestData;
    }
}
