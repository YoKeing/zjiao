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
@TableName("xuanke")
public class Xuanke extends Model<Xuanke> {

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
         @TableField(exist=false)
         private Users teacher;
        /**
     * 课程
     */
         @TableField("kid")
    private Integer kid;
         @TableField(exist=false)
         private Kecheng kecheng;
        /**
     * 记录时间
     */
         @TableField("optime")
    private String optime;
         
         @TableField(exist=false)
         private String uname;
         
         @TableField(exist=false)
         private String tname;
         
         @TableField(exist=false)
         private String kname;

        /**
     * 结课成绩
     */
         @TableField("score")
    private String score;

        /**
     * 备注
     */
         @TableField("remark")
    private String remark;

        /**
     * 删除标记
     */
         @TableField("isdel")
    private Integer isdel;


    public Integer getId() {
        return id;
    }

    public Xuanke setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Xuanke setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public Integer getTid() {
        return tid;
    }

    public Xuanke setTid(Integer tid) {
        this.tid = tid;
        return this;
    }

    public Integer getKid() {
        return kid;
    }

    public Xuanke setKid(Integer kid) {
        this.kid = kid;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Xuanke setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public String getScore() {
        return score;
    }

    public Xuanke setScore(String score) {
        this.score = score;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Xuanke setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Xuanke setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Xuanke{" +
        "id=" + id +
        ", uid=" + uid +
        ", tid=" + tid +
        ", kid=" + kid +
        ", optime=" + optime +
        ", score=" + score +
        ", remark=" + remark +
        ", isdel=" + isdel +
        "}";
    }

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Kecheng getKecheng() {
		return kecheng;
	}

	public void setKecheng(Kecheng kecheng) {
		this.kecheng = kecheng;
	}

	public Users getTeacher() {
		return teacher;
	}

	public void setTeacher(Users teacher) {
		this.teacher = teacher;
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
