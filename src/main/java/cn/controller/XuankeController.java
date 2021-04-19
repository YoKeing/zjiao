package cn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Kecheng;
import cn.entity.Pingjia;
import cn.entity.Xuanke;
import cn.entity.Users;
import cn.service.KechengService;
import cn.service.XuankeService;
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
@RequestMapping("/xuanke")
public class XuankeController {
	@Autowired
	XuankeService xuankeService;
	@Autowired
	UsersService usersService;
	@Autowired
	KechengService kechengService;

	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Xuanke> add(Xuanke xuanke) {
		Users u=usersService.getById(xuanke.getUid());
		Kecheng k=kechengService.getById(xuanke.getKid());
		QueryWrapper<Xuanke> wrapper = new QueryWrapper<Xuanke>();
		wrapper.eq("uid", xuanke.getUid());
		wrapper.eq("kid", xuanke.getKid());
		wrapper.eq("isdel",0);
		List<Xuanke> list=xuankeService.list(wrapper);
		if(list.size()>0){
			return new ServerResponse<Xuanke>("1", "您已经选择了该课程!");
		}
		xuanke.setOptime(Const.getTime());
		xuanke.setTid(k.getTid());
		xuanke.setIsdel(0);
		boolean flag = xuankeService.save(xuanke);
		if (flag) {
			return new ServerResponse<Xuanke>("0", "操作成功!");
		} else {
			return new ServerResponse<Xuanke>("1", "操作失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Xuanke> update(Xuanke xuanke) {
		boolean flag = xuankeService.updateById(xuanke);
		if (flag) {
			return new ServerResponse<Xuanke>("0", "修改成功!");
		} else {
			return new ServerResponse<Xuanke>("1", "修改失败!");
		}
	}

	@RequestMapping("json")
	@ResponseBody
	public Xuanke json(Integer id) {
		return xuankeService.getById(id);
	}

	@RequestMapping("jsonlist")
	@ResponseBody
	public List<Xuanke> jsonlist() {
		QueryWrapper<Xuanke> wrapper = new QueryWrapper<Xuanke>();
		wrapper.eq("isdel", 0);
		return xuankeService.list(wrapper);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Xuanke> delete(Xuanke xuanke) {
		xuanke.setIsdel(1);
		boolean flag = xuankeService.updateById(xuanke);
		if (flag) {
			return new ServerResponse<Xuanke>("0", "删除成功!");
		} else {
			return new ServerResponse<Xuanke>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Xuanke> list(Integer page, Integer limit, String name,
			HttpSession session) {
		QueryWrapper<Xuanke> wrapper = new QueryWrapper<Xuanke>();
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
		IPage<Xuanke> page_xuanke = new Page<Xuanke>(page, limit);
		page_xuanke = xuankeService.page(page_xuanke, wrapper);
		// 会自动查出总条数
		int count = (int) page_xuanke.getTotal();
		List<Xuanke> list = page_xuanke.getRecords();
		for(Xuanke o : list){
			o.setUname(usersService.getById(o.getUid()).getName());
			o.setTname(usersService.getById(o.getTid()).getName());
			o.setKname(kechengService.getById(o.getKid()).getName());
		}
		return new ServerResponse<Xuanke>("0", "", count, list);
	}
}
