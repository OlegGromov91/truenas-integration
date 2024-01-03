package ru.home.service;

import org.springframework.stereotype.Service;

@Service
public class BotCommonService {

//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RestTelegramBotService restTelegramBotService;
//    @Autowired
//    private TelegramFilesRepository telegramFilesRepository;
//    @Autowired
//    protected QbTorrentService qbTorrentService;
//
//    @Transactional
//    public void getFileInfo(String fileId, String fileName, Long userId) {
//        ApplicationUser user = userService.getUserByTelegramId(userId);
//        JSONObject fileInfo = restTelegramBotService.getFileInfo(fileId);
//
//        if (!fileInfo.keySet().contains("ok")) {
//            throw new RestQbTorrentException("when try get fileInfo response get wrong code");
//        }
//
//        String filePath = String.valueOf(fileInfo
//                .getJSONObject("result")
//                .getString("file_path"));
//
//        TelegramFiles torrentFile = TelegramFiles.builder()
//                .telegramFileType(ApplicationFileTypes.TORRENT)
//                .user(user)
//                .filePath(filePath)
//                .fileName(fileName)
//                .creatingDate(LocalDateTime.now())
//                .build();
//
//        telegramFilesRepository.save(torrentFile);
//    }
//
//    @Transactional
//    public String uploadFileToTorrentApplication(Long userId, String path, String category) {
//        ApplicationUser user = userService.getUserByTelegramId(userId);
//        List<TelegramFiles> tgFiles = telegramFilesRepository.findByUser(user);
//        TelegramFiles tgFile = tgFiles.stream().max(Comparator.comparing(TelegramFiles::getCreatingDate)).orElseThrow(RuntimeException::new);
//
//        byte[] file = restTelegramBotService.downloadFileFromTelegram(tgFile.getFilePath());
//
//        qbTorrentService.downloadTorrent(file, tgFile.getFileName(), path, category);
//        return tgFile.getFileName();
//    }
}
