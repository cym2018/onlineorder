package xyz.cym2018.onlineorder.user;

import xyz.cym2018.onlineorder.common.CommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @description 人员相关公共属性, 姓名, 性别, 年龄, 身份证号
 */
@Entity
public class User extends CommonEntity {
    @Column
    protected String gender;
    @Column
    protected String name;
    @Column
    protected Integer age;
    @Column(unique = true)
    protected String username;
    @Column
    protected String password;

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public User(String username, String password, Integer type) {
        setUsername(username);
        setPassword(password);
        setType(type);
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Integer getState() {
        return super.getState();
    }

    @Override
    public void setState(Integer state) {
        super.setState(state);
    }

    @Override
    public String getNote() {
        return super.getNote();
    }

    @Override
    public void setNote(String note) {
        super.setNote(note);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public Date getCreateTs() {
        return super.getCreateTs();
    }

    @Override
    public void setCreateTs(Date createTs) {
        super.setCreateTs(createTs);
    }

    @Override
    public Date getUpdateTs() {
        return super.getUpdateTs();
    }

    @Override
    public void setUpdateTs(Date updateTs) {
        super.setUpdateTs(updateTs);
    }

    @Override
    public Integer getType() {
        return super.getType();
    }

    @Override
    public void setType(Integer type) {
        super.setType(type);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
