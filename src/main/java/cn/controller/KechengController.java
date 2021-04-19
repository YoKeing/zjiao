package cn.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Kecheng;
import cn.entity.Shijian;
import cn.entity.Users;
import cn.entity.Xuanke;
import cn.service.KechengService;
import cn.service.ShijianService;
import cn.service.UsersService;
import cn.service.XuankeService;
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
@RequestMapping("/kecheng")
public class KechengController {
	@Autowired
	KechengService kechengService;
	@Autowired
	UsersService usersService;
	@Autowired
	XuankeService xuankeService;
	@Autowired
	ShijianService shijianService;

	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Kecheng> add(Kecheng kecheng) {
		kecheng.setIsdel(0);
		boolean flag = kechengService.save(kecheng);
		if (flag) {
			return new ServerResponse<Kecheng>("0", "操作成功!");
		} else {
			return new ServerResponse<Kecheng>("1", "操作失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Kecheng> update(Kecheng kecheng) {
		boolean flag = kechengService.updateById(kecheng);
		if (flag) {
			return new ServerResponse<Kecheng>("0", "修改成功!");
		} else {
			return new ServerResponse<Kecheng>("1", "修改失败!");
		}
	}

	@RequestMapping("json")
	@ResponseBody
	public Kecheng json(Integer id) {
		return kechengService.getById(id);
	}
	@RequestMapping("jsonlist")
	@ResponseBody
	public List<Kecheng> jsonlist(HttpSession session) {
		Integer role = (Integer) session.getAttribute("role");
		List<Integer> kids=new ArrayList<Integer>();
		if (role != null && role == 2) {
			Users u = (Users) session.getAttribute("users");
			QueryWrapper<Xuanke> xwrapper = new QueryWrapper<Xuanke>();
			xwrapper.eq("uid", u.getId());
			xwrapper.eq("isdel", 0);
			List<Xuanke> xkids=xuankeService.list(xwrapper);
			QueryWrapper<Shijian> swrapper = new QueryWrapper<Shijian>();
			swrapper.eq("uid", u.getId());
			swrapper.eq("isdel", 0);
			List<Shijian> skids=shijianService.list(swrapper);
			
			
			for(Xuanke x:xkids){
				kids.add(x.getKid());
			}
			for(Shijian x:skids){
				kids.add(x.getKid());
			}
			
		}
		QueryWrapper<Kecheng> wapper = new QueryWrapper<Kecheng>();
		wapper.eq("isdel", 0);
		wapper.in("id", kids);
		List<Kecheng> list = kechengService.list(wapper);
		return list;
	}
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Kecheng> delete(Kecheng kecheng) {
		kecheng.setIsdel(1);
		boolean flag = kechengService.updateById(kecheng);
		if (flag) {
			return new ServerResponse<Kecheng>("0", "删除成功!");
		} else {
			return new ServerResponse<Kecheng>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Kecheng> list(Integer page, Integer limit, String name,
			HttpSession session) {
		QueryWrapper<Kecheng> wrapper = new QueryWrapper<Kecheng>();
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
		IPage<Kecheng> page_kecheng = new Page<Kecheng>(page, limit);
		page_kecheng = kechengService.page(page_kecheng, wrapper);
		// 会自动查出总条数
		int count = (int) page_kecheng.getTotal();
		List<Kecheng> list = page_kecheng.getRecords();
		for(Kecheng o :list){
			o.setTeacher(usersService.getById(o.getTid()));
		}
		return new ServerResponse<Kecheng>("0", "", count, list);
	}
}
