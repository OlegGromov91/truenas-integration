package ru.home.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ru.home.criteria.base.AbstractMongoCriteria;
import ru.home.model.base.MongoIdentifier;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RepositoryCriteriaFactory {

    private final MongoTemplate template;
    private final CriteriaHelper helper;

    public <T extends MongoIdentifier, C extends AbstractMongoCriteria<T>> List<T> search(C criteria) {
        return template.find(helper.getQuery(criteria, helper::convertToIsCriteria), criteria.getType());
    }

    public <T extends MongoIdentifier, C extends AbstractMongoCriteria<T>> Optional<T> searchOne(C criteria) {
        return Optional.ofNullable(template.findOne(helper.getQuery(criteria, helper::convertToIsCriteria), criteria.getType()));
    }

    public <T extends MongoIdentifier, C extends AbstractMongoCriteria<T>> T searchOrThrow(C criteria) {
        return searchOne(criteria)
                .orElseThrow(() -> new RuntimeException(String.format("Сущность с типом %s не найдена, критерии поиска: %s", criteria.getType(), criteria)));
    }


}
