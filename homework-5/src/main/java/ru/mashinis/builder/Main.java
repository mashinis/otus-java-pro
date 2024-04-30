package ru.mashinis.builder;

public class Main {
    public static void main(String[] args) {

        Product product = Product.builder()
                .setId(1)
                .setTitle("Название продукта")
                .setDescription("Описание продукта")
                .setCost(99.99)
                .setWeight(500)
                .setWidth(10)
                .setLength(20)
                .setHeight(30)
                .build();

        System.out.println(product);
    }
}
