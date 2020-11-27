package ru.job4j.design.lsp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.model.Bean;
import ru.job4j.design.lsp.storage.Shop;
import ru.job4j.design.lsp.storage.Trash;
import ru.job4j.design.lsp.storage.Warehouse;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControlQualityTest {
    private final static double TRASH = 1;
    private final static double DISCOUNT = 0.75;
    private final static double SHOP = 0.25;
    private final static double WAREHOUSE = 0;
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
        controlQuality.manage(beanD);
        controlQuality.manage(beanW);
        controlQuality.manage(beanS);
        controlQuality.manage(beanT);
        assertTrue(warehouse.contains(beanW));
        assertTrue(trash.contains(beanT));
        assertTrue(shop.contains(beanS));
        assertTrue(shop.contains(beanD));
        assertThat(beanD.getPrice(), is(newPrice));
    }

    @Test
    public void countTest() {
        assertThat(controlQuality.regularCase(1000, 2000, 1100), is (0.1));
    }

    @Test
    public void roundTest() {
        assertThat(controlQuality.rounding(0), is (0.0));
        assertThat(controlQuality.rounding(0.1), is (0.0));
        assertThat(controlQuality.rounding(0.2), is (0.0));
        assertThat(controlQuality.rounding(0.3), is (0.25));
        assertThat(controlQuality.rounding(0.4), is (0.25));
        assertThat(controlQuality.rounding(0.5), is (0.25));
        assertThat(controlQuality.rounding(0.6), is (0.25));
        assertThat(controlQuality.rounding(0.7), is (0.25));
        assertThat(controlQuality.rounding(0.8), is (0.75));
        assertThat(controlQuality.rounding(0.9), is (0.75));
        assertThat(controlQuality.rounding(1), is (1.0));
        assertThat(controlQuality.rounding(2), is (1.0));
    }

    @Test
    public void calculateExpiryTest() {
        Bean beanW = new Bean("q", stopWarehouse, start, 1, 0.12);
        Bean beanS = new Bean("q", stopShop, start, 1, 0.12);
        Bean beanD = new Bean("q", stopDiscount, start, 1, 0.1);
        Bean beanT = new Bean("q", stopTrash, start, 1, 0.12);
        assertThat(controlQuality.rounding(controlQuality.calculateExpiry(beanD)), is (DISCOUNT));
        assertThat(controlQuality.rounding(controlQuality.calculateExpiry(beanS)), is (SHOP));
        assertThat(controlQuality.rounding(controlQuality.calculateExpiry(beanW)), is (WAREHOUSE));
        assertThat(controlQuality.rounding(controlQuality.calculateExpiry(beanT)), is (TRASH));
    }
}
