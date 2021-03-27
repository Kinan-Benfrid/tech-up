package episen.si.ing1.pds.client;


import java.util.Map;

public class Student {
    private Map<String,String> insert;

    public Student() {
    }

    public Map<String, String> getInsert() {
        return insert;
    }

    public void setInsert(Map<String, String> insert) {
        this.insert = insert;
    }

    @Override
    public String toString() {
        return "Student{" +
                "insert=" + insert +
                '}';
    }
}
