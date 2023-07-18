package pl.slabonart.epam.itms.song_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.slabonart.epam.itms.song_service.entity.SongMetaData;
import pl.slabonart.epam.itms.song_service.exceptions.SongMetaDataNotFoundException;
import pl.slabonart.epam.itms.song_service.repository.SongRepository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository repository;

    private static final String DELIMITER = ",";

    public SongMetaData saveSong(SongMetaData songMetaData) {
        return repository.save(songMetaData);
    }

    public SongMetaData getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new SongMetaDataNotFoundException(id));
    }

    public List<Long> delete(String ids) {
        List<Long> deleted = new ArrayList<>();
        List<Long> notFound = new ArrayList<>();

        for (Long id : getIds(ids)) {
            try {
                repository.deleteById(id);
                deleted.add(id);
            } catch (EmptyResultDataAccessException ex) {
                notFound.add(id);
            }
        }
        if (deleted.isEmpty()) {
            throw new SongMetaDataNotFoundException(notFound);
        }
        return deleted;
    }

    private List<Long> getIds(String ids) {
        return parse(splitIds(ids));
    }

    private List<String> splitIds(String ids) {
        return Arrays.asList(ids.split(DELIMITER));
    }

    private List<Long> parse(List<String> list) {
        return list.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
