package cn.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Kaoqin;
import cn.entity.Kecheng;
import cn.entity.Users;
import cn.service.KaoqinService;
import cn.service.KechengService;
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
@RequestMapping("/kaoqin")
public class KaoqinController {
	@Autowired
	KaoqinService kaoqinService;
	@Autowired
	UsersService usersService;
	@Autowired
	KechengService kechengService;

	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Kaoqin> add(Kaoqin kaoqin) {
		Users users=usersService.getById(kaoqin.getUid());
		kaoqin.setName(users.getName());
		Kecheng kecheng=kechengService.getById(kaoqin.getKid());
		kaoqin.setKid(kecheng.getId());
		kaoqin.setTid(kecheng.getTid());
		kaoqin.setOptime(Const.getFullTime());
		kaoqin.setIsdel(0);
		boolean flag = kaoqinService.save(kaoqin);
		if (flag) {
			return new ServerResponse<Kaoqin>("0", "操作成功!");
		} else {
			return new ServerResponse<Kaoqin>("1", "操作失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Kaoqin> update(Kaoqin kaoqin) {
		boolean flag = kaoqinService.updateById(kaoqin);
		if (flag) {
			return new ServerResponse<Kaoqin>("0", "修改成功!");
		} else {
			return new ServerResponse<Kaoqin>("1", "修改失败!");
		}
	}

	@RequestMapping("json")
	@ResponseBody
	public Kaoqin json(Integer id) {
		return kaoqinService.getById(id);
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Kaoqin> delete(Kaoqin kaoqin) {
		kaoqin.setIsdel(1);
		boolean flag = kaoqinService.updateById(kaoqin);
		if (flag) {
			return new ServerResponse<Kaoqin>("0", "删除成功!");
		} else {
			return new ServerResponse<Kaoqin>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Kaoqin> list(Integer page, Integer limit, String name,
			HttpSession session) {
		QueryWrapper<Kaoqin> wrapper = new QueryWrapper<Kaoqin>();
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
		wrapper.eq("isdel",0);
		wrapper.orderByDesc("id");
		IPage<Kaoqin> page_kaoqin = new Page<Kaoqin>(page, limit);
		page_kaoqin = kaoqinService.page(page_kaoqin, wrapper);
		// 会自动查出总条数
		int count = (int) page_kaoqin.getTotal();
		List<Kaoqin> list = page_kaoqin.getRecords();
		for(Kaoqin o :list){
			o.setUname(usersService.getById(o.getUid()).getName());
			o.setTname(usersService.getById(o.getTid()).getName());
			o.setKname(kechengService.getById(o.getKid()).getName());
		}
		return new ServerResponse<Kaoqin>("0", "", count, list);
	}
}
