package cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Abyss
 */
@TableName("jiaocai")
public class Jiaocai extends Model<Jiaocai> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
         private Integer id;

        /**
     * 用户
     */
         @TableField("name")
         private String name;

        /**
     * 合同内容
     */
         @TableField("content")
         private String content;

        /**
     * 时间
     */
         @TableField("optime")
         private String optime;

        /**
     * 删除标记
     */
        @TableField("isdel")
        private Integer isdel;


    public Integer getId() {
        return id;
    }

    public Jiaocai setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Jiaocai setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Jiaocai setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Jiaocai setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Jiaocai setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Jiaocai{" +
        "id=" + id +
        ", name=" + name +
        ", content=" + content +
        ", optime=" + optime +
        ", isdel=" + isdel +
        "}";
    }
}
