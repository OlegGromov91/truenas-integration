package ru.home.criteria.base;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum DirectionSorting {

    ASC(Sort.Direction.ASC),
    DESC(Sort.Direction.DESC);

    private final Sort.Direction direction;

    DirectionSorting(Sort.Direction direction) {
        this.direction = direction;
    }
}
