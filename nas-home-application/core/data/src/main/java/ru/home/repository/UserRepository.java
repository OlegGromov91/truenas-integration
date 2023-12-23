package ru.home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.model.user.ApplicationUser;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByTelegramUserId(Long id);
}
