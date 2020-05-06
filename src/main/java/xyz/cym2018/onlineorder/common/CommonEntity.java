package xyz.cym2018.onlineorder.common;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 所有表都包含的公共属性, id, 状态, 备注, 创建时间, 更新时间
 */
@MappedSuperclass
public abstract class CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column
    protected Integer state;
    @Column
    protected Integer type;
    @Column
    protected String note;
    @CreationTimestamp
    protected Date createTs;
    @UpdateTimestamp
    protected Date updateTs;

    public Integer getState() {
        if (state == null)
            return 0;
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }
}
