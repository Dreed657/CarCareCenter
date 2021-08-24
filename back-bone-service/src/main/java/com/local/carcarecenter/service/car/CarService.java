package com.local.carcarecenter.service.car;

import com.local.carcarecenter.dto.car.CarInputModel;
import com.local.carcarecenter.dto.car.CarShortViewModel;
import com.local.carcarecenter.service.AbstractBaseService;
import org.springframework.stereotype.Service;

@Service
public interface CarService extends AbstractBaseService<CarInputModel, CarShortViewModel> {
}
