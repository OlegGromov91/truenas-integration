package ru.home.model.meTube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.home.model.base.AbstractEntity;
import ru.home.model.meTube.enums.VideoStatus;
import ru.home.model.meTube.enums.VideoFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ME_TUBE_VIDEO")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MeTubeVideo extends AbstractEntity {

    @Column(name = "VIDEO_NAME")
    private String name;

    @Column(name = "CREATING_DATE")
    private LocalDateTime creatingDate;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "VIDEO_STATUS")
    @Enumerated(value = EnumType.STRING)
    private VideoStatus status;

    @Column(name = "VIDEO_FORMAT")
    @Enumerated(value = EnumType.STRING)
    private VideoFormat videoFormat;

}
