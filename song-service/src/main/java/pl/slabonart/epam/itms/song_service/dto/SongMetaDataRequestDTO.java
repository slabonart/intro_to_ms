package pl.slabonart.epam.itms.song_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SongMetaDataRequestDTO {

    private long resourceId;
    private String name;
    private String artist;
    private String album;
    private String length;
    private String year;

}
