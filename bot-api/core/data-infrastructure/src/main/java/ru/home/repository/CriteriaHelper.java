package ru.home.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.home.criteria.base.AbstractMongoCriteria;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.Function;

@Component
class CriteriaHelper {

    protected final <C extends AbstractMongoCriteria<?>> Query getQuery(C criteria, Function<C, Criteria> function) {
        Query query = new Query();
        Criteria mongoCriteria = function.apply(criteria);
        return query.addCriteria(mongoCriteria);
    }

    protected final <C extends AbstractMongoCriteria<?>> Criteria convertToIsCriteria(C criteria) {
        Field[] fields = criteria.getClass().getDeclaredFields();
        Criteria mongoCriteria = new Criteria();
        for (Field field : fields) {
            field.setAccessible(true);
            if (isFieldNonNull(criteria, field)) {
                try {
                    mongoCriteria = mongoCriteria.and(field.getName()).is(field.get(criteria));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            field.setAccessible(false);
        }
        return mongoCriteria;
    }

    private <C extends AbstractMongoCriteria<?>> boolean isFieldNonNull(C criteria, Field field) {
        try {
            return Objects.nonNull(field.get(criteria));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
