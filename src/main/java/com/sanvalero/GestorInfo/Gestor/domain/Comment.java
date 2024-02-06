package com.sanvalero.GestorInfo.Gestor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gestor_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "El texto no puede exceder los 255 caracteres")
    @NotBlank(message = "El texto no puede estar en blanco")
    @Column
    private String text;

    @NotNull(message = "La publicación no puede ser nula")
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @Past(message = "La fecha del comentario debe estar en el pasado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;


    @Override
    public int hashCode() {
        return Objects.hash(id, text, commentDate);
    }
}
