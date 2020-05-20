package xyz.cym2018.onlineorder.menu.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.menu.Menu;

@JsonPropertyOrder({"id", "name", "price", "note", "type", "state"})
public class ListView {
    final private Menu menu;

    public ListView(Menu menu) {
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

    public String getNote() {
        return menu.getNote();
    }

    public Integer getType() {
        return menu.getType();
    }

    public STATE getState() {
        return menu.getState();
    }
}
