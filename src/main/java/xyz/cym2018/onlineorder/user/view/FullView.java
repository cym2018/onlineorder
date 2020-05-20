package xyz.cym2018.onlineorder.user.view;

import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.user.User;

import java.util.Date;

public class FullView {
    final private User user;

    public FullView(User user) {
        this.user = user;
    }

    public String getMail() {
        return user.getMail();
    }

    public String getPhone() {
        return user.getPhone();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public STATE getState() {
        return user.getState();
    }

    public String getNote() {
        return user.getNote();
    }

    public Integer getId() {
        return user.getId();
    }

    public Integer getType() {
        return user.getType();
    }

    public String getGender() {
        return user.getGender();
    }

    public String getName() {
        return user.getName();
    }

    public Integer getAge() {
        return user.getAge();
    }
}
