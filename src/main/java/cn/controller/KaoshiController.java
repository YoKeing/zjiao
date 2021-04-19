package cn.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.entity.Kaoshi;
import cn.entity.Users;
import cn.service.KaoshiService;
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
@RequestMapping("/kaoshi")
public class KaoshiController {
	@Autowired
	KaoshiService kaoshiService;
	@Autowired
	UsersService usersService;

	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Kaoshi> add(Kaoshi kaoshi) {
			kaoshi.setIsdel(0);
		boolean flag = kaoshiService.save(kaoshi);
		if (flag) {
			return new ServerResponse<Kaoshi>("0", "操作成功!");
		} else {
			return new ServerResponse<Kaoshi>("1", "操作失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Kaoshi> update(Kaoshi kaoshi) {
		boolean flag = kaoshiService.updateById(kaoshi);
		if (flag) {
			return new ServerResponse<Kaoshi>("0", "修改成功!");
		} else {
			return new ServerResponse<Kaoshi>("1", "修改失败!");
		}
	}

	@RequestMapping("json")
	@ResponseBody
	public Kaoshi json(Integer id) {
		return kaoshiService.getById(id);
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Kaoshi> delete(Kaoshi kaoshi) {
		kaoshi.setIsdel(1);
		boolean flag = kaoshiService.updateById(kaoshi);
		if (flag) {
			return new ServerResponse<Kaoshi>("0", "删除成功!");
		} else {
			return new ServerResponse<Kaoshi>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Kaoshi> list(Integer page, Integer limit, String name,
			HttpSession session) {
		QueryWrapper<Kaoshi> wrapper = new QueryWrapper<Kaoshi>();
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		Integer role = (Integer) session.getAttribute("role");
		if (role != null && role == 1) {
			Users u = (Users) session.getAttribute("users");
			// 查自己
			wrapper.eq("tid", u.getId());
		}
		wrapper.eq("isdel",0);
		wrapper.orderByDesc("id");
		IPage<Kaoshi> page_kaoshi = new Page<Kaoshi>(page, limit);
		page_kaoshi = kaoshiService.page(page_kaoshi, wrapper);
		// 会自动查出总条数
		int count = (int) page_kaoshi.getTotal();
		List<Kaoshi> list = page_kaoshi.getRecords();
		for(Kaoshi o :list){
			o.setTeacher(usersService.getById(o.getTid()));
		}
		return new ServerResponse<Kaoshi>("0", "", count, list);
	}
}
