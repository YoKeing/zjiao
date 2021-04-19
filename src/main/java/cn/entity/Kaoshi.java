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
@TableName("kaoshi")
public class Kaoshi extends Model<Kaoshi> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 老师
     */
         @TableField("tid")
    private Integer tid;
         @TableField(exist=false)
         private Users teacher;
        /**
     * 标题
     */
         @TableField("name")
    private String name;

        /**
     * 内容
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

    public Kaoshi setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTid() {
        return tid;
    }

    public Kaoshi setTid(Integer tid) {
        this.tid = tid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Kaoshi setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Kaoshi setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Kaoshi setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Kaoshi setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Kaoshi{" +
        "id=" + id +
        ", tid=" + tid +
        ", name=" + name +
        ", content=" + content +
        ", optime=" + optime +
        ", isdel=" + isdel +
        "}";
    }

	public Users getTeacher() {
		return teacher;
	}

	public void setTeacher(Users teacher) {
		this.teacher = teacher;
	}
    
}
