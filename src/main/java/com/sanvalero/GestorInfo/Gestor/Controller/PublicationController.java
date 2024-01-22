package com.sanvalero.GestorInfo.Gestor.Controller;

import com.sanvalero.GestorInfo.Gestor.DTO.PublicationDTO;
import com.sanvalero.GestorInfo.Gestor.Service.PublicationService;
import com.sanvalero.GestorInfo.Gestor.domain.Publication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    private static final Logger logger = LoggerFactory.getLogger(PublicationController.class);

    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public List<Publication> getFilteredPublications(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String category) {
        logger.debug("Fetching publications with title: {}, user: {}, and category: {}", title, user, category);
        return publicationService.getFilteredPublications(title, user, category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable Long id) {
        logger.debug("Fetching publication with ID: {}", id);
        Publication publication = publicationService.getPublicationById(id).orElse(null);
        if (publication != null) {
            return ResponseEntity.ok(publication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Publication> createPublication(@RequestBody PublicationDTO publicationDTO) {
        logger.debug("Creating a new publication: {}", publicationDTO);
        Publication createdPublication = publicationService.createPublication(publicationDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPublication.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdPublication);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @RequestBody Publication updatedPublication) {
        logger.debug("Updating publication with ID: {}", id);
        Publication publication = publicationService.updatePublication(id, updatedPublication);
        if (publication != null) {
            return ResponseEntity.ok(publication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable Long id) {
        logger.debug("Deleting publication with ID: {}", id);
        publicationService.deletePublication(id);
        String message = "Publicación con ID " + id + " se ha borrado correctamente.";
        return ResponseEntity.ok(message);
    }

}
