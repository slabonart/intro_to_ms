package pl.slabonart.epam.itms.song_resource_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slabonart.epam.itms.song_resource_service.entity.SongResource;


public interface SongResourceRepository extends JpaRepository<SongResource, Long> {

}
