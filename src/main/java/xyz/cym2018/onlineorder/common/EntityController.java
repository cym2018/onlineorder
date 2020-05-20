package xyz.cym2018.onlineorder.common;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EntityController<T extends CommonEntity> {
    String findAll() throws JsonProcessingException;

    String findById(T t) throws JsonProcessingException;

    String findAllListView() throws JsonProcessingException;

    String removeById(T t);
}
