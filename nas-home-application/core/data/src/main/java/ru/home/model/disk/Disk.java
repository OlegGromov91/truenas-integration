package ru.home.model.disk;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.home.model.base.AbstractEntity;
import ru.home.model.disk.base.Device;
import ru.home.model.disk.base.Smart;
import ru.home.model.disk.base.TemperatureDeviceHistory;
import ru.home.model.disk.type.DiskFormFactorType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DISK")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Disk extends AbstractEntity {

    @Embedded
    private Device device;
    @Embedded
    private Smart smart;
    @Enumerated(EnumType.STRING)
    private DiskFormFactorType formFactor;

    @OneToMany
    @JoinTable(name = "disk_temp_device_history",
            joinColumns = @JoinColumn(name = "DISK_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEMP_HISTORY_ID"))
    private List<TemperatureDeviceHistory> temperatureDeviceHistories;


}
