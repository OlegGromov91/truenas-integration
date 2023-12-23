package ru.home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.model.meTube.MeTubeVideo;
import ru.home.model.meTube.enums.VideoStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeTubeRepository extends CrudRepository<MeTubeVideo, Long> {

    Optional<MeTubeVideo> findByUrl(String url);

    List<MeTubeVideo> findByStatusIn(List<VideoStatus> statuses);

}
