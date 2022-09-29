package com.muhammedtopgul.mockito.exercise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

/**
 * @author muhammed-topgul
 * @since 29/09/2022 22:51
 */
@DisplayName("Mockito Sample Mock Tests")
public class SampleMockWithCustomerOrderTest {
    @Test
    void addOrderToCustomer() {
        final Inventory inventoryMock = Mockito.mock(Inventory.class);
        Mockito.when(inventoryMock.isStockAvailable("Item1", "Item2"))
                .thenReturn(true);
        Mockito.when(inventoryMock.isStockAvailable("Item3"))
                .thenReturn(false);

        Customer customer = new Customer(inventoryMock);
        assertTrue(customer.addOrder(new Order(List.of("Item1", "Item2"))));
        assertFalse(customer.addOrder(new Order(List.of("Item3"))));

        Mockito.verify(inventoryMock, Mockito.times(1))
                .isStockAvailable(eq("Item1"), eq("Item2"));
        Mockito.verify(inventoryMock, Mockito.times(1))
                .isStockAvailable(eq("Item3"));
    }

    private class Customer {
        private final Inventory inventory;

        private Customer(Inventory inventory) {
            this.inventory = inventory;
        }

        public boolean addOrder(Order order) {
            return inventory.isStockAvailable(order.items.toArray(new String[]{}));
        }
    }

    private class Order {
        private final List<String> items = new ArrayList<>();

        public Order(List<String> items) {
            this.items.addAll(items);
        }

        public List<String> getItems() {
            return items;
        }
    }

    private interface Inventory {
        boolean isStockAvailable(String... items);
    }
}
