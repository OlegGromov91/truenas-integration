package ru.home.criteria.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.home.model.base.MongoIdentifier;

@Getter
@Setter
@SuperBuilder
public abstract class AbstractMongoCriteria<T extends MongoIdentifier> {

    @Builder.Default
    private DirectionSorting direction = DirectionSorting.ASC;

    public abstract Class<T> getType();
}
