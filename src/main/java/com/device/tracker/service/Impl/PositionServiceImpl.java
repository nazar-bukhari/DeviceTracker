package com.device.tracker.service.Impl;

import com.device.tracker.domain.Position;
import com.device.tracker.exception.ApplicationException;
import com.device.tracker.exception.NoDomainFoundException;
import com.device.tracker.repository.PositionRepository;
import com.device.tracker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;


    @Override
    public List<Position> getPosition(final long deviceId) throws ApplicationException {
        return positionRepository.findTop2ByDeviceIdOrderByReceiveTimeDesc(deviceId);
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
