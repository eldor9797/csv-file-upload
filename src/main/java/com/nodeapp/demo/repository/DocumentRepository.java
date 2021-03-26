package com.nodeapp.demo.repository;

import com.nodeapp.demo.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Long> {

    @Query( value = "select d.key, d.childs, d1.name ,d1.date   from document d " +
            "                left join document d1 on d.childs = d1.childs  " +
            "               group by d.key, d.childs, d1.name, d1.date order by d.childs", nativeQuery = true)
    List<Object[]> getResult();
}
