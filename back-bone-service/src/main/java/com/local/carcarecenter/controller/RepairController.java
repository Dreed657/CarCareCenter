package com.local.carcarecenter.controller;

import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.dto.repair.RepairInputModel;
import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.service.repair.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/repairs")
public class RepairController {

    private final RepairService service;

    @GetMapping()
    public ResponseEntity<List<RepairViewModel>> getAllPaged(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortBy) {
        return ResponseEntity.ok().body(service.getAllPaged(sortBy, page, size));
    }


    @GetMapping("/{id}")
    public ResponseEntity<RepairViewModel> getById(@PathVariable Long id) throws EntityNotFoundExecution {
        var result = service.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/")
    public ResponseEntity<RepairViewModel> create(@Valid @RequestBody RepairInputModel entity) throws EntityNotFoundExecution {
        return ResponseEntity.ok().body(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairViewModel> update(@PathVariable Long id, @Valid @RequestBody RepairInputModel entity) throws EntityNotFoundExecution {
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
