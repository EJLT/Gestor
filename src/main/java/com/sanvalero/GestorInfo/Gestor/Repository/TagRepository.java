package com.sanvalero.GestorInfo.Gestor.Repository;

import com.sanvalero.GestorInfo.Gestor.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM gestor_tag t WHERE LOWER(t.name) LIKE %:name%")
    List<Tag> findTagsByNameContaining(@Param("name") String name);




}

