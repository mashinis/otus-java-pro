package ru.mashinis.builder;

public final class Product {
    private final int id;
    private final String title;
    private final String description;
    private final double cost;
    private final int weight;
    private final int width;
    private final int length;
    private final int height;

    public static Builder builder() {
        return new Builder();
    }

    private Product(Builder builder) {
        id = builder.id;
        title = builder.title;
        description = builder.description;
        cost = builder.cost;
        weight = builder.weight;
        width = builder.width;
        length = builder.length;
        height = builder.height;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                ", width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }

    public static final class Builder {
        private int id;
        private String title;
        private String description;
        private double cost;
        private int weight;
        private int width;
        private int length;
        private int height;

        private Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder cost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
