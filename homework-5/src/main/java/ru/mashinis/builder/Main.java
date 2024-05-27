package ru.mashinis.builder;

public class Main {
    public static void main(String[] args) {

        Product product = Product.builder()
                .id(1)
                .title("Название продукта")
                .description("Описание продукта")
                .cost(99.99)
                .weight(500)
                .width(10)
                .length(20)
                .height(30)
                .build();

        System.out.println(product);
    }
}
