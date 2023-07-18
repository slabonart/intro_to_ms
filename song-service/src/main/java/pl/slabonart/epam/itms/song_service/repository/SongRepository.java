package pl.slabonart.epam.itms.song_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slabonart.epam.itms.song_service.entity.SongMetaData;


public interface SongRepository extends JpaRepository<SongMetaData, Long> {

}
