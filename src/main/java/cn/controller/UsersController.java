package cn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Users;
import cn.service.UsersService;
import cn.util.Const;
import cn.util.ServerResponse;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Abyss
 */
@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService usersService;

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Users> list(Integer page, Integer limit, Integer role,String no,String name,HttpSession session) {
		QueryWrapper<Users> wapper = new QueryWrapper<Users>();
		if (!StringUtils.isEmpty(no)) {
			wapper.like("no", no);
		}
		if (!StringUtils.isEmpty(name)) {
			wapper.like("name", name);
		}
		Integer r = (Integer) session.getAttribute("role");
		if (r != null && r == 1) {
			Users u = (Users) session.getAttribute("users");
			// 查自己的
			wapper.eq("tid", u.getId());
		}
		wapper.eq("role", role);
		IPage<Users> page_users = new Page<Users>(page, limit);
		page_users = usersService.page(page_users, wapper);
		// 关联对象
		List<Users> list = page_users.getRecords();
		/*for (Users m : list) {
		}*/
		// 会自动查出总条数
		int count = (int) page_users.getTotal();
		return new ServerResponse<Users>("0", "", count,
				page_users.getRecords());
	}

	@RequestMapping("jsonlist")
	@ResponseBody
	public List<Users> jsonlist() {
		QueryWrapper<Users> wapper = new QueryWrapper<Users>();
		wapper.eq("role", 1);
		wapper.eq("isdel", 0);
		List<Users> list = usersService.list(wapper);
		return list;
	}
	
	@RequestMapping("jsonslist")
	@ResponseBody
	public List<Users> jsonslist() {
		QueryWrapper<Users> wapper = new QueryWrapper<Users>();
		wapper.eq("role", 2);
		wapper.eq("isdel", 0);
		List<Users> list = usersService.list(wapper);
		return list;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Users> add(Users users) {
		QueryWrapper<Users> wapper = new QueryWrapper<Users>();
		wapper.eq("no", users.getNo());
		Users u = usersService.getOne(wapper);
		if (u != null) {
			return new ServerResponse<Users>("1", "该学号已经存在!");
		} else {
			wapper = new QueryWrapper<Users>();
			wapper.eq("username", users.getUsername());
			u = usersService.getOne(wapper);
			if (u != null) {
				return new ServerResponse<Users>("1", "该用户名已经存在!");
			} else {
				users.setIsdel(0);
				usersService.save(users);
				return new ServerResponse<Users>("0", "添加成功!");
			}
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Users> update(Users users) {
		boolean flag = usersService.updateById(users);
		if (flag) {
			return new ServerResponse<Users>("0", "修改成功!");
		} else {
			return new ServerResponse<Users>("1", "修改失败!");
		}
	}

	@RequestMapping("getById")
	@ResponseBody
	public Users getById(Integer id) {
		Users users = usersService.getById(id);
		return users;
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Users> delete(Users users) {
		users.setIsdel(1);
		boolean flag = usersService.updateById(users);
		if (flag) {
			return new ServerResponse<Users>("0", "删除成功!");
		} else {
			return new ServerResponse<Users>("1", "删除失败!");
		}
	}

}
