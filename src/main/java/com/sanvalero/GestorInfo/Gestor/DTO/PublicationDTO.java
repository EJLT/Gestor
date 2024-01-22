package com.sanvalero.GestorInfo.Gestor.DTO;

import java.util.Date;
import java.util.List;

public class PublicationDTO {

    private String title;
    private String content;
    private Long userId;
    private Long categoryId;
    private List<Long> tagIds;
    private List<Long> commentIds; // Agregar la lista de IDs de comentarios
    private Date creationDate;

    // Constructor con todos los campos
    public PublicationDTO(String title, String content, Long userId, Long categoryId, List<Long> tagIds, List<Long> commentIds) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.categoryId = categoryId;
        this.tagIds = tagIds;
        this.commentIds = commentIds;
    }

    // Getters y setters (incluido getCommentIds)

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public List<Long> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<Long> commentIds) {
        this.commentIds = commentIds;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
