package ru.home.model.file;

public enum FileType {

    TORRENT("torrent"),
    ;

    private final String type;

    FileType(String type) {
        this.type = type;
    }
}
