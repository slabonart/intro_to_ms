package pl.slabonart.epam.itms.song_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.slabonart.epam.itms.song_service.dto.SongMetaDataDeleteResponseDTO;
import pl.slabonart.epam.itms.song_service.dto.SongMetaDataRequestDTO;
import pl.slabonart.epam.itms.song_service.entity.SongMetaData;
import pl.slabonart.epam.itms.song_service.exceptions.InvalidRequestException;
import pl.slabonart.epam.itms.song_service.mapper.SongMetaDataRequestMapper;
import pl.slabonart.epam.itms.song_service.mapper.SongMetaDataResponseMapper;
import pl.slabonart.epam.itms.song_service.service.SongService;


import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
public class SongController {

    @Autowired
    private SongService service;

    @Autowired
    SongMetaDataRequestMapper requestMapper;

    @Autowired
    SongMetaDataResponseMapper responseMapper;

    @PostMapping("/songs")
    public ResponseEntity createSong(@RequestBody SongMetaDataRequestDTO request) throws InvalidRequestException {

        validateDTO(request);
        SongMetaData saved = service.saveSong(requestMapper.toSongMetaData(request));
        return ResponseEntity.ok(responseMapper.toDTO(saved));
    }

    @GetMapping("songs/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {

        SongMetaData song = service.getById(id);
        return ResponseEntity.ok(responseMapper.toDTO(song));
    }

    @DeleteMapping("/songs")
    public ResponseEntity delete(@RequestParam(name = "id") String id) {

        List<Long> deleted = service.delete(id);
        return ResponseEntity.ok(SongMetaDataDeleteResponseDTO.builder()
                .ids(deleted)
                .build());
    }

    private void validateDTO(SongMetaDataRequestDTO request) throws InvalidRequestException {
        if (isEmpty(request.getName()) || isEmpty(request.getResourceId())) {
            throw new InvalidRequestException();
        }
    }
}
