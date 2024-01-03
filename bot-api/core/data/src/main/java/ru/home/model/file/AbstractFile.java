package ru.home.model.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.home.model.base.MongoIdentifier;

import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractFile extends MongoIdentifier {
    @NotNull
    private FileType type;
    @NotNull
    private String userId;
}
