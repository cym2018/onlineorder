package xyz.cym2018.onlineorder.common;

import java.util.List;

public interface CommonService<T> {
    T save(T t);

    List<T> findAll();
}
