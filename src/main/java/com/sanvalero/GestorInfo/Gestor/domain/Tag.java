package com.sanvalero.GestorInfo.Gestor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gestor_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Publication> publications = new HashSet<>();


    public void initializePublications() {
        Hibernate.initialize(this.publications);
    }

}

