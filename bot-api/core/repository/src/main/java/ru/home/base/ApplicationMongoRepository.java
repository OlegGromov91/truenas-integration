package ru.home.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.home.model.base.Identifier;

public interface ApplicationMongoRepository<T extends Identifier> extends MongoRepository<T, String> {

    default Class<T> getType() {
        return null;
    }

}
