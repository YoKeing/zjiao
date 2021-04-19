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
@TableName("shijian")
public class Shijian extends Model<Shijian> {

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
         @TableField(exist=false)
         private String uname;
         
         @TableField(exist=false)
         private String tname;
         
         @TableField(exist=false)
         private String kname;
        /**
     * 老师
     */
         @TableField("tid")
    private Integer tid;

        /**
     * 课程
     */
         @TableField("kid")
    private Integer kid;
         @TableField(exist=false)
         private Kecheng kecheng;
        /**
     * 指导过程
     */
         @TableField("gc")
    private String gc;

        /**
     * 中期检查
     */
         @TableField("zq")
    private String zq;

        /**
     * 成绩
     */
         @TableField("score")
    private String score;

        /**
     * 记录时间
     */
         @TableField("optime")
    private String optime;

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

    public Shijian setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Shijian setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public Integer getTid() {
        return tid;
    }

    public Shijian setTid(Integer tid) {
        this.tid = tid;
        return this;
    }

    public Integer getKid() {
        return kid;
    }

    public Shijian setKid(Integer kid) {
        this.kid = kid;
        return this;
    }

    public String getGc() {
        return gc;
    }

    public Shijian setGc(String gc) {
        this.gc = gc;
        return this;
    }

    public String getZq() {
        return zq;
    }

    public Shijian setZq(String zq) {
        this.zq = zq;
        return this;
    }

    public String getScore() {
        return score;
    }

    public Shijian setScore(String score) {
        this.score = score;
        return this;
    }

    public String getOptime() {
        return optime;
    }

    public Shijian setOptime(String optime) {
        this.optime = optime;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Shijian setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Shijian setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Shijian{" +
        "id=" + id +
        ", uid=" + uid +
        ", tid=" + tid +
        ", kid=" + kid +
        ", gc=" + gc +
        ", zq=" + zq +
        ", score=" + score +
        ", optime=" + optime +
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
