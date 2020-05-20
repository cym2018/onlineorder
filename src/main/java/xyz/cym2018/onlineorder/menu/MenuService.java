package xyz.cym2018.onlineorder.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.cym2018.onlineorder.common.CommonService;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.menu.view.FullView;
import xyz.cym2018.onlineorder.menu.view.ListView;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService implements CommonService<Menu> {
    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findByStateNot(STATE.DELETE);
    }

    @Override
    public List<Object> toListView(List<Menu> menus) {
        List<Object> viewList = new ArrayList<>();
        menus.forEach(o -> viewList.add(new ListView(o)));
        return viewList;
    }

    @Override
    public boolean remove(Menu menu) {
        try {
            menuRepository.delete(menu);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
