package ru.home.model.meTube.enums;

import lombok.Getter;

@Getter
public enum VideoStatus {
    IN_QUEUE("В очереди"),
    DOWNLOADING("Загружается"),
    DONE("Загружен"),
    ERROR("Ошибка"),
    ;

    private final String description;

    VideoStatus(String description) {
        this.description = description;
    }

}
