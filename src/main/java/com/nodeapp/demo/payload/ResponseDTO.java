package com.nodeapp.demo.payload;

import java.sql.Timestamp;
import java.time.Instant;

public class ResponseDTO {
    private String id;
    private String name;
    private Timestamp date;
    private String parentId;

    public ResponseDTO() {
    }

    public ResponseDTO(String id, String name, Timestamp date, String parentId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
