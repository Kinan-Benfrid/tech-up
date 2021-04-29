package episen.si.ing1.pds.backend.server.socket;

public class RequestSocket {
    private String request;
    private Object data;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
