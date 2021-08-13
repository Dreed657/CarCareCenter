package com.local.carcarecenter.service.item;

import com.local.carcarecenter.dto.item.ItemInputModel;
import com.local.carcarecenter.dto.item.ItemViewModel;
import com.local.carcarecenter.service.AbstractBaseService;
import org.springframework.stereotype.Service;

@Service
public interface ItemService extends AbstractBaseService<ItemInputModel, ItemViewModel> {
}
