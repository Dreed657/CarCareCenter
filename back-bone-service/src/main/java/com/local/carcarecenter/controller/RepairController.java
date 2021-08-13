package com.local.carcarecenter.controller;

import com.local.carcarecenter.dto.repair.RepairInputModel;
import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.service.repair.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/repairs")
public class RepairController {

    private final RepairService service;

    @GetMapping("/")
    public ResponseEntity<List<RepairViewModel>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairViewModel> getById(@PathVariable Integer id) {
        try {
            var result = service.getById(id);
            return ResponseEntity.ok().body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<RepairViewModel> create(@Valid @RequestBody RepairInputModel entity) {
        return ResponseEntity.ok().body(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairViewModel> update(@PathVariable Integer id, @Valid @RequestBody RepairInputModel entity) {
        try {
            var result = service.update(id, entity);
            return ResponseEntity.ok().body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        try {
            service.getById(id);
            service.delete(id);

            return ResponseEntity.ok(true);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
