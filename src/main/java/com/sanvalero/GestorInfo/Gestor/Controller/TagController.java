package com.sanvalero.GestorInfo.Gestor.Controller;

import com.sanvalero.GestorInfo.Gestor.DTO.TagDTO;
import com.sanvalero.GestorInfo.Gestor.Service.TagService;
import com.sanvalero.GestorInfo.Gestor.domain.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getFilteredTags(@RequestParam(name = "name", required = false) String name) {
        logger.debug("Fetching tags with name: {}", name);

        if (name != null) {
            return tagService.getFilteredTags(name);
        }
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Optional<Tag> getTagById(@PathVariable Long id) {
        logger.debug("Fetching tag with ID: {}", id);
        return tagService.getTagById(id);
    }

    @PostMapping
    public Tag addTag(@RequestBody Tag tag) {
        logger.debug("Creating a new tag: {}", tag);
        return tagService.addTag(tag);
    }

    @PutMapping("/{id}")
    public TagDTO updateTag(@PathVariable Long id, @RequestBody TagDTO updatedTagDTO) {
        logger.debug("Updating tag with ID: {}", id);
        return tagService.updateTag(id, updatedTagDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        logger.debug("Deleting tag with ID: {}", id);
        tagService.deleteTag(id);
        String message = "Etiqueta con ID " + id + " se ha borrado exitosamente.";
        return ResponseEntity.ok(message);
    }

}
