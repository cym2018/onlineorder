package xyz.cym2018.onlineorder.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import xyz.cym2018.onlineorder.user.User;
import xyz.cym2018.onlineorder.user.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/")
    public String autoLogin(
            @Nullable @CookieValue("username") String cUsername,
            @Nullable @CookieValue("password") String cPassword,
            @Nullable @SessionAttribute("username") String sUsername
    ) {
        if(sUsername==null){

        }
        if (sUsername != null || (userService.login(cUsername, cPassword))) {
            return "main";
        }
        return "login";
    }

    /**
     * @return debug页面
     * @description 初始化数据
     */
    @RequestMapping("/debug")
    public String debug() {
        if (userService.findAll().size() == 0) {
            userService.save(new User("admin", "admin", 0));
            userService.save(new User("root", "root", 0));
            userService.save(new User("worker", "worker", 1));
            userService.save(new User("customer", "customer", 2));
        }
        return "debug";
    }

}
