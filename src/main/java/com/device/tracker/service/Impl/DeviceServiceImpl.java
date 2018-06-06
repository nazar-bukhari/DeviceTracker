package com.device.tracker.service.Impl;

import com.device.tracker.domain.Device;
import com.device.tracker.exception.ApplicationException;
import com.device.tracker.exception.DuplicateDomainFoundException;
import com.device.tracker.exception.NoDomainFoundException;
import com.device.tracker.repository.DeviceRepository;
import com.device.tracker.service.DeviceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Log LOG = LogFactory.getLog(DeviceServiceImpl.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device add(Device device) throws ApplicationException {

        LOG.debug("Trying to save a new Device: " + device.getName());

        verifyDevice(device);

//        if(findDeviceByName(device.getName()) != null){
//            throw new DuplicateDomainFoundException("Device under same name already exists");
//        }

        if(findDeviceByHardwareId(device.getHardwareId()) != null){
            throw new DuplicateDomainFoundException("Device under same hardwareId already exists");
        }

        Device createdDevice = deviceRepository.saveAndFlush(device);
        LOG.debug(String.format("Created new device '%s': ",createdDevice.getName()));

        return createdDevice;
    }

    @Override
    public Device update(Device device) throws ApplicationException {

        verifyDevice(device);

        Device deviceToUpdate = deviceRepository.findById(device.getId()).orElse(null);

        if(deviceToUpdate == null){
            throw new NoDomainFoundException(String.format("No Device with name %s found to update",device.getName()));
        }

        return deviceRepository.saveAndFlush(device);
    }

    @Override
    public List<Device> getAllDevice() throws ApplicationException {
        return null;
    }

    @Override
    public Device findDeviceByHardwareId(long hardwareId) throws ApplicationException {
        return deviceRepository.findByHardwareId(hardwareId);
    }

    @Override
    public Device findDeviceByName(String name) throws ApplicationException {

        return deviceRepository.findByName(name);
    }

    private void verifyDevice(Device device) throws ApplicationException {

        if(device == null){
            throw new NoDomainFoundException("No device provided for lookup");
        }
    }
}
