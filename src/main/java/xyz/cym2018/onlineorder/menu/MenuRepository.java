package xyz.cym2018.onlineorder.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.cym2018.onlineorder.common.STATE;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByStateNot(STATE state);
    List<Menu> findByState(STATE state);

}
