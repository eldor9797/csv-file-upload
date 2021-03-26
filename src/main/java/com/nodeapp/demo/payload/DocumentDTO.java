package com.nodeapp.demo.payload;


import com.opencsv.bean.CsvBindByName;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class DocumentDTO {

    @CsvBindByName
    private String id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String date;

    public DocumentDTO() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

