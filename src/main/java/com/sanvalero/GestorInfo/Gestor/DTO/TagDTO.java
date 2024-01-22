package com.sanvalero.GestorInfo.Gestor.DTO;

import lombok.Data;

@Data
public class TagDTO {
    private Long id;
    private String name;

    public TagDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
