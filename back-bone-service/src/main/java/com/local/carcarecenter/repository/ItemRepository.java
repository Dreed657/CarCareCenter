package com.local.carcarecenter.repository;

import com.local.carcarecenter.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
