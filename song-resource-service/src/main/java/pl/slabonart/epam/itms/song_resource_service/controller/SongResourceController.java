package pl.slabonart.epam.itms.song_resource_service.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import pl.slabonart.epam.itms.song_resource_service.dto.SongResourceDeleteResponseDTO;
import pl.slabonart.epam.itms.song_resource_service.entity.SongResource;
import pl.slabonart.epam.itms.song_resource_service.exception.InvalidFileException;
import pl.slabonart.epam.itms.song_resource_service.exception.ResourceNotFoundException;
import pl.slabonart.epam.itms.song_resource_service.service.SongResourceService;


import java.io.IOException;
import java.util.List;

@RestController
public class SongResourceController {

    private static final String CONTENT_TYPE = "audio/mp3";

    @Autowired
    private SongResourceService service;

    @PostMapping(
            path = "/resources",
            produces = "application/json"
    )
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException, InvalidFileException, TikaException, SAXException {
        validateFile(file);
        return ResponseEntity.ok(service.saveSongResource(file));
    }

    @GetMapping("/resources/{id}")
    public ResponseEntity downloadFile(@PathVariable("id") Integer id) {
        SongResource resource = service.getById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(CONTENT_TYPE))
                .header("Content-Disposition", "attachment;filename=" + resource.getFileName())
                .body(resource.getResource());
    }

    @DeleteMapping(value = "/resources")
    public ResponseEntity deleteSongResources(@RequestParam(name = "id") String id) throws ResourceNotFoundException {
        List<Long> deletedIds = service.deleteSongResources(id);
        return ResponseEntity.ok(SongResourceDeleteResponseDTO.builder()
                .ids(deletedIds)
                .build());
    }

    private void validateFile(MultipartFile file) throws InvalidFileException {
        if (isFileEmpty(file) || isFileInvalidType(file)) {
            throw new InvalidFileException();
        }
    }

    private boolean isFileEmpty(MultipartFile file) {
        return file.isEmpty() || file.getSize() == 0;
    }

    private boolean isFileInvalidType(MultipartFile file) {
        return !file.getContentType().equals("audio/mpeg") || !FilenameUtils.getExtension(file.getOriginalFilename()).equals("mp3");
    }
}
