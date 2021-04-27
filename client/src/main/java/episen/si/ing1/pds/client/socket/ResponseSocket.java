package episen.si.ing1.pds.client.socket;

public class ResponseSocket {
    private String event;
    private Object data;

    public String getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
