package com.local.carcarecenter.controller;

import com.local.carcarecenter.dto.item.ItemInputModel;
import com.local.carcarecenter.dto.item.ItemViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items")
public class ItemController {

    private final ItemService service;

    @GetMapping("/")
    public ResponseEntity<List<ItemViewModel>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemViewModel> getById(@PathVariable Integer id) throws EntityNotFoundExecution {
        var result = service.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/")
    public ResponseEntity<ItemViewModel> create(@Valid @RequestBody ItemInputModel entity) throws EntityNotFoundExecution {
        return ResponseEntity.ok().body(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemViewModel> update(@PathVariable Integer id, @Valid @RequestBody ItemInputModel entity) throws EntityNotFoundExecution {
        var result = service.update(id, entity);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) throws EntityNotFoundExecution {
        service.getById(id);
        service.delete(id);

        return ResponseEntity.ok(true);
    }
}
