package com.nodeapp.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "document")
public class Document {

    @Id
    private Long docId;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Instant date;

    public Document() {
    }

    public Document(Long id, String name, Instant date) {
        this.docId = id;
        this.name = name;
        this.date = date;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
