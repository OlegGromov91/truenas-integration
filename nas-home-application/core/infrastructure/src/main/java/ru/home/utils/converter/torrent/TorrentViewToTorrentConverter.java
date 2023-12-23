package ru.home.utils.converter.torrent;

import ru.home.model.qbTorrent.Size;
import ru.home.model.qbTorrent.Torrent;
import ru.home.model.qbTorrent.enums.Status;
import ru.home.model.qbTorrent.enums.TorrentCategory;
import ru.home.view.qbTorrent.TorrentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TorrentViewToTorrentConverter implements Converter<TorrentView, Torrent> {

    @Override
    public Torrent convert(TorrentView source) {
        return Torrent.builder()
                .id(source.getId())
                .hash(source.getHash())
                .name(source.getName())
                .rootPath(source.getRootPath())
                .contentPath(source.getContentPath())
                .rawTotalSize(source.getRawTotalSize())
                .totalSize(Size.builder()
                        .size(source.getTotalSize().getSize())
                        .unit(source.getTotalSize().getUnit())
                        .build())
                .rawDownloadedSize(source.getRawDownloadedSize())
                .downloadedSize(Size.builder()
                        .size(source.getDownloadedSize().getSize())
                        .unit(source.getDownloadedSize().getUnit())
                        .build())
                .downloadedPercent(source.getDownloadedPercent())
                .state(extractStatus(source.getState()))
                .torrentCategory(extractCategory(source.getTorrentCategory()))
                .build();
    }

    private Status extractStatus(String state) {
        return Arrays.stream(Status.values()).filter(status -> status.getQbTorrentName().equals(state)).findFirst().orElse(Status.UNKNOWN);
    }

    private TorrentCategory extractCategory(String torrentCategory) {
        return Arrays.stream(TorrentCategory.values()).filter(category -> category.getDescription().equals(torrentCategory)).findFirst().orElse(TorrentCategory.UNKNOWN);
    }
}
