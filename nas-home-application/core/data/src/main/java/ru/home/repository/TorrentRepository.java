package ru.home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.model.qbTorrent.Torrent;

import java.util.Optional;

@Repository
public interface TorrentRepository extends CrudRepository<Torrent, Long> {

    Optional<Torrent> findTorrentByHash(String hash);

    Optional<Torrent> findTorrentByHashAndName(String hash, String name);

}
