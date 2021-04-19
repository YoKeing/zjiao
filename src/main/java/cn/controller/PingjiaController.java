package cn.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Kecheng;
import cn.entity.Pingjia;
import cn.entity.Users;
import cn.service.KechengService;
import cn.service.PingjiaService;
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
@RequestMapping("/pingjia")
public class PingjiaController {
	@Autowired
	PingjiaService pingjiaService;
	@Autowired
	UsersService usersService;
	@Autowired
	KechengService kechengService;
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Pingjia> add(Pingjia pingjia) {
		Kecheng kecheng=kechengService.getById(pingjia.getKid());

		Users u=usersService.getById(pingjia.getUid());
		Users t=usersService.getById(kecheng.getTid());
		
		QueryWrapper<Pingjia> wrapper = new QueryWrapper<Pingjia>();
		wrapper.eq("uid", pingjia.getUid());
		wrapper.eq("tid", kecheng.getTid());
		wrapper.eq("isdel",0);
		List<Pingjia> list=pingjiaService.list(wrapper);
		if(list.size()>0){
			return new ServerResponse<Pingjia>("1", "您已经评价了该老师!");
		}
		pingjia.setKid(kecheng.getId());
		pingjia.setTid(kecheng.getTid());
		pingjia.setOptime(Const.getFullTime());
		pingjia.setName(t.getName());
		pingjia.setIsdel(0);
		boolean flag = pingjiaService.save(pingjia);
		if (flag) {
			return new ServerResponse<Pingjia>("0", "操作成功!");
		} else {
			return new ServerResponse<Pingjia>("1", "操作失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Pingjia> update(Pingjia pingjia) {
		boolean flag = pingjiaService.updateById(pingjia);
		if (flag) {
			return new ServerResponse<Pingjia>("0", "修改成功!");
		} else {
			return new ServerResponse<Pingjia>("1", "修改失败!");
		}
	}

	@RequestMapping("json")
	@ResponseBody
	public Pingjia json(Integer id) {
		return pingjiaService.getById(id);
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Pingjia> delete(Pingjia pingjia) {
		pingjia.setIsdel(1);
		boolean flag = pingjiaService.updateById(pingjia);
		if (flag) {
			return new ServerResponse<Pingjia>("0", "删除成功!");
		} else {
			return new ServerResponse<Pingjia>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Pingjia> list(Integer page, Integer limit, String name,
			HttpSession session) {
		QueryWrapper<Pingjia> wrapper = new QueryWrapper<Pingjia>();
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		Integer role = (Integer) session.getAttribute("role");
		if (role != null && role == 2) {
			Users u = (Users) session.getAttribute("users");
			// 查自己
			wrapper.eq("uid", u.getId());
		}
		wrapper.eq("isdel",0);
		wrapper.orderByDesc("id");
		IPage<Pingjia> page_pingjia = new Page<Pingjia>(page, limit);
		page_pingjia = pingjiaService.page(page_pingjia, wrapper);
		// 会自动查出总条数
		int count = (int) page_pingjia.getTotal();
		List<Pingjia> list = page_pingjia.getRecords();
		for(Pingjia o :list){
			o.setUname(usersService.getById(o.getUid()).getName());
			o.setTname(usersService.getById(o.getTid()).getName());
			o.setKname(kechengService.getById(o.getKid()).getName());
		}
		return new ServerResponse<Pingjia>("0", "", count, list);
	}
}
