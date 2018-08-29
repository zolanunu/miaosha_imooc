package com.imooc.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;

@Controller
@RequestMapping("/demo")
//@EnableAutoConfiguration
public class DemoController {
	
	@Autowired
	UserService UserService;
	
	@Autowired
	RedisService redisService;
	@RequestMapping("/home")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
	// 1. json Êä³ö
	// 2. Ò³Ãæ
	@RequestMapping("/hello")
	@ResponseBody
	public Result<String> hello() {
		return Result.success("hello, imooc");
//		return "hi, world";
//		return new Result(0, "success", "hello, imooc");
		
	}
	@RequestMapping("/helloError")
	@ResponseBody
	public Result<String> helloError() {
		return Result.error(CodeMsg.SERVER_ERROR);
//		return new Result(500102, "****");
//		return new Result(500100, "helll");
//		return new Result(500103, "sessionÊ§Ð§");
	}
	
	@RequestMapping("/thymeleaf")
//	@ResponseBody
	public String thymeleaf(Model model) {
		model.addAttribute("name", "zola");
		return "hello";
	}
	
	@RequestMapping("/db/get")
	@ResponseBody
	public Result<User> dbGet() {
		User user = UserService.getById(1);
		if(user != null) {
			return Result.success(user);
		}
		return Result.error(CodeMsg.SERVER_ERROR);
	}
	
	@RequestMapping("/db/tx")
	@ResponseBody
	public Result<Boolean> dbtx() {
		UserService.tx();
		return Result.success(true);
	}
	
	@RequestMapping("/redis/get")
	@ResponseBody
	public Result<User> redisGet() {
		User user = redisService.get(UserKey.getById, ""+1, User.class);
		return Result.success(user);
	}
	
	@RequestMapping("/redis/set")
	@ResponseBody
	public Result<Boolean> redisSet() {
		User user = new User();
		user.setId(1);
		user.setName("1111");
		boolean v1 = redisService.set(UserKey.getById, ""+1, user);
//		String string = redisService.getRedis(KeyPrefix, "key2", String.class);
		return Result.success(v1);
	}
}
