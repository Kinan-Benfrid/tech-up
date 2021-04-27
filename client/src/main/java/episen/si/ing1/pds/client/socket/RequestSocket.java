package episen.si.ing1.pds.client.socket;

public class RequestSocket {
    private String event;
    private Object data;

    public void setEvent(String event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }
}
