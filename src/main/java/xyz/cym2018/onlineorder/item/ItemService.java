package xyz.cym2018.onlineorder.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.cym2018.onlineorder.common.CommonService;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.common.TYPE;
import xyz.cym2018.onlineorder.item.view.ListView;
import xyz.cym2018.onlineorder.menu.Menu;
import xyz.cym2018.onlineorder.menu.MenuService;
import xyz.cym2018.onlineorder.user.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements CommonService<Item> {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MenuService menuService;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Object> toListView(List<Item> orders) {
        List<Object> viewList = new ArrayList<>();
        orders.forEach(o -> viewList.add(new ListView(o)));
        return viewList;
    }

    @Override
    public boolean remove(Item item) {
        try {
            itemRepository.delete(item);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional(rollbackOn = Exception.class)
    public String createOrders(User user, Menu menu, Integer number) throws Exception {
        // 查询操作权限:顾客账号且激活状态
        if (!user.getType().equals(TYPE.顾客) || !user.getState().equals(STATE.ACTIVE)) {
            throw new Exception("账号未激活或非顾客账号");
        }
        // 查询库存
        if (menu.getNumber() != -1 && menu.getNumber() < number) {
            throw new Exception("库存不足");
        }
        if (menu.getNumber() >= number) {
            // 库存有限
            menu.setNumber(menu.getNumber() - number);
            menuService.save(menu);
        }
        itemRepository.save(new Item(user, menu, number));
        return "成功";
    }
}
