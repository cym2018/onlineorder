package xyz.cym2018.onlineorder.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 公共属性, id, 状态, 备注, 创建时间, 更新时间
 */
@MappedSuperclass
public abstract class CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column
    protected STATE state = STATE.未激活;
    @Column
    protected TYPE type;
    @Column
    protected String note;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    protected Date updateTs;

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
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

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

}
