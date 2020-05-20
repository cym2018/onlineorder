package xyz.cym2018.onlineorder.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.cym2018.onlineorder.common.EntityController;
import xyz.cym2018.onlineorder.menu.view.FullView;

@RestController
@RequestMapping("/menu")
public class MenuController implements EntityController<Menu> {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MenuService menuService;

    @Override
    public String findAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(menuService.findAll());
    }


    @Override
    @RequestMapping("/{id}")
    public String findById(@PathVariable("id") Menu menu) throws JsonProcessingException {
        return objectMapper.writeValueAsString(new FullView(menu));
    }

    @Override
    @RequestMapping("/listView/findAll")
    public String findAllListView() throws JsonProcessingException {
        return objectMapper.writeValueAsString(menuService.toListView(menuService.findAll()));
    }

    @Override
    @RequestMapping("/remove/{id}")
    public String removeById(@PathVariable("id") Menu menu) {
        try {
            menuService.remove(menu);
        } catch (Exception e) {
            return "失败";
        }
        return "成功";
    }
}
