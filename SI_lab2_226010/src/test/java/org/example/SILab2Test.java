package org.example;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
r
public class SILab2Test {

    @Test(expected = RuntimeException.class)
    public void testAllItemsNull() {
        SILab2.checkCart(null, 100);
    }

    @Test
    public void testItemWithEmptyName() {
        Item item = new Item("", "123", 100, 0.0f);
        assertTrue(SILab2.checkCart(Arrays.asList(item), 100));
        assertEquals("unknown", item.getName());
    }

    @Test(expected = RuntimeException.class)
    public void testItemWithoutBarcode() {
        Item item = new Item("Item1", null, 100, 0.0f);
        SILab2.checkCart(Arrays.asList(item), 100);
    }

    @Test(expected = RuntimeException.class)
    public void testItemWithInvalidBarcode() {
        Item item = new Item("Item1", "123a", 100, 0.0f);
        SILab2.checkCart(Arrays.asList(item), 100);
    }

    @Test
    public void testItemWithDiscountNoSpecialCondition() {
        Item item = new Item("Item1", "123", 100, 0.1f);
        assertTrue(SILab2.checkCart(Arrays.asList(item), 90));
    }

    @Test
    public void testItemWithDiscountSpecialConditionMet() {
        Item item = new Item("Item1", "0123", 400, 0.1f);
        assertTrue(SILab2.checkCart(Arrays.asList(item), 340));
    }

    @Test
    public void testMultipleItemsPaymentMatches() {
        Item item1 = new Item("Item1", "123", 100, 0.0f);
        Item item2 = new Item("Item2", "456", 200, 0.0f);
        assertTrue(SILab2.checkCart(Arrays.asList(item1, item2), 300));
    }

    @Test
    public void testMultipleItemsPaymentExceeds() {
        Item item1 = new Item("Item1", "123", 100, 0.0f);
        Item item2 = new Item("Item2", "456", 200, 0.0f);
        assertFalse(SILab2.checkCart(Arrays.asList(item1, item2), 250));
    }

    @Test
    public void testItemWithoutDiscountSpecialConditionNotMet() {
        Item item = new Item("Item1", "123", 350, 0.0f);
        assertTrue(SILab2.checkCart(Arrays.asList(item), 350));
    }

    @Test
    public void testItemWithDiscountSpecialConditionPaymentLess() {
        Item item = new Item("Item1", "0123", 400, 0.2f);
        assertFalse(SILab2.checkCart(Arrays.asList(item), 300));
    }
}
