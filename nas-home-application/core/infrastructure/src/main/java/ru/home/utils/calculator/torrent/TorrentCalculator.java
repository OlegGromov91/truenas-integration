package ru.home.utils.calculator.torrent;

import ru.home.utils.calculator.base.Calculator;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TorrentCalculator implements Calculator {

    public Double calculateDownloadingPercent(@NotNull Number totalSize, @NotNull Number downloaded) {
        return doFormat(100 * downloaded.doubleValue() / totalSize.doubleValue());
    }

}
