package episen.si.ing1.pds.client.socket;

public class ResponseSocket {
    private String request ;
    private Object data ;


    public String getRequest() {
        return request;
    }

    public Object getData() {
        return data;
    }

    public void setRequest(String event) {
        this.request = request;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
