package xyz.cym2018.onlineorder.item;

import xyz.cym2018.onlineorder.common.CommonEntity;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.common.TYPE;
import xyz.cym2018.onlineorder.menu.Menu;
import xyz.cym2018.onlineorder.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * 订单
 */
@Entity
public class Item extends CommonEntity {
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @OneToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    public Item(User user, Menu menu, Integer number) {
        this.user = user;
        this.menu = menu;
        this.number = number;
    }

    @Column
    private Integer number;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public STATE getState() {
        return super.getState();
    }

    @Override
    public void setState(STATE state) {
        super.setState(state);
    }

    @Override
    public String getNote() {
        return super.getNote();
    }

    @Override
    public void setNote(String note) {
        super.setNote(note);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public Date getUpdateTs() {
        return super.getUpdateTs();
    }

    @Override
    public void setUpdateTs(Date updateTs) {
        super.setUpdateTs(updateTs);
    }

    @Override
    public TYPE getType() {
        return super.getType();
    }

    @Override
    public void setType(TYPE type) {
        super.setType(type);
    }
}
