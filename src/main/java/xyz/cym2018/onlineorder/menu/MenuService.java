package xyz.cym2018.onlineorder.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.cym2018.onlineorder.common.CommonService;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.item.ItemService;
import xyz.cym2018.onlineorder.menu.view.CustomView;
import xyz.cym2018.onlineorder.menu.view.ListView;
import xyz.cym2018.onlineorder.user.User;
import xyz.cym2018.onlineorder.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService implements CommonService<Menu> {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findByStateNot(STATE.删除);
    }

    public List<Menu> findAllActive() {
        return menuRepository.findByState(STATE.激活);
    }

    @Override
    public List<Object> toListView(List<Menu> menus) {
        List<Object> viewList = new ArrayList<>();
        menus.forEach(o -> viewList.add(new ListView(o)));
        return viewList;
    }

    public List<Object> toCustomView(List<Menu> menus, User user) {
        List<Object> viewList = new ArrayList<>();
        menus.forEach(o -> viewList.add(new CustomView(o, user, itemService)));
        return viewList;
    }

    public void setNotActive(Menu menu) {
        menu.setState(STATE.未激活);
        menuRepository.save(menu);
    }

    public void setActive(Menu menu) {
        menu.setState(STATE.激活);
        menuRepository.save(menu);
    }


    @Override
    public boolean remove(Menu menu) {
        try {
            menu.setState(STATE.删除);
            menuRepository.save(menu);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
