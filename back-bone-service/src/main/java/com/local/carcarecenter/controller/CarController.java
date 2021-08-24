package com.local.carcarecenter.controller;

import com.local.carcarecenter.dto.car.CarInputModel;
import com.local.carcarecenter.dto.car.CarShortViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cars")
public class CarController {

    private final CarService service;

    @GetMapping()
    public ResponseEntity<List<CarShortViewModel>> getAllPaged(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy) {
        return ResponseEntity.ok().body(service.getAllPaged(sortBy, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarShortViewModel> getById(@PathVariable Long id) throws EntityNotFoundExecution {
        var result = service.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() throws EntityNotFoundExecution {
        var result = service.getCount();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/")
    public ResponseEntity<CarShortViewModel> create(@Valid @RequestBody CarInputModel entity) throws EntityNotFoundExecution {
        return ResponseEntity.ok().body(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarShortViewModel> update(@PathVariable Long id, @Valid @RequestBody CarInputModel entity) throws EntityNotFoundExecution {
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
