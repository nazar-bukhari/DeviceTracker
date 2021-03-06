package com.device.tracker.service.Impl;

import com.device.tracker.domain.Device;
import com.device.tracker.domain.Position;
import com.device.tracker.exception.ApplicationException;
import com.device.tracker.exception.NoDomainFoundException;
import com.device.tracker.repository.DeviceRepository;
import com.device.tracker.repository.PositionRepository;
import com.device.tracker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Position> getPosition(final long organizationId) throws ApplicationException {
//        return positionRepository.findTop2ByDeviceIdAndLiveTrueOrderByReceiveTimeDesc(organizationId);
        List<Position> livePositionList = new LinkedList<>();
        List<Device> deviceList = deviceRepository.findByOrganizationId(organizationId);

        if(deviceList.isEmpty()){
            throw new NoDomainFoundException("No location found for this organization");
        }

        for(Device device: deviceList){

            Position position = positionRepository.findTopByDeviceIdOrderByReceiveTimeDesc(device.getId());
            if(position != null && position.isLive()) {
                livePositionList.add(position);
            }
        }

        return livePositionList;
    }

    @Override
    public Position add(Position position) throws ApplicationException {

        verifyPosition(position);
        return positionRepository.saveAndFlush(position);
    }

    private void verifyPosition(Position position) throws ApplicationException {

        if(position == null){
            throw new NoDomainFoundException("No location found to lookup.");
        }
    }
}
