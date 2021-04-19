package cn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Jiaocai;
import cn.entity.Users;
import cn.service.JiaocaiService;
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
@RequestMapping("/jiaocai")
public class JiaocaiController {
	@Autowired
	JiaocaiService jiaocaiService;
	@Autowired
	UsersService usersService;

	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Jiaocai> add(Jiaocai jiaocai) {
		jiaocai.setOptime(Const.getFullTime());
		jiaocai.setIsdel(0);
		boolean flag = jiaocaiService.save(jiaocai);
		if (flag) {
			return new ServerResponse<Jiaocai>("0", "添加成功!");
		} else {
			return new ServerResponse<Jiaocai>("1", "添加失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Jiaocai> update(Jiaocai jiaocai) {
		boolean flag = jiaocaiService.updateById(jiaocai);
		if (flag) {
			return new ServerResponse<Jiaocai>("0", "修改成功!");
		} else {
			return new ServerResponse<Jiaocai>("1", "修改失败!");
		}
	}

	@RequestMapping("json")
	@ResponseBody
	public Jiaocai json(Integer id) {
		return jiaocaiService.getById(id);
	}

	@RequestMapping("jsonlist")
	@ResponseBody
	public List<Jiaocai> jsonlist() {
		QueryWrapper<Jiaocai> wrapper = new QueryWrapper<Jiaocai>();
		wrapper.eq("isdel", 0);
		return jiaocaiService.list(wrapper);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Jiaocai> delete(Jiaocai jiaocai) {
		jiaocai.setIsdel(1);
		boolean flag = jiaocaiService.updateById(jiaocai);
		if (flag) {
			return new ServerResponse<Jiaocai>("0", "删除成功!");
		} else {
			return new ServerResponse<Jiaocai>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Jiaocai> list(Integer page, Integer limit, String name,
			HttpSession session) {
		QueryWrapper<Jiaocai> wrapper = new QueryWrapper<Jiaocai>();
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		wrapper.eq("isdel", 0);
		//wrapper.orderByDesc("id");
		IPage<Jiaocai> page_jiaocai = new Page<Jiaocai>(page, limit);
		page_jiaocai = jiaocaiService.page(page_jiaocai, wrapper);
		// 会自动查出总条数
		int count = (int) page_jiaocai.getTotal();
		List<Jiaocai> list = page_jiaocai.getRecords();
		return new ServerResponse<Jiaocai>("0", "", count, list);
	}
}
