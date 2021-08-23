package com.local.carcarecenter.service.repair;

import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.dto.repair.RepairInputModel;
import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.model.Repair;
import com.local.carcarecenter.repository.CarRepository;
import com.local.carcarecenter.repository.RepairRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {

    private final CarRepository cars;

    private final RepairRepository repairs;

    private final ModelMapper mapper;

    @Override
    public RepairViewModel getById(Long id) throws EntityNotFoundExecution {
        var entity = this.repairs.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Repair was not found!"));

        return this.mapper.map(result, RepairViewModel.class);
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
    public List<RepairViewModel> getAllPaged(Optional<String> sortBy, Optional<Integer> page, Optional<Integer> size) {
        return this.repairs.findAll(PageRequest.of(
                        page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.ASC, sortBy.orElse("id")))
                .stream()
                .map(c -> this.mapper.map(c, RepairViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RepairViewModel save(RepairInputModel model) throws EntityNotFoundExecution {
        var car = this.cars.findById(model.getCarId());
        var carResult = car.orElseThrow(() -> new EntityNotFoundExecution("Car was not found!"));

        var repair = new Repair();

        repair.setMileage(model.getMileage());
        repair.setStatus(model.getStatus());
        this.repairs.save(repair);

        carResult.getRepairments().add(repair);
        this.cars.save(carResult);

        return this.mapper.map(repair, RepairViewModel.class);
    }

    @Override
    public RepairViewModel update(Long id, RepairInputModel model) throws EntityNotFoundExecution {
        var entity = this.repairs.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Repair was not found!"));

        result.setMileage(model.getMileage());
        result.setStatus(model.getStatus());

        this.repairs.save(result);

        return this.mapper.map(result, RepairViewModel.class);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundExecution {
        var entity = this.repairs.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Repair was not found!"));

        this.repairs.delete(result);

        return true;
    }
}
