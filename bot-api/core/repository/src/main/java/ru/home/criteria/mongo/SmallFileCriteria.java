package ru.home.criteria.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.home.criteria.base.AbstractMongoCriteria;
import ru.home.model.file.FileType;
import ru.home.model.file.SmallFile;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class SmallFileCriteria extends AbstractMongoCriteria<SmallFile> {

    private String id;
    private FileType type;
    private String userId;
    private String filePath;
    private String fileName;
    private LocalDateTime creatingDate;

    @Override
    public Class<SmallFile> getType() {
        return SmallFile.class;
    }

}
