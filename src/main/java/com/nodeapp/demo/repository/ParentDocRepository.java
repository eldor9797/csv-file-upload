package com.nodeapp.demo.repository;

import com.nodeapp.demo.entity.ParentDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentDocRepository extends JpaRepository<ParentDoc,Long> {
    Optional<ParentDoc> findByDoc_DocIdAndParentId(Long docId, Long parentId);
}
