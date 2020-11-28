package ru.job4j.design.lsp.storage;

import ru.job4j.design.lsp.storage.model.Food;
import ru.job4j.design.lsp.storage.storages.Shop;
import ru.job4j.design.lsp.storage.storages.Storage;
import ru.job4j.design.lsp.storage.storages.Trash;
import ru.job4j.design.lsp.storage.storages.Warehouse;

import java.util.List;

public class ControlQuality {

    /**
     * Storages
     */
    private final List<Storage> storages;

    public ControlQuality(Shop shop, Warehouse warehouse, Trash trash) {
        this.storages = List.of(shop, warehouse, trash);
    }

    public void distribute(Food food) {
        for (Storage storage : storages) {
            if (storage.accept(food)){
                storage.add(food);
            }
        }
    }
}