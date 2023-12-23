package ru.home.model.disk.base;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {

    private LocalDateTime createDate;
    private String deviceSystemName;
    private String modelName;
    private String interfaceSpeed;
    private String serialNumber;
    private String rotationalSpeed;
    private String capacity;
    private String hostId;
    private String deviceStatus;
}
