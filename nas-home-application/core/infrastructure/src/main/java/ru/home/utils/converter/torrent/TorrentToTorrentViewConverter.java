package ru.home.utils.converter.torrent;

import ru.home.model.qbTorrent.Torrent;
import ru.home.view.qbTorrent.SizeView;
import ru.home.view.qbTorrent.TorrentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TorrentToTorrentViewConverter implements Converter<Torrent, TorrentView> {

    @Override
    public TorrentView convert(Torrent source) {
        return TorrentView.builder()
                .id(source.getId())
                .hash(source.getHash())
                .name(source.getName())
                .rootPath(source.getRootPath())
                .contentPath(source.getContentPath())
                .rawTotalSize(source.getRawTotalSize())
                .totalSize(SizeView.builder()
                        .size(source.getTotalSize().getSize())
                        .unit(source.getTotalSize().getUnit())
                        .build())
                .rawDownloadedSize(source.getRawDownloadedSize())
                .downloadedSize(SizeView.builder()
                        .size(source.getDownloadedSize().getSize())
                        .unit(source.getDownloadedSize().getUnit())
                        .build())
                .downloadedPercent(source.getDownloadedPercent())
                .state(source.getState().getDescription())
                .torrentCategory(source.getTorrentCategory().getDescription())
                .build();
    }


}
