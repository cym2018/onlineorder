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

    public List<Item> findByUser(User user) {
        return itemRepository.findByUser(user);
    }

    public List<Item> findCartByUser(User user) {
        return itemRepository.findByUserAndState(user, 未支付);
    }

    public List<Item> findHistoryByUser(User user) {
        return itemRepository.findByUserAndStateNot(user, 未支付);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findByUserAndMenu(User user, Menu menu) {
        return itemRepository.findByUserAndMenu(user, menu);
    }

    public void clearCart(User user) {
        List<Item> list = itemRepository.findByUserAndState(user, 未支付);
        list.forEach(itemRepository::delete);
    }
    public void finished(Item item){
        item.setState(已完成);
        itemRepository.save(item);
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
    public boolean remove(Item item) {
        try {
            itemRepository.delete(item);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void cancel(Item item){
        item.setState(撤销);
        itemRepository.save(item);
    }
    public double backMoney(Item item){
        item.setState(退款);
        itemRepository.save(item);
        return item.getNumber()*item.getMenu().getPrice();
    }
    public boolean doBuy(User user, Menu menu, Integer number) {
        // 检查用户
        if ((!user.getState().equals(STATE.激活)) || (!user.getType().equals(TYPE.顾客))) {
            return false;
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
                    return false;
                }
            } else {
                // 撤销购买,检查订单是否充足
                if (item.getNumber() < number) {
                    return false;
                }
            }
            menu.setNumber(menu.getNumber() + number);
            menuService.save(menu);
        }
        // 修改数据
        item.setNumber(item.getNumber() + number);
        // 如果操作结果使购买数量为0,删除购买记录
        item = itemRepository.save(item);
        if (item.getNumber() == 0) {
            itemRepository.delete(item);
        }
        return true;
    }

    public Item findByUserAndMenuAndState(User user, Menu menu, STATE state) {
        return itemRepository.findByUserAndMenuAndState(user, menu, state);
    }


//    /**
//     * 创建订单
//     *
//     * @param user   操作用户
//     * @param menu   购买商品
//     * @param number 数量
//     * @throws Exception 库存或操作用户错误
//     */
//    @Transactional(rollbackOn = Exception.class)
//    public void createOrders(User user, Menu menu, Integer number) throws Exception {
//        // 检查用户权限
//        if (!checkUser(user)) {
//            throw new Exception("账号未激活或非顾客账号");
//        }
//        // 检查库存
//        if (!checkInventory(menu, number)) {
//            throw new Exception("库存不足");
//        }
//        // 扣除库存
//        if (menu.getNumber() >= number) {
//            menu.setNumber(menu.getNumber() - number);
//            menuService.save(menu);
//        }
//        // 创建订单
//        itemRepository.save(new Item(user, menu, number));
//    }
//
//    public void editOrders(User user, Menu menu, Integer number) {
//        if (number > 0) {
//            if (checkInventory(menu, number)) {
//                changeInventory(menu, number);
//            }
//            // 增加购买量
//        } else {
//            // 减少购买量
//            changeInventory(menu, number);
//        }
//    }
//
//    /**
//     * 检查用户状态
//     *
//     * @param user 操作用户
//     * @return 是否允许购买
//     */
//    private boolean checkUser(User user) {
//        if (!user.getType().equals(TYPE.顾客)) {
//            return false;
//        }
//        if (!user.getState().equals(STATE.激活)) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 库存正常,购买数量正常
//     *
//     * @param menu   购买的商品
//     * @param number 购买的数量
//     * @return 是否允许购买
//     */
//    private boolean checkInventory(Menu menu, Integer number) {
//        if (!menu.getState().equals(STATE.激活)) {
//            return false;
//        }
//        if (number <= 0) {
//            return false;
//        }
//        if (menu.getNumber() == -1) {
//            return true;
//        }
//        if (menu.getNumber() >= number) {
//            return true;
//        }
//        return false;
//    }
//
//    private void changeInventory(Menu menu, Integer number) {
//        if (menu.getNumber() == -1) {
//            return;
//        }
//        menu.setNumber(menu.getNumber() + number);
//        menuService.save(menu);
//    }
}
