package com.local.carcarecenter.service.repair;

import com.local.carcarecenter.dto.repair.RepairInputModel;
import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.service.AbstractBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepairService extends AbstractBaseService<RepairInputModel, RepairViewModel> {
    List<RepairViewModel> getAllByCarId(Long carId) throws EntityNotFoundExecution;
}
