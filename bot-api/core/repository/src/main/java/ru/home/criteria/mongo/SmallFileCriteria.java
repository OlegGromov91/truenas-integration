package ru.home.criteria.mongo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.home.criteria.base.AbstractMongoCriteria;
import ru.home.criteria.base.DirectionSorting;
import ru.home.model.file.FileType;
import ru.home.model.file.SmallFile;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@ToString
public class SmallFileCriteria extends AbstractMongoCriteria<SmallFile> {

    private String id;
    private FileType type;
    private String userId;
    private String filePath;
    private String fileName;
    private LocalDateTime creatingDate;

    public static SmallFileCriteria sortByCreatingDate() {
        return SmallFileCriteria.builder()
                .creatingDate(LocalDateTime.now())
                .build();
    }

    @Override
    public Class<SmallFile> getType() {
        return SmallFile.class;
    }

}
