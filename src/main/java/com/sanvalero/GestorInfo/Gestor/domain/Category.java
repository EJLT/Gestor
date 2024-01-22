package com.sanvalero.GestorInfo.Gestor.domain;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gestor_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // Relación uno a muchos con Publicación
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Publication> publications = new HashSet<>();
}
