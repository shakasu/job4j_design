package ru.job4j.design.lsp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.storage.ControlQuality;
import ru.job4j.design.lsp.storage.model.Bean;
import ru.job4j.design.lsp.storage.storages.Shop;
import ru.job4j.design.lsp.storage.storages.Trash;
import ru.job4j.design.lsp.storage.storages.Warehouse;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControlQualityTest {
    Shop shop;
    Trash trash;
    Warehouse warehouse;
    ControlQuality controlQuality;
    Calendar start;
    Calendar now;
    Calendar stopWarehouse;
    Calendar stopShop;
    Calendar stopDiscount;
    Calendar stopTrash;

    @Before
    public void setUp() {
        shop = new Shop();
        trash = new Trash();
        warehouse = new Warehouse();
        controlQuality = new ControlQuality(shop, warehouse, trash);
        now = Calendar.getInstance();
        start = new GregorianCalendar(2020, Calendar.JANUARY, 1);
        stopDiscount = new GregorianCalendar(2020, Calendar.DECEMBER, 1);
        stopShop = new GregorianCalendar(2021, Calendar.JULY, 1);
        stopWarehouse = new GregorianCalendar(2030, Calendar.JANUARY, 3);
        stopTrash = new GregorianCalendar(2020, Calendar.NOVEMBER, 1);
    }

    @Test
    public void manage() {
        int price = 100;
        double discount = 0.68;
        double newPrice = 68.0;
        Bean beanW = new Bean("q", stopWarehouse, start, 1, 0.12);
        Bean beanS = new Bean("q", stopShop, start, 1, 0.12);
        Bean beanD = new Bean("q", stopDiscount, start, price, discount);
        Bean beanT = new Bean("q", stopTrash, start, 1, 0.12);
        controlQuality.distribute(beanD);
        controlQuality.distribute(beanW);
        controlQuality.distribute(beanS);
        controlQuality.distribute(beanT);
        assertTrue(warehouse.contains(beanW));
        assertTrue(trash.contains(beanT));
        assertTrue(shop.contains(beanS));
        assertTrue(shop.contains(beanD));
        assertThat(beanD.getPrice(), is(newPrice));
    }
}
