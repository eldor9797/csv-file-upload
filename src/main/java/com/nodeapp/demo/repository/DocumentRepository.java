package com.nodeapp.demo.repository;

import com.nodeapp.demo.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {

}
