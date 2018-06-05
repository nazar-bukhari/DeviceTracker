package com.device.tracker.service;

import com.device.tracker.domain.Position;
import com.device.tracker.exception.ApplicationException;

import java.util.List;

public interface PositionService {

    List<Position> getPosition(long deviceId) throws ApplicationException;
    Position add(final Position position) throws ApplicationException;
}
