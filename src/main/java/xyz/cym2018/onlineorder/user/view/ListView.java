package xyz.cym2018.onlineorder.user.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import xyz.cym2018.onlineorder.user.User;

@JsonPropertyOrder({"id", "username", "name", "gender", "age", "type"})
public class ListView {
    final private User user;

    public ListView(User user) {
        this.user = user;
    }

    public Integer getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
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

    public String getType() {
        switch (user.getType()) {
            case 0:
                return "管理员";
            case 1:
                return "员工";
            case 2:
                return "顾客";
            default:
                return user.getType().toString();
        }
    }
}
