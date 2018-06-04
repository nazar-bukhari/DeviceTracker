package com.device.tracker.service;

import com.device.tracker.domain.Position;
import com.device.tracker.exception.ApplicationException;

public interface PositionService {

    Position getPosition() throws ApplicationException;
    Position add(final Position position) throws ApplicationException;
}
