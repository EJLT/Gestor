package com.sanvalero.GestorInfo.Gestor.Repository;

import com.sanvalero.GestorInfo.Gestor.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByTextContaining(String text);

    List<Comment> findCommentsByPublicationId(Long publicationId);

    @Query("SELECT c FROM gestor_comment c WHERE " +
            "(:text IS NULL OR c.text LIKE %:text%) AND " +
            "(:publicationId IS NULL OR c.publication.id = :publicationId)")
    List<Comment> findCommentsByFilter(String text, Long publicationId);
}


