package com.sanvalero.GestorInfo.Gestor.Service;

import com.sanvalero.GestorInfo.Gestor.DTO.TagDTO;
import com.sanvalero.GestorInfo.Gestor.Repository.TagRepository;
import com.sanvalero.GestorInfo.Gestor.domain.Publication;
import com.sanvalero.GestorInfo.Gestor.domain.Tag;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagService {

    @PersistenceContext
    private EntityManager entityManager;
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional
    public List<Tag> getFilteredTags(String name) {
        List<Tag> tags = entityManager.createQuery(
                        "SELECT t FROM gestor_tag t LEFT JOIN FETCH t.publications WHERE LOWER(t.name) LIKE LOWER(:name)",
                        Tag.class
                )
                .setParameter("name", "%" + name + "%")
                .getResultList();

        // Inicializar las colecciones antes de cerrar la sesión
        tags.forEach(tag -> Hibernate.initialize(tag.getPublications()));

        return tags;
    }
    public List<Tag> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        for (Tag tag : tags) {
            // Antes de devolver las etiquetas, inicializa la colección de publicaciones
            tag.initializePublications();
        }
        return tags;
    }


    @Transactional
    public Optional<Tag> getTagById(Long id) {
        try {
            Tag tag = entityManager.createQuery(
                            "SELECT t FROM gestor_tag t LEFT JOIN FETCH t.publications WHERE t.id = :id",
                            Tag.class
                    )
                    .setParameter("id", id)
                    .getSingleResult();

            // Inicializar la colección antes de cerrar la sesión
            Hibernate.initialize(tag.getPublications());

            return Optional.of(tag);
        } catch (NoResultException e) {
            return Optional.empty(); // Devolver Optional vacío si no se encuentra ninguna entidad
        }
    }


    public TagDTO updateTag(Long id, TagDTO updatedTagDTO) {
        Optional<Tag> existingTagOptional = tagRepository.findById(id);
        if (existingTagOptional.isPresent()) {
            Tag existingTag = existingTagOptional.get();
            existingTag.setName(updatedTagDTO.getName());

            // Guardar la entidad actualizada
            Tag updatedTag = tagRepository.save(existingTag);

            // Convertir la entidad actualizada a DTO antes de devolverla
            return convertToDTO(updatedTag);
        }
        return null;
    }

    // Método para convertir una entidad Tag a un DTO TagDTO
    private TagDTO convertToDTO(Tag tag) {
        return new TagDTO(tag.getId(), tag.getName());
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }


    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }


    @Transactional
    public Tag getTagWithPublications(Long tagId) {
        Tag tag = tagRepository.findById(tagId).orElse(null);

        // Acceder a la colección dentro de la transacción
        if (tag != null) {
            Set<Publication> publications = tag.getPublications();

        }

        return tag;
    }
}
