package ru.home.model.files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.home.model.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "APPLICATION_FILE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "APPLICATION_FILE_TYPE")
@SuperBuilder
@NoArgsConstructor
public abstract class ApplicationFile extends AbstractEntity {

}
