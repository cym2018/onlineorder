package xyz.cym2018.onlineorder.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.cym2018.onlineorder.user.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainRestController {
    @Autowired
    HttpServletRequest request;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletResponse response;
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

}
