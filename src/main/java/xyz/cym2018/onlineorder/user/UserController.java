package xyz.cym2018.onlineorder.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping("/findAll")
    public String findAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userService.findAll());
    }

    @RequestMapping("/listView/findAll")
    public String findAllListView() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userService.toListView(userService.findAll()));
    }
    @RequestMapping("/{id}")
    public String findById(@PathVariable("id")User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(user);
    }

}
