package com.local.carcarecenter.service.repair;

import com.local.carcarecenter.dto.repair.RepairInputModel;
import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.service.AbstractBaseService;
import org.springframework.stereotype.Service;

@Service
public interface RepairService extends AbstractBaseService<RepairInputModel, RepairViewModel> {
}
