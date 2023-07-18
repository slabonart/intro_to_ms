package pl.slabonart.epam.itms.song_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SongMetaDataDeleteResponseDTO {

    List<Long> ids;

}
