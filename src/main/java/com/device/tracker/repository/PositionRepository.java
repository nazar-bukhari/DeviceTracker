package com.device.tracker.repository;

import com.device.tracker.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    List<Position> findTop2ByDeviceIdAndLiveTrueOrderByReceiveTimeDesc(long id);
}
