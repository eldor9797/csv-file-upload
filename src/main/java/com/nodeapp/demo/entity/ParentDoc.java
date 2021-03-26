package com.nodeapp.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "parent_docs")
public class ParentDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doc_id", nullable=false)
    private Document doc;

    @Column(name = "parent_id")
    private Long parentId;

    public ParentDoc(Document doc, Long parentId) {
        this.doc = doc;
        this.parentId = parentId;
    }

    public ParentDoc() {
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
