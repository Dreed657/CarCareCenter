package com.local.carcarecenter.service.item;

import com.local.carcarecenter.dto.item.ItemInputModel;
import com.local.carcarecenter.dto.item.ItemViewModel;
import com.local.carcarecenter.model.Item;
import com.local.carcarecenter.repository.ItemRepository;
import com.local.carcarecenter.repository.RepairRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final RepairRepository repairs;

    private final ItemRepository items;

    private final ModelMapper mapper;

    @Override
    public ItemViewModel getById(Integer id) {
        var entity = this.items.findById(id);
        var result = entity.orElseThrow(() -> new RuntimeException("Item was not found!"));

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
    public ItemViewModel save(ItemInputModel model) {
        var repair = this.repairs.findById(model.getRepairId());
        var repairResult = repair.orElseThrow(() -> new RuntimeException("Repair was not found!"));

        var item = new Item();

        item.setDescription(model.getDescription());
        item.setMetric(model.getMetric());
        item.setRepair(repairResult);

        this.items.save(item);

        return this.mapper.map(item, ItemViewModel.class);
    }

    @Override
    public ItemViewModel update(Integer id, ItemInputModel model) {
        var entity = this.items.findById(id);
        var result = entity.orElseThrow(() -> new RuntimeException("Item was not found!"));

        result.setMetric(model.getMetric());
        result.setDescription(model.getDescription());

        this.items.save(result);

        return this.mapper.map(result, ItemViewModel.class);
    }

    @Override
    public boolean delete(Integer id) {
        var entity = this.items.findById(id);
        var result = entity.orElseThrow(() -> new RuntimeException("Item was not found!"));

        this.items.delete(result);

        return true;
    }
}
