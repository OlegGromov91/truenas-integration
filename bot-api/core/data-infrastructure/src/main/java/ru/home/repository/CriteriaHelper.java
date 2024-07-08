package ru.home.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.home.criteria.base.AbstractMongoCriteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Component
class CriteriaHelper {

    protected final Query getEmptyQuery() {
        return new Query();
    }

    protected final <C extends AbstractMongoCriteria<?>> Query getQuery(C criteria, Function<C, Criteria> function) {
        Query query = getEmptyQuery();
        Criteria mongoCriteria = function.apply(criteria);
        return query.addCriteria(mongoCriteria);
    }

    protected final <C extends AbstractMongoCriteria<?>> Query getLimitQuery(C criteria, int limit) {
        Query query = getEmptyQuery();
        query.limit(limit);
        query.with(Sort.by(criteria.getDirection().getDirection(), getFieldsName(criteria)));
        return query;
    }

    //todo replace by criteria api
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
                } finally {
                    field.setAccessible(false);
                }
            }
        }
        return mongoCriteria;
    }

    protected final <C extends AbstractMongoCriteria<?>> String[] getFieldsName(C criteria) {
        Field[] fields = criteria.getClass().getDeclaredFields();
        List<String> fieldsName = new ArrayList<>();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            if (isFieldNonNull(criteria, field)) {
                fieldsName.add(field.getName());
            }
            field.setAccessible(false);
        });
        return fieldsName.toArray(String[]::new);
    }

    private <C extends AbstractMongoCriteria<?>> boolean isFieldNonNull(C criteria, Field field) {
        try {
            return Objects.nonNull(field.get(criteria));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
