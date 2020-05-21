package xyz.cym2018.onlineorder.menu.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import xyz.cym2018.onlineorder.menu.Menu;

@JsonPropertyOrder({"id", "name", "price", "number", "note"})
public class CustomView {
    private final Menu menu;

    public CustomView(Menu menu) {
        this.menu = menu;
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


}
