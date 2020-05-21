package xyz.cym2018.onlineorder.item.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.common.TYPE;
import xyz.cym2018.onlineorder.item.Item;

@JsonPropertyOrder({"id", "username", "menu", "number", "amount", "type"})
public class ListView {
    private final Item item;

    public ListView(Item item) {
        this.item = item;
    }

    public Integer getId() {
        return item.getId();
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

    public Double getAmount() {
        return item.getNumber() * item.getMenu().getPrice();
    }

    public STATE getState() {
        return item.getState();
    }
}
