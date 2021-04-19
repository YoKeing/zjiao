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
@TableName("users")
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

        /**
     * 编号
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 学号
     */
         @TableField("no")
    private String no;

        /**
     * 用户名
     */
         @TableField("username")
    private String username;

        /**
     * 密码
     */
         @TableField("password")
    private String password;

        /**
     * 姓名
     */
         @TableField("name")
    private String name;

        /**
     * 电话
     */
         @TableField("phone")
    private String phone;

        /**
     * 专业
     */
         @TableField("zhuanye")
    private String zhuanye;

        /**
     * 班级
     */
         @TableField("banji")
    private String banji;

        /**
     * 性别
     */
         @TableField("sex")
    private String sex;

        /**
     * 身份
     */
         @TableField("role")
    private Integer role;

        /**
     * 删除标记
     */
         @TableField("isdel")
    private Integer isdel;


    public Integer getId() {
        return id;
    }

    public Users setId(Integer id) {
        this.id = id;
        return this;
    }

  
    public String getNo() {
        return no;
    }

    public Users setNo(String no) {
        this.no = no;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Users setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Users setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Users setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public Users setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
        return this;
    }

    public String getBanji() {
        return banji;
    }

    public Users setBanji(String banji) {
        this.banji = banji;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Users setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public Users setRole(Integer role) {
        this.role = role;
        return this;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public Users setIsdel(Integer isdel) {
        this.isdel = isdel;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Users{" +
        "id=" + id +
        ", no=" + no +
        ", username=" + username +
        ", password=" + password +
        ", name=" + name +
        ", phone=" + phone +
        ", zhuanye=" + zhuanye +
        ", banji=" + banji +
        ", sex=" + sex +
        ", role=" + role +
        ", isdel=" + isdel +
        "}";
    }

}
