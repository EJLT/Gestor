package com.sanvalero.GestorInfo.Gestor.Service;

import com.sanvalero.GestorInfo.Gestor.Repository.CommentRepository;
import com.sanvalero.GestorInfo.Gestor.domain.Comment;
import com.sanvalero.GestorInfo.Gestor.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public List<Comment> getFilteredComments(String text, Long publicationId) {
        if (text != null && publicationId != null) {
            // Ambos parámetros de filtro presentes
            return commentRepository.findCommentsByFilter(text, publicationId);
        } else if (text != null) {
            // Solo filtro por texto presente
            return commentRepository.findCommentsByTextContaining(text);
        } else if (publicationId != null) {
            // Solo filtro por ID de publicación presente
            return commentRepository.findCommentsByPublicationId(publicationId);
        } else {
            // Sin filtros, devuelve todos los comentarios
            return commentRepository.findAll();
        }
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }


    public Comment updateComment(Long id, Comment updatedComment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment comment = existingComment.get();
            comment.setText(updatedComment.getText());
            return commentRepository.save(comment);
        }
        return null;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


    public Comment addComment(Comment comment) {
        // Establecer la fecha actual si no está presente
        if (comment.getCommentDate() == null) {
            comment.setCommentDate(new Date());
        }

        // Si la publicación está presente, establecer la relación bidireccional
        if (comment.getPublication() != null) {
            comment.getPublication().getComments().add(comment);
        }

        return commentRepository.save(comment);
    }
}


