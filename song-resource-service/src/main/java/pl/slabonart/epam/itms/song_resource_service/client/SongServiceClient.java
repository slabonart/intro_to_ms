package pl.slabonart.epam.itms.song_resource_service.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.slabonart.epam.itms.song_resource_service.dto.SongMetaDataDTO;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "song-service")
@RibbonClient(name = "song-service")
public interface SongServiceClient {

    @PostMapping(value = "songs/", consumes = {APPLICATION_JSON_VALUE})
    ResponseEntity<?> createSong(@RequestBody SongMetaDataDTO songMetaDataDTO);
}
