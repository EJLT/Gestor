package com.sanvalero.GestorInfo.Gestor.Service;

import com.sanvalero.GestorInfo.Gestor.DTO.PublicationDTO;
import com.sanvalero.GestorInfo.Gestor.Repository.*;
import com.sanvalero.GestorInfo.Gestor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PublicationService {


    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, UserRepository userRepository,
                              CategoryRepository categoryRepository,
                              TagRepository tagRepository, CommentRepository commentRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }


    public List<Publication> getFilteredPublications(String title, String user, String category) {
        return publicationRepository.findPublicationsByFilter(title, user, category);
    }

    public Optional<Publication> getPublicationById(Long id) {
        return publicationRepository.findById(id);
    }

    public Publication createPublication(PublicationDTO publicationDTO) {
        publicationDTO.setCreationDate(new Date());
        Publication publication = convertToEntity(publicationDTO);

        // Guardar la publicación
        publication = publicationRepository.save(publication);

        // Recargar la publicación con las relaciones cargadas
        publication = publicationRepository.findById(publication.getId()).orElse(null);

        // Devolver la publicación creada
        return publication;
    }


    public Publication updatePublication(Long id, Publication updatedPublication) {
        Optional<Publication> existingPublication = publicationRepository.findById(id);
        if (existingPublication.isPresent()) {
            Publication publication = existingPublication.get();
            publication.setTitle(updatedPublication.getTitle());
            publication.setContent(updatedPublication.getContent());
            return publicationRepository.save(publication);
        } else {
            throw new RuntimeException("La publicación con ID " + id + " no existe");
        }
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Transactional
    public Publication convertToEntity(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        publication.setTitle(publicationDTO.getTitle());
        publication.setContent(publicationDTO.getContent());

        // Asociar usuario y categoría (asumiendo que ya existen en la base de datos)
        User user = userRepository.findById(publicationDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + publicationDTO.getUserId()));
        publication.setUser(user);

        Category category = categoryRepository.findById(publicationDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + publicationDTO.getCategoryId()));
        publication.setCategory(category);

        // Cargar las etiquetas dentro de una transacción de Hibernate
        Set<Tag> tags = new HashSet<>();
        if (publicationDTO.getTagIds() != null) {
            for (Long tagId : publicationDTO.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new EntityNotFoundException("Etiqueta no encontrada con ID: " + tagId));
                tags.add(tag);
            }
        }
        publication.setTags(tags);

        // Cargar los comentarios dentro de una transacción de Hibernate
        Set<Comment> comments = new HashSet<>();
        if (publicationDTO.getCommentIds() != null) {
            for (Long commentId : publicationDTO.getCommentIds()) {
                Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado con ID: " + commentId));
                comments.add(comment);
            }
        }
        publication.setComments(comments);

        return publication;
    }


}




