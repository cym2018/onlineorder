package xyz.cym2018.onlineorder.item.view;

import xyz.cym2018.onlineorder.item.Item;

public class ListView {
    private final Item item;

    public ListView(Item item) {
        this.item = item;
    }

    public String getUsername() {
        return item.getUser().getUsername();
    }

    public String getMenu() {
        return item.getMenu().getName();
    }

    public Integer getNumber() {
        return item.getNumber();
    }
}
