package com.device.tracker.controller;

import com.device.tracker.domain.Device;
import com.device.tracker.exception.ApplicationException;
import com.device.tracker.service.Impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceServiceImpl deviceService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Device addDevice(final @RequestBody Device device) throws ApplicationException {

        return deviceService.add(device);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Device updateDevice(final @RequestBody Device device) throws ApplicationException {
        return deviceService.update(device);
    }
}
