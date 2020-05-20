package xyz.cym2018.onlineorder.user.view;

import xyz.cym2018.onlineorder.user.User;

/**
 * 用户查看自己的信息
 */
public class SelfView {
    private final User user;

    public SelfView(User user) {
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

    public String getNote() {
        return user.getNote();
    }

    public Integer getId() {
        return user.getId();
    }

    public String getPassword() {
        return user.getPassword();
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
