package ru.home.model.disk.base;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Smart {

    private String temperature;
    private String powerOnHours;
}
