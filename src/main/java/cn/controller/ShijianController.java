package cn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Kecheng;
import cn.entity.Pingjia;
import cn.entity.Shijian;
import cn.entity.Users;
import cn.service.KechengService;
import cn.service.ShijianService;
import cn.service.UsersService;
import cn.util.Const;
import cn.util.ServerResponse;

import com.alibaba.druid.util.StringUtils;
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
@RequestMapping("/shijian")
public class ShijianController {
	@Autowired
	ShijianService shijianService;
	@Autowired
	UsersService usersService;
	@Autowired
	KechengService kechengService;

	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Shijian> add(Shijian shijian) {
		Users u=usersService.getById(shijian.getUid());
		Kecheng k=kechengService.getById(shijian.getKid());
		QueryWrapper<Shijian> wrapper = new QueryWrapper<Shijian>();
		wrapper.eq("uid", shijian.getUid());
		wrapper.eq("kid", shijian.getKid());
		wrapper.eq("isdel",0);
		List<Shijian> list=shijianService.list(wrapper);
		if(list.size()>0){
			return new ServerResponse<Shijian>("1", "您已经选择了该课程!");
		}
		shijian.setOptime(Const.getTime());
		shijian.setTid(k.getTid());
		shijian.setIsdel(0);
		boolean flag = shijianService.save(shijian);
		if (flag) {
			return new ServerResponse<Shijian>("0", "操作成功!");
		} else {
			return new ServerResponse<Shijian>("1", "操作失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Shijian> update(Shijian shijian) {
		boolean flag = shijianService.updateById(shijian);
		if (flag) {
			return new ServerResponse<Shijian>("0", "修改成功!");
		} else {
			return new ServerResponse<Shijian>("1", "修改失败!");
		}
	}

	@RequestMapping("json")
	@ResponseBody
	public Shijian json(Integer id) {
		return shijianService.getById(id);
	}

	@RequestMapping("jsonlist")
	@ResponseBody
	public List<Shijian> jsonlist() {
		QueryWrapper<Shijian> wrapper = new QueryWrapper<Shijian>();
		wrapper.eq("isdel", 0);
		return shijianService.list(wrapper);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Shijian> delete(Shijian shijian) {
		shijian.setIsdel(1);
		boolean flag = shijianService.updateById(shijian);
		if (flag) {
			return new ServerResponse<Shijian>("0", "删除成功!");
		} else {
			return new ServerResponse<Shijian>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Shijian> list(Integer page, Integer limit, String name,
			HttpSession session) {
		QueryWrapper<Shijian> wrapper = new QueryWrapper<Shijian>();
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		Integer role = (Integer) session.getAttribute("role");
		if (role != null && role == 1) {
			Users u = (Users) session.getAttribute("users");
			// 查自己
			wrapper.eq("tid", u.getId());
		}
		if (role != null && role == 2) {
			Users u = (Users) session.getAttribute("users");
			// 查自己
			wrapper.eq("uid", u.getId());
		}
		wrapper.eq("isdel", 0);
		//wrapper.orderByDesc("id");
		IPage<Shijian> page_shijian = new Page<Shijian>(page, limit);
		page_shijian = shijianService.page(page_shijian, wrapper);
		// 会自动查出总条数
		int count = (int) page_shijian.getTotal();
		List<Shijian> list = page_shijian.getRecords();
		for(Shijian o : list){
			o.setUname(usersService.getById(o.getUid()).getName());
			o.setTname(usersService.getById(o.getTid()).getName());
			o.setKname(kechengService.getById(o.getKid()).getName());
		}
		return new ServerResponse<Shijian>("0", "", count, list);
	}
}
