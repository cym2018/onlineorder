package xyz.cym2018.onlineorder.item.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.item.Item;

import java.util.Date;

@JsonPropertyOrder({"id", "username", "menu", "number", "amount", "state", "time","operator"})
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

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getTime() {
        return item.getUpdateTs();
    }
    public String getOperator() {
        return item.getOperator().getUsername();
    }
}
