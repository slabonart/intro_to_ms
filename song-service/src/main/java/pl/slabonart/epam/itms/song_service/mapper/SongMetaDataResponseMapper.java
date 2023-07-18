package pl.slabonart.epam.itms.song_service.mapper;

import org.mapstruct.Mapper;
import pl.slabonart.epam.itms.song_service.dto.SongMetaDataResponseDTO;
import pl.slabonart.epam.itms.song_service.entity.SongMetaData;


@Mapper(componentModel = "spring")
public interface SongMetaDataResponseMapper {

    SongMetaData toSongMetaData(SongMetaDataResponseDTO dto);

    SongMetaDataResponseDTO toDTO(SongMetaData songMetaData);
}
