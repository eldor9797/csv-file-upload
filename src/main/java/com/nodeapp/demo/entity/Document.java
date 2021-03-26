package com.nodeapp.demo.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key")
    private Long key;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Instant date;

    @Column(name = "childs")
    private String childlist;

    public Document() {
    }

    public Document(Long key, String name, Instant date, String childlist) {
        this.key = key;
        this.name = name;
        this.date = date;
        this.childlist = childlist;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildlist() {
        return childlist;
    }

    public void setChildlist(String childlist) {
        this.childlist = childlist;
    }
}
