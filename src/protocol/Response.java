package protocol;

import java.io.Serializable;

public class Response implements Serializable {
    private ResponseType responseType;
    private Object responseData;
    private String errorMessage;

    public Response(ResponseType responseType, Object responseData) {
        this.responseType = responseType;
        this.responseData = responseData;
    }

    public Response(ResponseType responseType, String errorMessage) {
        this.responseType = responseType;
        this.errorMessage = errorMessage;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Object getResponseData() {
        return responseData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
