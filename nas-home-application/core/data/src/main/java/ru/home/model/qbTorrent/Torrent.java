package ru.home.model.qbTorrent;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.home.model.base.AbstractEntity;
import ru.home.model.qbTorrent.enums.Status;
import ru.home.model.qbTorrent.enums.TorrentCategory;

import javax.persistence.*;

@Entity
@Table(name = "TORRENT")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Torrent extends AbstractEntity {

    @Column(name = "HASH_NAME", unique = true, nullable = false)
    private String hash;

    @Column(name = "TORRENT_NAME")
    private String name;

    @Column(name = "ROOT_PATH")
    private String rootPath;

    @Column(name = "CONTENT_PATH")
    private String contentPath;

    @Column(name = "RAW_TOTAL_SIZE")
    private Number rawTotalSize;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "size", column = @Column(name = "CONVERTED_TOTAL_SIZE")),
            @AttributeOverride(name = "unit", column = @Column(name = "CONVERTED_TOTAL_UNIT")),
    })
    private Size totalSize;

    @Column(name = "RAW_DOWNLOADED_SIZE")
    private Number rawDownloadedSize;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "size", column = @Column(name = "CONVERTED_DOWNLOADED_SIZE")),
            @AttributeOverride(name = "unit", column = @Column(name = "CONVERTED_DOWNLOADED_UNIT")),
    })
    private Size downloadedSize;


    @Column(name = "DOWNLOADED_PROCENT")
    private Double downloadedPercent;

    @Column(name = "STATE")
    @Enumerated(value = EnumType.STRING)
    private Status state;

    @Column(name = "TORRENT_CATEGORY")
    @Enumerated(value = EnumType.STRING)
    private TorrentCategory torrentCategory;

}
