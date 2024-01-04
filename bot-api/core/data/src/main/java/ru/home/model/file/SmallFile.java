package ru.home.model.file;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "small_file")
public class SmallFile extends AbstractFile {

    private Binary file;
    @NotNull
    private String filePath;
    @NotNull
    private String fileName;
    @NotNull
    @Builder.Default
    private LocalDateTime creatingDate = LocalDateTime.now();

    public static Binary toBinary(byte[] data) {
        return new Binary(BsonBinarySubType.BINARY, data);
    }

}
