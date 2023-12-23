package ru.home.utils.converter;

import org.springframework.core.convert.converter.Converter;

public interface ApplicationConverter<S, T> extends Converter<S, T>, org.modelmapper.Converter<S, T> {

    Class<S> getSourceType();

    Class<T> getTargetType();

    T convert(S source);

}
