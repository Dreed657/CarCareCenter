package com.local.carcarecenter.repository;

import com.local.carcarecenter.model.Repair;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
}
