package ru.home.view.qbTorrent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.home.model.qbTorrent.enums.Status;
import ru.home.model.qbTorrent.enums.TorrentCategory;

import java.util.Arrays;


@Getter
@Setter
@Builder
public class TorrentView {

    private Long id;
    private String hash;
    private String name;
    private String rootPath;
    private String contentPath;
    private Number rawTotalSize;
    private SizeView totalSize;
    private Number rawDownloadedSize;
    private SizeView downloadedSize;
    private Double downloadedPercent;
    private String state;
    private String torrentCategory;

    public String getTorrentInfo() {
        return "\tИмя='" + name + '\'' +
                ",\n Размер файла =" + totalSize.getSize() + " " + totalSize.getUnit() +
                ", Скаченно=" + downloadedSize.getSize() + " " + downloadedSize.getUnit() +
                " | " + downloadedPercent + "% " +
                ", Статус='" + extractStatus() +
                ", Категория='" + extractCategory() +
                '\''
                + "\n_________________\n"
                ;
    }

    private String extractStatus() {
        return Arrays.stream(Status.values()).filter(status -> status.getQbTorrentName().equals(state)).findFirst().orElse(Status.UNKNOWN).getDescription();
    }

    private String extractCategory() {
        return Arrays.stream(TorrentCategory.values()).filter(category -> category.getDescription().equals(this.torrentCategory)).findFirst().orElse(TorrentCategory.UNKNOWN).getDescription();
    }
}
