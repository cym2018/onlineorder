package xyz.cym2018.onlineorder.item;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.cym2018.onlineorder.common.STATE;
import xyz.cym2018.onlineorder.menu.Menu;
import xyz.cym2018.onlineorder.user.User;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    boolean existsByUserAndMenuAndState(User user, Menu menu, STATE state);

    Item findByUserAndMenuAndState(User user, Menu menu, STATE state);
}
