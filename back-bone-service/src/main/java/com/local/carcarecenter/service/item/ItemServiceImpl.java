package com.local.carcarecenter.service.item;

import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.dto.item.ItemInputModel;
import com.local.carcarecenter.dto.item.ItemViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.model.Item;
import com.local.carcarecenter.repository.ItemRepository;
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
public class ItemServiceImpl implements ItemService {

    private final RepairRepository repairs;

    private final ItemRepository items;

    private final ModelMapper mapper;

    @Override
    public Long getCount() {
        return this.items.count();
    }

    @Override
    public ItemViewModel getById(Long id) throws EntityNotFoundExecution {
        var entity = this.items.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Item was not found!"));

        return this.mapper.map(result, ItemViewModel.class);
    }

    @Override
    public List<ItemViewModel> getAll() {
        return this.items
                .findAll()
                .stream()
                .map(c -> this.mapper.map(c, ItemViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemViewModel> getAllPaged(Optional<String> sortBy, Optional<Integer> page, Optional<Integer> size) {
        return this.items.findAll(PageRequest.of(
                        page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.ASC, sortBy.orElse("id")))
                .stream()
                .map(c -> this.mapper.map(c, ItemViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel save(ItemInputModel model) throws EntityNotFoundExecution {
        var repair = this.repairs.findById(model.getRepairId());
        var repairResult = repair.orElseThrow(() -> new EntityNotFoundExecution("Repair was not found!"));

        var item = new Item();

        item.setDescription(model.getDescription());
        item.setQuantity(model.getQuantity());
        item.setPrice(model.getPrice());
        item.setMetric(model.getMetric());
        this.items.save(item);

        repairResult.getItems().add(item);
        this.repairs.save(repairResult);

        return this.mapper.map(item, ItemViewModel.class);
    }

    @Override
    public ItemViewModel update(Long id, ItemInputModel model) throws EntityNotFoundExecution {
        var entity = this.items.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Item was not found!"));

        result.setMetric(model.getMetric());
        result.setPrice(model.getPrice());
        result.setDescription(model.getDescription());

        this.items.save(result);

        return this.mapper.map(result, ItemViewModel.class);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundExecution {
        var entity = this.items.findById(id);
        var result = entity.orElseThrow(() -> new EntityNotFoundExecution("Item was not found!"));

        this.items.delete(result);

        return true;
    }
}
