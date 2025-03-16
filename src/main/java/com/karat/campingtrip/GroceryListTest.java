package com.karat.campingtrip;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroceryListTest {
    static String[][] products;

    @BeforeAll
    public static void globalSetUp(){
        products = new String[][]{
                {"Cheese", "Dairy"},
                {"Carrots", "Produce"},
                {"Potatoes", "Produce"},
                {"Canned Tuna", "Pantry"},
                {"Romaine Lettuce", "Produce"},
                {"Chocolate Milk", "Dairy"},
                {"Flour", "Pantry"},
                {"Iceberg Lettuce", "Produce"},
                {"Coffee", "Pantry"},
                {"Pasta", "Pantry"},
                {"Milk", "Dairy"},
                {"Blueberries", "Produce"},
                {"Pasta Sauce", "Pantry"}
        };

    }

    @Test
    public void testNormalScenario(){
        String[] list1 = {"Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"};

    }

}