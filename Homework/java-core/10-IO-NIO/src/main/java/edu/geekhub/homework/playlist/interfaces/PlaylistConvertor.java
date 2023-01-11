package edu.geekhub.homework.playlist.interfaces;

import edu.geekhub.homework.playlist.PlaylistElement;

import java.io.File;
import java.util.List;

public interface PlaylistConvertor {
    List<PlaylistElement> convert(File playListRecords);
}
