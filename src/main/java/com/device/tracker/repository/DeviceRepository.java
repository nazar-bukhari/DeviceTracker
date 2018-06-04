package com.device.tracker.repository;

import com.device.tracker.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    Device findByName(final String name);
    Device findByHardwareId(final long hardwareId);
}
