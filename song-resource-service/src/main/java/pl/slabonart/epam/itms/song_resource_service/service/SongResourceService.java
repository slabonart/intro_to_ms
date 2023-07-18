package pl.slabonart.epam.itms.song_resource_service.service;

import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import pl.slabonart.epam.itms.song_resource_service.client.SongServiceClient;
import pl.slabonart.epam.itms.song_resource_service.dto.SongMetaDataDTO;
import pl.slabonart.epam.itms.song_resource_service.dto.SongResourceCreateResponseDTO;
import pl.slabonart.epam.itms.song_resource_service.entity.SongResource;
import pl.slabonart.epam.itms.song_resource_service.exception.ResourceNotFoundException;
import pl.slabonart.epam.itms.song_resource_service.repository.SongResourceRepository;
import pl.slabonart.epam.itms.song_resource_service.util.SongMetaDataExtractor;


import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongResourceService {

    @Autowired
    private SongResourceRepository repository;

    @Autowired
    private SongServiceClient songServiceClient;


    private static final String DELIMITER = ",";

    public SongResourceCreateResponseDTO saveSongResource(SongResource songResource) {

        SongResource resource = repository.save(songResource);

        return SongResourceCreateResponseDTO.builder()
                .id(resource.getId())
                .build();
    }

    @Transactional
    public SongResourceCreateResponseDTO saveSongResource(MultipartFile file) throws IOException, TikaException, SAXException {

        SongResource resource = repository.save(
                SongResource.builder()
                        .resource(file.getBytes())
                        .fileName(file.getOriginalFilename())
                        .build()
        );

        SongMetaDataDTO metaDataDTO = SongMetaDataExtractor.extract(file);
        metaDataDTO.setResourceId(resource.getId());

        songServiceClient.createSong(metaDataDTO);

        return SongResourceCreateResponseDTO.builder()
                .id(resource.getId())
                .build();
    }

    public SongResource getById(long id) throws ResolutionException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Long> deleteSongResources(String ids) throws ResourceNotFoundException {
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
            throw new ResourceNotFoundException(notFound);
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
