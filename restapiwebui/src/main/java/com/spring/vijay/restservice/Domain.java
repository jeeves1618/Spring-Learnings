package com.spring.vijay.restservice;

public class Domain {
    private long id;
    private String content;

    public Domain() {

    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setContent(String content){
        this.content = content;
    }


    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + content + '\'' +
                '}';
    }
}
