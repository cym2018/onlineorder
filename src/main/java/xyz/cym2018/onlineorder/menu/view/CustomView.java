package xyz.cym2018.onlineorder.menu.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.item.ItemService;
import xyz.cym2018.onlineorder.menu.Menu;
import xyz.cym2018.onlineorder.user.User;

@JsonPropertyOrder({"id", "name", "price", "number", "note", "buyNumber"})
public class CustomView {
    private final Menu menu;
    private final User user;

    private final ItemService itemService;

    public CustomView(Menu menu, User user, ItemService itemService) {
        this.menu = menu;
        this.user = user;
        this.itemService = itemService;
    }


    public Integer getId() {
        return menu.getId();
    }

    public String getName() {
        return menu.getName();
    }

    public Double getPrice() {
        return menu.getPrice();
    }

    public String getNumber() {
        if (menu.getNumber() != -1)
            return menu.getNumber().toString();
        return "无限";

    }

    public String getNote() {
        return menu.getNote();
    }

    public String getBuyNumber() {
        try {
            return itemService.findByUserAndMenuAndState(user, menu, STATE.未支付).getNumber().toString();
        } catch (Exception e) {
            return "0";
        }
    }

}
