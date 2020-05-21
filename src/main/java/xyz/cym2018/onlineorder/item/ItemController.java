package xyz.cym2018.onlineorder.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.cym2018.onlineorder.common.EntityController;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.user.User;
import xyz.cym2018.onlineorder.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController implements EntityController<Item> {
    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    @RequestMapping("/findAll")
    public String findAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(itemService.findAll());
    }

    @Override
    @RequestMapping("/{id}")
    public String findById(@PathVariable("id") Item item) throws JsonProcessingException {
        return objectMapper.writeValueAsString(item);
    }

    @Override
    @RequestMapping("/listView/findAll")
    public String findAllListView() throws JsonProcessingException {
        return objectMapper.writeValueAsString(itemService.toListView(itemService.findAll()));
    }

    @Override
    @RequestMapping("/remove/{id}")
    public String removeById(@PathVariable("id") Item item) {
        return itemService.remove(item) ? "成功" : "失败";
    }

    @RequestMapping("/save")
    public String save(Item item) {
        try {
            itemService.save(item);
        } catch (Exception e) {
            return "失败";
        }
        return "成功";
    }

    @RequestMapping("/CustomView/cart")
    public String customViewCart(@CookieValue("username") String username) throws JsonProcessingException {
        User user = userService.findByUsername(username);
        return objectMapper.writeValueAsString(itemService.toCustomView(itemService.findCartByUser(user)));
    }

    @RequestMapping("/CustomView/history")
    public String customViewHistory(@CookieValue("username") String username) throws JsonProcessingException {
        User user = userService.findByUsername(username);
        return objectMapper.writeValueAsString(itemService.toCustomView(itemService.findHistoryByUser(user)));
    }

    @RequestMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") Item item) {
        itemService.cancel(item);
        return "成功";
    }

    @RequestMapping("/clearCart")
    public String clearCart(@CookieValue("username") String username) {
        User user = userService.findByUsername(username);
        itemService.clearCart(user);
        return "成功";
    }

    @RequestMapping("/pay")
    public String pay(@CookieValue("username") String username) {
        User user = userService.findByUsername(username);
        List<Item> list = itemService.findCartByUser(user);
        double sum = list.stream().mapToDouble(o -> {
            return o.getNumber() * o.getMenu().getPrice();
        }).sum();
        list.forEach(o -> {
            o.setState(STATE.已支付);
            itemService.save(o);
        });
        return Double.toString(sum);
    }

    @RequestMapping("/finished/{id}")
    public String finished(@PathVariable("id") Item item) {
        itemService.finished(item);
        return "成功";
    }

    @RequestMapping("/backMoney/{id}")
    public Double backMoney(@PathVariable("id") Item item) {
        return itemService.backMoney(item);
    }

}
