package xyz.cym2018.onlineorder.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import xyz.cym2018.onlineorder.menu.Menu;
import xyz.cym2018.onlineorder.menu.MenuService;
import xyz.cym2018.onlineorder.user.User;
import xyz.cym2018.onlineorder.user.UserService;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public String autoLogin(
            @Nullable @CookieValue("username") String cUsername,
            @Nullable @CookieValue("password") String cPassword,
            @Nullable @SessionAttribute("username") String sUsername
    ) {
        if (sUsername != null || (userService.login(cUsername, cPassword))) {
            return "main";
        }
        return "login";
    }

    /**
     * 初始化数据
     *
     * @return debug页面
     */
    @RequestMapping("/debug")
    public String debug() {
        if (userService.findAll().size() == 0) {
            userService.save(new User("admin", "admin", 0));
            userService.save(new User("root", "root", 0));
            userService.save(new User("worker", "worker", 1));
            userService.save(new User("customer", "customer", 2));

            Menu menu = new Menu();
            menu.setName("超甜甜王西瓜果切/500ml");
            menu.setNote("650ml哦");
            menu.setPrice(2.79);
            menu.setState(STATE.ACTIVE);
            menuService.save(menu);

            menu = new Menu();
            menu.setName("香酥烧饼");
            menu.setNote("主要原料:面粉");
            menu.setPrice(2.0);
            menu.setState(STATE.ACTIVE);
            menuService.save(menu);

            menu = new Menu();
            menu.setName("淮南牛肉汤");
            menu.setNote("汤料丰富,正宗淮南食材.需醋包请单点或备注");
            menu.setPrice(13.0);
            menu.setDetail("主要原料:粉丝,豆腐皮,香菜,香葱,牛肉");
            menu.setState(STATE.ACTIVE);
            menuService.save(menu);

            menu = new Menu();
            menu.setName("济南甜沫");
            menu.setNote("纯正的老济南味道");
            menu.setPrice(4.0);
            menu.setState(STATE.PASSIVE);
            menuService.save(menu);
        }
        return "debug";
    }

}
