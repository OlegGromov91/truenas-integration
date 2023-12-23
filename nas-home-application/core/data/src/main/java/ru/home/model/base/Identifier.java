package ru.home.model.base;

import java.io.Serializable;

public interface Identifier extends Serializable {

    <T> T getId();

}
