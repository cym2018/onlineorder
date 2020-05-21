package xyz.cym2018.onlineorder.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.cym2018.onlineorder.common.EntityController;

@RestController
@RequestMapping("/item")
public class ItemController implements EntityController<Item> {
    @Autowired
    ItemService itemService;
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
}
