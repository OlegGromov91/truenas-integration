package ru.home;

import ru.home.base.ApplicationMongoRepository;
import ru.home.model.file.SmallFile;

import java.util.Optional;

public interface SmallFileRepository extends ApplicationMongoRepository<SmallFile> {

    Optional<SmallFile> findByUserId(String userId);

    @Override
    default Class<SmallFile> getType() {
        return SmallFile.class;
    }

}
