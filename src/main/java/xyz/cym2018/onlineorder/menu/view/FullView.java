package xyz.cym2018.onlineorder.menu.view;

import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.menu.Menu;

public class FullView {
    private final Menu menu;

    public FullView(Menu menu) {
        this.menu = menu;
    }

    public Double getPrice() {
        return menu.getPrice();
    }

    public String getName() {
        return menu.getName();
    }

    public STATE getState() {
        return menu.getState();
    }

    public String getNote() {
        return menu.getNote();
    }

    public Integer getId() {
        return menu.getId();
    }

    public Integer getType() {
        return menu.getType();
    }

    public String getDetail() {
        return menu.getDetail();
    }
}
