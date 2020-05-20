package xyz.cym2018.onlineorder.menu;

import xyz.cym2018.onlineorder.common.CommonEntity;
import xyz.cym2018.onlineorder.common.STATE;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 菜单表
 * 状态:state
 * 0->未发布
 * 1->在用
 * 2->删除
 */
@Entity
public class Menu extends CommonEntity {
    // 名称
    @Column
    private String name;
    // 价格
    @Column
    private Double price;
    // 商品信息 -> note
    // 商品详情
    @Column
    private String detail;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
