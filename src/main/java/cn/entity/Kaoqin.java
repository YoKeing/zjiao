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
@TableName("kaoqin")
public class Kaoqin extends Model<Kaoqin> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 学生
     */
         @TableField("uid")
    private Integer uid;
         @TableField(exist=false)
         private Users users;
        /**
     * 老师
     */
         @TableField("tid")
    private Integer tid;
         
         @TableField("kid")
         private Integer kid;
         @TableField(exist=false)
         private Kecheng kecheng;
        /**
        /**
     * 用户名
     */
         @TableField("name")
    private String name;

        /**
     * 记录时间
     */
         @TableField("optime")
    private String optime;

        /**
     * 删除标记
     */
         @TableField("isdel")
    private Integer isdel;

         @TableField(exist=false)
         private String uname;
         
         @TableField(exist=false)
         private String tname;
         
         @TableField(exist=false)
         private String kname;

    public Integer getId() {
        return id;
    }

    public Kaoqin setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Kaoqin setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public Integer getTid() {
        return tid;
    }

    public Kaoqin setTid(Integer tid) {
        this.tid = tid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Kaoqin setName(String name) {
        this.name = name;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Kaoqin setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Kaoqin setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Kaoqin{" +
        "id=" + id +
        ", uid=" + uid +
        ", tid=" + tid +
        ", name=" + name +
        ", optime=" + optime +
        ", isdel=" + isdel +
        "}";
    }

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getKid() {
		return kid;
	}

	public void setKid(Integer kid) {
		this.kid = kid;
	}

	public Kecheng getKecheng() {
		return kecheng;
	}

	public void setKecheng(Kecheng kecheng) {
		this.kecheng = kecheng;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getKname() {
		return kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}
    
}
