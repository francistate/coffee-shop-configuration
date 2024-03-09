package protocol;

import java.io.Serializable;

public class Request implements Serializable {
    private RequestType requestType;
    private Object requestData;
    private String[] returnStingArray;
    private String returnString;

    public Request(RequestType requestType, Object requestData) {
        this.requestType = requestType;
        this.requestData = requestData;
        this.returnStingArray = null;
        this.returnString = null;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Object getRequestData() {
        return requestData;
    }

    public void setReturnStingArray(String[] returnStingArray) {
        this.returnStingArray = returnStingArray;
    }

    public void setReturnString(String returnString) {
        this.returnString = returnString;
    }

    public String[] getReturnStingArray() {
        return returnStingArray;
    }

    public String getReturnString() {
        return returnString;
    }
}
