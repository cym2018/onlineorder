package xyz.cym2018.onlineorder.user.view;

import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.common.TYPE;
import xyz.cym2018.onlineorder.user.User;

/**
 * 管理员查看用户信息
 */
public class AdminView {
    private final User user;

    public AdminView(User user) {
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

    public STATE getState() {
        return user.getState();
    }

    public String getNote() {
        return user.getNote();
    }

    public Integer getId() {
        return user.getId();
    }

    public TYPE getType() {
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
