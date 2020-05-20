package xyz.cym2018.onlineorder.inventory;

import xyz.cym2018.onlineorder.common.CommonEntity;
import xyz.cym2018.onlineorder.common.STATE;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Inventory 库存
 */
@Entity
public class Inventory  extends CommonEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Date getUpdateTs() {
        return super.getUpdateTs();
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public String getNote() {
        return super.getNote();
    }

    @Override
    public Integer getType() {
        return super.getType();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public void setNote(String note) {
        super.setNote(note);
    }

    @Override
    public STATE getState() {
        return super.getState();
    }

    @Override
    public void setState(STATE state) {
        super.setState(state);
    }


    @Override
    public void setType(Integer type) {
        super.setType(type);
    }

    @Override
    public void setUpdateTs(Date updateTs) {
        super.setUpdateTs(updateTs);
    }
}
