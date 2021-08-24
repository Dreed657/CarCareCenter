package com.local.carcarecenter.service.car;

import com.local.carcarecenter.dto.car.CarInputModel;
import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.model.Car;
import com.local.carcarecenter.repository.CarRepository;
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
public class CarServiceImpl implements CarService {

    private final CarRepository cars;

    private final ModelMapper mapper;

    @Override
    public Long getCount() {
        return this.cars.count();
    }

    @Override
    public CarViewModel getById(Long id) throws EntityNotFoundExecution {
        var entity = this.cars.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Car was not found!"));

        return this.mapper.map(result, CarViewModel.class);
    }

    @Override
    public List<CarViewModel> getAll() {
        return this.cars
                .findAll()
                .stream()
                .map(c -> this.mapper.map(c, CarViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarViewModel> getAllPaged(Optional<String> sortBy, Optional<Integer> page, Optional<Integer> size) {
        return this.cars.findAll(PageRequest.of(
                        page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.ASC, sortBy.orElse("id")))
                .stream()
                .map(c -> this.mapper.map(c, CarViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarViewModel save(CarInputModel model) {
        var entity = new Car();

        entity.setVIN(model.getVIN());
        entity.setManufacturer(model.getManufacturer());
        entity.setModel(model.getModel());
        entity.setYear(model.getYear());
        entity.setType(model.getType());

        this.cars.save(entity);

        return this.mapper.map(entity, CarViewModel.class);
    }

    @Override
    public CarViewModel update(Long id, CarInputModel model) throws EntityNotFoundExecution {
        if (model == null) {
            throw new IllegalArgumentException("You must provide an valid model!");
        }

        var entity = this.cars.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Car was not found!"));

        result.setVIN(model.getVIN());
        result.setType(model.getType());
        result.setYear(model.getYear());
        result.setModel(model.getModel());
        result.setManufacturer(model.getManufacturer());

        this.cars.save(result);

        return this.mapper.map(result, CarViewModel.class);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundExecution {
        var entity = this.cars.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Car was not found!"));

        this.cars.delete(result);
        return true;
    }
}
