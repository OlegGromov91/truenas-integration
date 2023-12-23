package ru.home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.model.files.TelegramFile;
import ru.home.model.user.ApplicationUser;

import java.util.List;

@Repository
public interface TelegramFilesRepository extends CrudRepository<TelegramFile, Long> {

    List<TelegramFile> findByUser(ApplicationUser user);

}
