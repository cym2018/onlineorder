package xyz.cym2018.onlineorder.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.cym2018.onlineorder.common.CommonService;
import xyz.cym2018.onlineorder.user.view.ListView;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements CommonService<User> {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        Class<User> i = User.class;
        return userRepository.findAll();
    }

    public List<ListView> toListView(List<User> userList) {
        List<ListView> viewList = new ArrayList<>();
        userList.forEach(o -> viewList.add(new ListView(o)));
        return viewList;
    }
}
