package pl.slabonart.epam.itms.song_service.mapper;

import org.mapstruct.Mapper;
import pl.slabonart.epam.itms.song_service.dto.SongMetaDataRequestDTO;
import pl.slabonart.epam.itms.song_service.entity.SongMetaData;


@Mapper(componentModel = "spring")
public interface SongMetaDataRequestMapper {

    SongMetaData toSongMetaData(SongMetaDataRequestDTO dto);

    SongMetaDataRequestDTO toDTO(SongMetaData songMetaData);
}
