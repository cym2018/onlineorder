package xyz.cym2018.onlineorder.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.cym2018.onlineorder.common.EntityController;

@RequestMapping("/user")
@RestController
public class UserController implements EntityController<User> {
    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    @RequestMapping("/findAll")
    public String findAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userService.findAll());
    }

    @Override
    @RequestMapping("/{id}")
    public String findById(@PathVariable("id") User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userService.toFullView(user));
    }

    @Override
    @RequestMapping("/listView/findAll")
    public String findAllListView() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userService.toListView(userService.findAll()));
    }

    @Override
    @RequestMapping("/remove/{id}")
    public String removeById(@PathVariable("id") User user) {
        return userService.remove(user) ? "成功" : "失败";
    }

    @RequestMapping("/save")
    public String save(User user) {
        try {
            userService.save(user);
        } catch (Exception e) {
            return "失败";
        }
        return "成功";
    }
}
