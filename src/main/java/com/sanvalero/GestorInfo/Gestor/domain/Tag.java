package com.sanvalero.GestorInfo.Gestor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gestor_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Publication> publications = new HashSet<>();


    // Puedes mantener tu método existente para inicializar publicaciones
    public void initializePublications() {
        Hibernate.initialize(this.publications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
