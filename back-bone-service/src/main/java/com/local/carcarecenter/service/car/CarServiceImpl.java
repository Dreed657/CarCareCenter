package com.local.carcarecenter.service.car;

import com.local.carcarecenter.dto.car.CarInputModel;
import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.model.Car;
import com.local.carcarecenter.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    private final ModelMapper mapper;

    @Override
    public CarViewModel getById(String id) {
        var entity = this.repository.findById(id);
        entity.orElseThrow(() -> new RuntimeException("Car was not found!"));

        return this.mapper.map(entity, CarViewModel.class);
    }

    @Override
    public List<CarViewModel> getAll() {
        return this.repository
                .findAll()
                .stream()
                .map(c -> this.mapper.map(c, CarViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarViewModel create(CarInputModel model) {
        var entity = this.mapper.map(model, Car.class);

//        this.repository.save(entity);

        return this.mapper.map(entity, CarViewModel.class);
    }

    @Override
    public CarViewModel update(String id, CarInputModel model) {
        var entity = this.repository.findById(id);
        var result = entity.orElseThrow(() -> new RuntimeException("Car was not found!"));

        result.setVIN(model.getVIN());
        result.setType(model.getType());
        result.setYear(model.getYear());
        result.setModel(model.getModel());
        result.setManufacturer(model.getManufacturer());

        this.repository.save(result);

        return this.mapper.map(result, CarViewModel.class);
    }

    @Override
    public boolean delete(String id) {
        var entity = this.repository.findById(id);
        entity.orElseThrow(() -> new RuntimeException("Car was not found!"));

        return true;
    }
}
