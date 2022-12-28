package edu.geekhub.homework;

import edu.geekhub.homework.playlist.PlaylistConvertor;
import edu.geekhub.homework.playlist.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaylistServiceTest {
    PlaylistService playlistService;

    @BeforeEach
    void setUp() {
        playlistService = new PlaylistService(new PlaylistConvertor());
    }

    @Test
    void test() {
        playlistService.saveSongsOnDrive();
        playlistService.saveSongsOnDrive();
        playlistService.saveSongsOnDrive();
        playlistService.saveSongsOnDrive();
    }
}
