package ru.home.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class RestTelegramService {

    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.file-info}")
    private String fileInfoUri;
    @Value("${bot.file-storage}")
    private String fileStorageUri;
    @Value("${rest.request-timeout:1}")
    private Long requestTimeout;

    private final WebClient webClient;

    public String getFilePath(String fileId) {
        return Optional.ofNullable(getFileInfo(fileId))
                .map(json -> json.getJSONObject("result"))
                .map(json -> json.getString("file_path"))
                .orElseThrow(() -> new RuntimeException(String.format("error when try get file path by fileId=%s", fileId)));
    }

    protected JSONObject getFileInfo(String fileId) {
        String fileInfoUri = this.fileInfoUri.replace("{token}", botToken).replace("{fileId}", fileId);
        try {
            String response = webClient.get()
                    .uri(fileInfoUri)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<String>() {
                    })
                    .block(Duration.of(requestTimeout, ChronoUnit.MINUTES));

            JSONObject fileInfo = new JSONObject(response);

            if (!fileInfo.keySet().contains("ok")) {
                throw new RuntimeException("when try get fileInfo response get wrong code");
            }
            return fileInfo;
        } catch (Exception e) {
            throw new RuntimeException(String.format("error when try get file info by fileId=%s", fileId), e);
        }
    }

    protected byte[] downloadFileFromTelegram(String filePath) {
        String storageUri = fileStorageUri.replace("{token}", botToken).replace("{filePath}", filePath);
        //todo refactor bellow
        URL urlObj;
        try {
            urlObj = new URL(storageUri);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try (InputStream stream = urlObj.openStream()) {
            return stream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(urlObj.toExternalForm(), e);
        }
    }


}
