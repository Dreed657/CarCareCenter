package com.local.carcarecenter.service.repair;

import com.local.carcarecenter.dto.repair.RepairInputModel;
import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.model.Repair;
import com.local.carcarecenter.repository.CarRepository;
import com.local.carcarecenter.repository.RepairRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {

    private final CarRepository cars;

    private final RepairRepository repairs;

    private final ModelMapper mapper;

    @Override
    public RepairViewModel getById(Integer id) {
        var entity = this.repairs.findById(id);
        entity.orElseThrow(() -> new RuntimeException("Repair was not found!"));

        return this.mapper.map(entity, RepairViewModel.class);
    }

    @Override
    public List<RepairViewModel> getAll() {
        return this.repairs
                .findAll()
                .stream()
                .map(c -> this.mapper.map(c, RepairViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RepairViewModel save(RepairInputModel model) {
        var car = this.cars.findById(model.getCarId());
        var carResult = car.orElseThrow(() -> new RuntimeException("Car was not found!"));

        var repair = new Repair();

        repair.setMileage(model.getMileage());
        repair.setStatus(model.getStatus());
        repair.setCar(carResult);

        this.repairs.save(repair);

        return this.mapper.map(repair, RepairViewModel.class);
    }

    @Override
    public RepairViewModel update(Integer id, RepairInputModel model) {
        var entity = this.repairs.findById(id);
        var result = entity.orElseThrow(() -> new RuntimeException("Repair was not found!"));

        result.setMileage(model.getMileage());
        result.setStatus(model.getStatus());

        this.repairs.save(result);

        return this.mapper.map(result, RepairViewModel.class);
    }

    @Override
    public boolean delete(Integer id) {
        var entity = this.repairs.findById(id);
        var result = entity.orElseThrow(() -> new RuntimeException("Repair was not found!"));

        this.repairs.delete(result);

        return true;
    }
}
