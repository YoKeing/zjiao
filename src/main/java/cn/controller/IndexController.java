package cn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Users;
import cn.service.UsersService;
import cn.util.ServerResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
public class IndexController {
	@Autowired
	UsersService usersService;


	@RequestMapping("/index")
	public String index(String username, String password, Integer role,
			HttpServletRequest request) {
		return "index";
	}


	@RequestMapping("/login")
	@ResponseBody
	public String login(String username, String password, Integer role,
			HttpServletRequest request) {
		request.getSession().setAttribute("role", role);
		QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
		wrapper.eq("role", role);
		wrapper.eq("username", username);
		wrapper.eq("password", password);
		List<Users> list = usersService.list(wrapper);
		if (list != null && list.size() > 0) {
			Users users = list.get(0);
			request.getSession().setAttribute("users", users);
			return "ok";
		}
		return "error";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}

	@RequestMapping("/uppwd")
	@ResponseBody
	public ServerResponse<String> uppwd(String password, HttpSession session) {
		Users a = (Users) session.getAttribute("users");
		a.setPassword(password);
		boolean flag = usersService.updateById(a);
		if (flag) {
			return new ServerResponse<String>("0", "修改成功，请重新登录");
		}
		return new ServerResponse<String>("1", "修改失败");
	}

}
