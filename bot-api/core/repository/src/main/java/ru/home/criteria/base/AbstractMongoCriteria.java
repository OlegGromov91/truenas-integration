package ru.home.criteria.base;

import lombok.Getter;
import lombok.Setter;
import ru.home.model.base.MongoIdentifier;

@Getter
@Setter
public abstract class AbstractMongoCriteria<T extends MongoIdentifier> {

    public abstract Class<T> getType();
}
