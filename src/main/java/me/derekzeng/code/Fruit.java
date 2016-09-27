package me.derekzeng.code;


public enum Fruit {

    Apple("Apple", 123),
    Orange("Orange", 123);

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    Fruit() {
    }

    Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
