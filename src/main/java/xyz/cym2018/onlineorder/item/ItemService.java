package xyz.cym2018.onlineorder.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.cym2018.onlineorder.common.CommonService;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.common.TYPE;
import xyz.cym2018.onlineorder.item.view.CustomView;
import xyz.cym2018.onlineorder.item.view.ListView;
import xyz.cym2018.onlineorder.menu.Menu;
import xyz.cym2018.onlineorder.menu.MenuService;
import xyz.cym2018.onlineorder.user.User;

import java.util.ArrayList;
import java.util.List;

import static xyz.cym2018.onlineorder.common.STATE.*;

@Service
public class ItemService implements CommonService<Item> {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MenuService menuService;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByUserAndState(User user, STATE state) {
        return itemRepository.findByUserAndState(user, state);
    }

    public List<Item> findHistoryByUser(User user) {
        return itemRepository.findByUserAndStateNot(user, 未支付);
    }

    public List<Item> findByState(STATE state) {
        return itemRepository.findByState(state);
    }

    @Override
    public List<Object> toListView(List<Item> orders) {
        List<Object> viewList = new ArrayList<>();
        orders.forEach(o -> viewList.add(new ListView(o)));
        return viewList;
    }

    public List<Object> toCustomView(List<Item> orders) {
        List<Object> viewList = new ArrayList<>();
        orders.forEach(o -> viewList.add(new CustomView(o)));
        return viewList;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item findByUserAndMenuAndState(User user, Menu menu, STATE state) {
        return itemRepository.findByUserAndMenuAndState(user, menu, state);
    }

    public double backMoney(Item item, User user) {
        item.setOperator(user);
        item.setState(退款);
        itemRepository.save(item);
        return item.getNumber() * item.getMenu().getPrice();
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

    public void clearCart(User user) {
        List<Item> list = itemRepository.findByUserAndState(user, 未支付);
        list.forEach(itemRepository::delete);
    }

    public void finished(Item item, User user) {
        item.setState(已完成);
        item.setOperator(user);
        itemRepository.save(item);
    }

    public void cancel(Item item,User user) {
        item.setState(撤销);
        item.setOperator(user);
        itemRepository.save(item);
    }

    public void doBuy(User user, Menu menu, Integer number) {
        // 检查用户
        if ((!user.getState().equals(STATE.激活)) || (!user.getType().equals(TYPE.顾客))) {
            return;
        }
        // 检查是否存在记录
        Item item;
        if (itemRepository.existsByUserAndMenuAndState(user, menu, STATE.未支付)) {
            // 存在记录,修改
            item = itemRepository.findByUserAndMenuAndState(user, menu, STATE.未支付);
        } else {
            // 不存在记录,创建
            item = new Item(user, menu, 0);
        }
        // 检查库存
        if (menu.getNumber() != -1) {
            if (number > 0) {
                // 加订,检查库存是否充足
                if (menu.getNumber() < number) {
                    return;
                }
            } else {
                // 撤销购买,检查订单是否充足
                if (item.getNumber() < number) {
                    return;
                }
            }
            menu.setNumber(menu.getNumber() + number);
            menuService.save(menu);
        }
        // 修改数据
        item.setNumber(item.getNumber() + number);
        // 如果操作结果使购买数量为0,删除购买记录
        item.setOperator(user);
        item = itemRepository.save(item);
        if (item.getNumber() == 0) {
            itemRepository.delete(item);
        }
    }
}
