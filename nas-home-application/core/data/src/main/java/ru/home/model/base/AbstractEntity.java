package ru.home.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class AbstractEntity implements Identifier {

    @Id
    @SequenceGenerator(name = "JPWH_SEQUENCE", initialValue = 1000, sequenceName = "JPWH_SEQUENCE")
    @GeneratedValue(generator = "JPWH_SEQUENCE", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
