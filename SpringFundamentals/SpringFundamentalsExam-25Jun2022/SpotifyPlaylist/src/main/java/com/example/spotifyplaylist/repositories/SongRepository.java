package com.example.spotifyplaylist.repositories;

import com.example.spotifyplaylist.models.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
