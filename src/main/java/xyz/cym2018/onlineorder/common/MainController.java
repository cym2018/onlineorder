package xyz.cym2018.onlineorder.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xyz.cym2018.onlineorder.item.ItemService;
import xyz.cym2018.onlineorder.menu.Menu;
import xyz.cym2018.onlineorder.menu.MenuService;
import xyz.cym2018.onlineorder.user.User;
import xyz.cym2018.onlineorder.user.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController {
    @Autowired
    HttpServletRequest request;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletResponse response;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    ItemService itemService;
    @Autowired
    private UserService adminService;

    @RequestMapping("/login")
    public String login(String username, String password) {
        boolean result = adminService.login(username, password);
        if (result) {
            response.addCookie(new Cookie("username", username));
            response.addCookie(new Cookie("password", password));
            return "登陆成功";
        }
        return "用户名或密码不正确";
    }

    /**
     * 删除登陆信息:Cookie和Session
     *
     * @return 注销成功
     */
    @RequestMapping("/logout")
    public boolean logout() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie i : cookies) {
                i.setMaxAge(0);
                response.addCookie(i);
            }
        }
        request.getSession().invalidate();
        return true;
    }

    /**
     * 生成二维码方法
     *
     * @param username,password 输入的信息
     * @param resp              response对象
     * @throws Exception 抛出异常
     */
    @RequestMapping(value = "/qrcode")
    public void createQrcode(@CookieValue("username") String username, @CookieValue("password") String password, Integer id, HttpServletResponse resp) throws Exception {
        if (!userService.login(username, password)) {
            return;
        }
        if (!userService.findByUsername(username).getType().equals(TYPE.管理员)) {
            return;
        }
        User user = userService.findById(id);
        String content = "localhost/QRLogin?username=" + user.getUsername() + "&password=" + user.getPassword();
        ServletOutputStream stream = null;
        try {
            stream = resp.getOutputStream();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            //编码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //边框距
            hints.put(EncodeHintType.MARGIN, 0);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
            MatrixToImageWriter.writeToStream(bm, "png", stream);
        } catch (WriterException e) {
            e.getStackTrace();

        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 初始化数据
     *
     * @return debug页面
     */
    @RequestMapping("/debug")
    public ModelAndView debug() {
        if (userService.findAll().size() == 0) {
            // 生成用户数据
            {
                User user = new User("admin", "admin", TYPE.管理员);
                user.setState(STATE.激活);
                userService.save(user);
                user = new User("root", "root", TYPE.管理员);
                user.setState(STATE.激活);
                userService.save(user);
                user = new User("worker", "worker", TYPE.员工);
                user.setState(STATE.激活);
                userService.save(user);
                user = new User("customer", "customer", TYPE.顾客);
                user.setState(STATE.激活);
                userService.save(user);
            }
            // 生成菜单数据
            {
                Menu menu = new Menu();
                menu.setName("超甜甜王西瓜果切/500ml");
                menu.setNote("650ml哦");
                menu.setPrice(2.79);
                menu.setState(STATE.激活);
                menuService.save(menu);

                menu = new Menu();
                menu.setName("香酥烧饼");
                menu.setNote("主要原料:面粉");
                menu.setPrice(2.0);
                menu.setState(STATE.激活);
                menuService.save(menu);

                menu = new Menu();
                menu.setName("淮南牛肉汤");
                menu.setNote("汤料丰富,正宗淮南食材.需醋包请单点或备注");
                menu.setPrice(13.0);
                menu.setDetail("主要原料:粉丝,豆腐皮,香菜,香葱,牛肉");
                menu.setState(STATE.激活);
                menuService.save(menu);

                menu = new Menu();
                menu.setName("济南甜沫");
                menu.setNote("纯正的老济南味道");
                menu.setPrice(4.0);
                menu.setState(STATE.未激活);
                menuService.save(menu);
            }
            // 生成订单数据
            {
                AtomicInteger i = new AtomicInteger(1);
                User user = userService.findByUsername("customer");
                List<Menu> menu = menuService.findAllActive();
                menu.forEach(o -> {
                    i.getAndIncrement();
                    try {
                        itemService.doBuy(user, o, i.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        return new ModelAndView("debug");
    }

    /**
     * 根据cookie 自动登陆
     *
     * @param cUsername 用户名
     * @param cPassword 密码
     * @return 视图
     */
    @RequestMapping("/")
    public ModelAndView autoLogin(
            @Nullable @CookieValue("username") String cUsername,
            @Nullable @CookieValue("password") String cPassword
    ) {
        if (cUsername == null || cPassword == null) {
            return new ModelAndView("login");
        }
        if (!userService.login(cUsername, cPassword)) {
            return new ModelAndView("login");
        }
        User user = userService.findByUsername(cUsername);
        switch (user.getType()) {
            case 员工:
                return new ModelAndView("task");
            case 顾客:
                return new ModelAndView("shop");
            case 管理员:
                return new ModelAndView("admin");
            default:
                return new ModelAndView("login");
        }
    }

    @RequestMapping("/QRLogin")
    public ModelAndView qrLogin(String username, String password) {
        if (login(username, password).equals("登陆成功")) {
            return autoLogin(username, password);
        } else {
            return new ModelAndView("login");
        }

    }
}
