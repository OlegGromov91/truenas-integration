package ru.home.model.files;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.home.model.files.type.ApplicationFileType;
import ru.home.model.user.ApplicationUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION_FILES")
@DiscriminatorValue(value = "TelegramFile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TelegramFile extends ApplicationFile {

    @Column(name = "TELEGRAM_FILE_TYPE")
    @Enumerated(value = EnumType.STRING)
    private ApplicationFileType telegramFileType;

    @ManyToOne
    private ApplicationUser user;

    @Column(name = "FILE_PATH", nullable = false)
    private String filePath;

    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "CREATING_DATE", nullable = false)
    private LocalDateTime creatingDate;
}
