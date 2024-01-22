package com.sanvalero.GestorInfo.Gestor.Controller;

import com.sanvalero.GestorInfo.Gestor.Service.CommentService;
import com.sanvalero.GestorInfo.Gestor.domain.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getFilteredComments(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) Long publicationId
    ) {
        logger.debug("Fetching comments with text: {} and publicationId: {}", text, publicationId);
        List<Comment> filteredComments = commentService.getFilteredComments(text, publicationId);
        return new ResponseEntity<>(filteredComments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Comment> getCommentById(@PathVariable Long id) {
        logger.debug("Fetching comment with ID: {}", id);
        return commentService.getCommentById(id);
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        logger.debug("Adding a new comment: {}", comment);
        return commentService.addComment(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        logger.debug("Updating comment with ID: {}", id);
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        logger.debug("Deleting comment with ID: {}", id);
        commentService.deleteComment(id);
    }
}
