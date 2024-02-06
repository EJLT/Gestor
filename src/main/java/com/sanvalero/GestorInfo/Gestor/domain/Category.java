package com.sanvalero.GestorInfo.Gestor.domain;
import com.sanvalero.GestorInfo.Gestor.Validation.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gestor_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Publication> publications = new HashSet<>();

    // Considera agregar métodos para facilitar la gestión bidireccional
    public void addPublication(Publication publication) {
        publications.add(publication);
        publication.setCategory(this);
    }

    public void removePublication(Publication publication) {
        publications.remove(publication);
        publication.setCategory(null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
