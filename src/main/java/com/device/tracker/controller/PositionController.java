package com.device.tracker.controller;

import com.device.tracker.domain.Position;
import com.device.tracker.exception.ApplicationException;
import com.device.tracker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(path = "/{deviceId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private Position getLastPosition(final @PathVariable long deviceId) throws ApplicationException {
        return positionService.getPosition(deviceId);
    }

    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private Position addPosition(final @RequestBody Position position) throws ApplicationException {
        return positionService.add(position);
    }

}
