package ru.home.utils.calculator.information;

import ru.home.utils.calculator.base.Formatter;
import ru.home.view.qbTorrent.SizeView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import static ru.home.utils.calculator.information.MultipleByteUnits.*;

@Slf4j
@Component
public class InformationFormatter implements Formatter {

    public double convertByteToKilobyte(@NotNull Number byteValue) {
        return doFormat(byteValue.doubleValue() / KILOBYTE.getSize());
    }

    public double convertByteToMegabyte(@NotNull Number byteValue) {
        return doFormat(byteValue.doubleValue() / MEGABYTE.getSize());
    }

    public double convertByteToGigabyte(@NotNull Number byteValue) {
        return doFormat(byteValue.doubleValue() / GIGABYTE.getSize());
    }

    public double convertByteToTerabyte(@NotNull Number byteValue) {
        return doFormat(byteValue.doubleValue() / TERABYTE.getSize());
    }

    public SizeView autoConvert(@NotNull Number byteValue) {
        if (byteValue.longValue() <= KILOBYTE_MAX_VALUE) {
            return buildSize(KILOBYTE, convertByteToKilobyte(byteValue));
        }
        if (byteValue.longValue() <= MEGABYTE_MAX_VALUE) {
            return buildSize(MEGABYTE, convertByteToMegabyte(byteValue));
        }
        if (byteValue.longValue() <= GIGABYTE_MAX_VALUE) {
            return buildSize(GIGABYTE, convertByteToGigabyte(byteValue));
        }
        if (byteValue.longValue() > GIGABYTE_MAX_VALUE) {
            return buildSize(TERABYTE, convertByteToTerabyte(byteValue));
        }
        return buildSize(BYTE, doFormat(byteValue.doubleValue()));
    }

    private final SizeView buildSize(MultipleByteUnits units, Double size) {
        return SizeView.builder()
                .size(size)
                .unit(units.getMetric())
                .build();
    }

}
