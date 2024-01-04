package ru.home.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.home.base.ApplicationMongoRepository;
import ru.home.model.base.Identifier;

import javax.annotation.PostConstruct;
import java.util.*;

import static java.util.Objects.isNull;

//todo mb genericTypeResolver?
@Component
public class RepositoryFactory {

    @Autowired
    @Lazy
    private Set<? extends ApplicationMongoRepository<? extends Identifier>> mongoRepositories = new HashSet<>();
    private final Map<Class<? extends Identifier>, ApplicationMongoRepository<? extends Identifier>> repositoryMap = new HashMap<>();

    @PostConstruct
    protected void init() {
        mongoRepositories.forEach(repository -> repositoryMap.put(repository.getType(), repository));
    }

    public <I extends Identifier> I save(I entity) {
        return (I) getRepository(entity.getClass()).save(entity);
    }

    public <I extends Identifier> Optional<I> findById(String id, Class<I> entityClass) {
        return getRepository(entityClass).findById(id);
    }

    public <I extends Identifier> boolean existsById(String id, Class<I> entityClass) {
        return getRepository(entityClass).existsById(id);
    }

    public <I extends Identifier> List<I> findAll(Class<I> entityClass) {
        return getRepository(entityClass).findAll();
    }

    public <I extends Identifier> Iterable<I> findAllById(Class<I> entityClass, Iterable<String> ids) {
        return (Iterable<I>) getRepository(entityClass).findAllById(ids);
    }

    public <I extends Identifier> long count(Class<I> entityClass) {
        return getRepository(entityClass).count();
    }

    public <I extends Identifier> void deleteById(Class<I> entityClass, String id) {
        getRepository(entityClass).deleteById(id);
    }

    public <I extends Identifier> void delete(I entity) {
        getRepository(entity.getClass()).delete(entity.getId());
    }

    public <I extends Identifier> void deleteAllById(Class<I> entityClass, Iterable<String> ids) {
        getRepository(entityClass).deleteAllById(ids);
    }

    public <I extends Identifier> void deleteAll(Class<I> entityClass, Iterable<I> entities) {
        getRepository(entityClass).deleteAll(entities);
    }

    public <I extends Identifier> void deleteAll(Class<I> entityClass) {
        getRepository(entityClass).deleteAll();
    }

    public <I extends Identifier> List<I> saveAll(List<I> entities) {
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return getRepository(entities.stream().findFirst().get().getClass()).saveAll(entities);
    }

    public <I extends Identifier> List<I> findAll(Class<I> entityClass, Sort sort) {
        return getRepository(entityClass).findAll(sort);
    }

    public <I extends Identifier> I insert(I entity) {
        return (I) getRepository(entity.getClass()).insert(entity);
    }

    public <I extends Identifier> List<I> insert(Collection<I> entities) {
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return (List<I>) getRepository(entities.stream().findFirst().get().getClass()).insert(entities);
    }

    public <I extends Identifier> List<I> findAll(Class<I> entityClass, Example<I> example) {
        return getRepository(entityClass).findAll(example);
    }

    public <I extends Identifier> List<I> findAll(Class<I> entityClass, Example<I> example, Sort sort) {
        return getRepository(entityClass).findAll(example, sort);
    }

    public <I extends Identifier, T extends ApplicationMongoRepository> T getRepository(Class<I> entityClass) {
        if (isNull(entityClass)) {
            throw new UnsupportedOperationException("entityClass is null");
        }
        return Optional.ofNullable(repositoryMap.get(entityClass))
                .map(repository -> (T) repository)
                .orElseThrow(() -> new UnsupportedOperationException(String.format("cannot find repository for %s method getType() should be overridden in repo", entityClass)));
    }


}
