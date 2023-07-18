package pl.slabonart.epam.itms.song_resource_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SongResourceDeleteResponseDTO {

    List<Long> ids;

}
