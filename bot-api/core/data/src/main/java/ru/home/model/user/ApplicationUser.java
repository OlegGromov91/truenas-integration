package ru.home.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import ru.home.model.base.MongoIdentifier;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class ApplicationUser extends MongoIdentifier {

    private String name;
    @NotNull
    @Indexed(unique = true)
    private String email;

}

