package xyz.cym2018.onlineorder.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.cym2018.onlineorder.common.CommonService;
import xyz.cym2018.onlineorder.user.view.AdminView;
import xyz.cym2018.onlineorder.user.view.FullView;
import xyz.cym2018.onlineorder.user.view.ListView;
import xyz.cym2018.onlineorder.user.view.SelfView;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements CommonService<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        Class<User> i = User.class;
        return userRepository.findAll();
    }

    @Override
    public boolean remove(User user) {
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean login(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }


    public List<Object> toListView(List<User> userList) {
        List<Object> viewList = new ArrayList<>();
        userList.forEach(o -> viewList.add(new ListView(o)));
        return viewList;
    }

    public List<Object> toAdminView(List<User> userList) {
        List<Object> viewList = new ArrayList<>();
        userList.forEach(o -> viewList.add(new AdminView(o)));
        return viewList;
    }

    public Object toSelfView(User user) {
        return new SelfView(user);
    }

    public Object toFullView(User user) {
        return new FullView(user);
    }
}
