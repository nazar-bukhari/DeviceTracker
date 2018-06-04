package com.device.tracker.repository;

import com.device.tracker.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    Position findFirstByOrderByReceiveTimeDesc();
}
