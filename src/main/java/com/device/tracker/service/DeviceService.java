package com.device.tracker.service;

import com.device.tracker.domain.Device;
import com.device.tracker.exception.ApplicationException;

public interface DeviceService {

    Device add(final Device device) throws ApplicationException;
    Device update(final Device device) throws ApplicationException;
    Device findDeviceByHardwareId(final long hardwareId) throws ApplicationException;
    Device findDeviceByName(final String name) throws ApplicationException;
}
