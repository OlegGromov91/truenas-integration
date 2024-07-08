package ru.home.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.bot.HomeNasAppTgBot;
import ru.home.criteria.mongo.SmallFileCriteria;
import ru.home.criteria.mongo.TelegramUserCriteria;
import ru.home.model.file.FileType;
import ru.home.model.file.SmallFile;
import ru.home.model.user.TelegramUser;
import ru.home.repository.RepositoryFactory;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TelegramCommonService implements BotCommonService<HomeNasAppTgBot> {

    private final RestTelegramService restService;
    private final RepositoryFactory repository;

    public void getFileInfo(String fileId, String fileName, Long userTgId) {
        TelegramUser user = repository.criteria().searchOrThrow(TelegramUserCriteria.builder().tgId(userTgId).build());

        JSONObject fileInfo = restService.getFileInfo(fileId);

        String filePath = fileInfo.getJSONObject("result").getString("file_path");

        SmallFile torrentFile = SmallFile.builder()
                .type(FileType.TORRENT)
                .userId(user.getId())
                .filePath(filePath)
                .fileName(fileName)
                .build();

        repository.save(torrentFile);
    }

    @Transactional
    public void handleFileToTorrentClient(Long userTgId, String category, String path) {
        TelegramUser user = repository.criteria().searchOrThrow(TelegramUserCriteria.builder().tgId(userTgId).build());
        List<SmallFile> tgFiles = repository.criteria().search(SmallFileCriteria.builder().userId(user.getId()).build());
        SmallFile tgFile = tgFiles.stream().max(Comparator.comparing(SmallFile::getCreatingDate)).orElseThrow(RuntimeException::new);

        byte[] file = restService.downloadFileFromTelegram(tgFile.getFilePath());
        tgFile.setFile(SmallFile.toBinary(file));
        repository.save(tgFile);
        // todo: kafka = qbTorrentService.downloadTorrent(file, tgFile.getFileName(), path, category);
        System.out.println("файл отправлен в торрент клиент");
    }
}
