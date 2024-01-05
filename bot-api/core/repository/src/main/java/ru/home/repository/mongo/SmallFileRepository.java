package ru.home.repository.mongo;

import ru.home.repository.base.ApplicationMongoRepository;
import ru.home.model.file.SmallFile;

import java.util.List;

public interface SmallFileRepository extends ApplicationMongoRepository<SmallFile> {

    List<SmallFile> findByUserId(String userId);

    @Override
    default Class<SmallFile> getType() {
        return SmallFile.class;
    }

}
