package com.local.carcarecenter.controller;

import com.local.carcarecenter.dto.car.CarInputModel;
import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cars")
public class CarController {

    private final CarService service;

    @GetMapping("/")
    public ResponseEntity<List<CarViewModel>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarViewModel> getById(@PathVariable Long id) throws EntityNotFoundExecution {
        var result = service.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/")
    public ResponseEntity<CarViewModel> create(@Valid @RequestBody CarInputModel entity) throws EntityNotFoundExecution {
        return ResponseEntity.ok().body(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarViewModel> update(@PathVariable Long id, @Valid @RequestBody CarInputModel entity) throws EntityNotFoundExecution {
        var result = service.update(id, entity);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws EntityNotFoundExecution {
        service.getById(id);
        service.delete(id);

        return ResponseEntity.ok(true);
    }
}
