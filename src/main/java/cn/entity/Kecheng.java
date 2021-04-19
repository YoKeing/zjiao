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
@TableName("kecheng")
public class Kecheng extends Model<Kecheng> {

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
     * 课程类型
     */
         @TableField("type")
    private String type;

        /**
     * 课程名称
     */
         @TableField("name")
    private String name;

        /**
     * 上课教室
     */
         @TableField("cname")
    private String cname;

        /**
     * 课程介绍
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

    public Kecheng setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTid() {
        return tid;
    }

    public Kecheng setTid(Integer tid) {
        this.tid = tid;
        return this;
    }

    public String getType() {
        return type;
    }

    public Kecheng setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Kecheng setName(String name) {
        this.name = name;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public Kecheng setCname(String cname) {
        this.cname = cname;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Kecheng setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Kecheng setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Kecheng setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Kecheng{" +
        "id=" + id +
        ", tid=" + tid +
        ", type=" + type +
        ", name=" + name +
        ", cname=" + cname +
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
