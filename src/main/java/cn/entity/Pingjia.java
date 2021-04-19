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
@TableName("pingjia")
public class Pingjia extends Model<Pingjia> {

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
     * 学生
     */
         @TableField("uid")
    private Integer uid;
         @TableField(exist=false)
         private Users users;
         @TableField("kid")
         private Integer kid;
         @TableField(exist=false)
         private Kecheng kecheng;
         
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

		/**
     * 老师
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

         @TableField(exist=false)
         private String uname;
         
         @TableField(exist=false)
         private String tname;
         
         @TableField(exist=false)
         private String kname;
    public Integer getId() {
        return id;
    }

    public Pingjia setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTid() {
        return tid;
    }

    public Pingjia setTid(Integer tid) {
        this.tid = tid;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Pingjia setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pingjia setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Pingjia setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Pingjia setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Pingjia setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pingjia{" +
        "id=" + id +
        ", tid=" + tid +
        ", uid=" + uid +
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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
