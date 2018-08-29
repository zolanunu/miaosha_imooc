package com.imooc.miaosha.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.vo.LoginVo;


@Controller
@RequestMapping("/login")
public class LoginController {
	
private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
    	log.info(loginVo.toString());
    	//µÇÂ¼
    	userService.login(response, loginVo);
    	return Result.success(true);
    }
}
//	private static org.slf4j.Logger log = LoggerFactory.getLogger(LoginController.class);
//	@Autowired
//	MiaoshaUserService miaoshaUserService;
//	
//	@Autowired
//	RedisService redisService;
//	// Ìø×ªµ½loginÒ³Ãæ
//	@RequestMapping("/to_login")
//	String toLogin() {
//		return "login";
//	}
//	
//	@RequestMapping("/do_login")
//	@ResponseBody
//	public Result<Boolean> doLogin(HttpServletResponse response, LoginVo loginVo) {
//		log.info(loginVo.toString());
////		String passInput = loginVo.getPassword();
////		String mobile = loginVo.getPhone();
////		if(StringUtils.isBlank(passInput)) {
////			return Result.error(CodeMsg.PASSWORD_EMPTY);
////		}
////		if(StringUtils.isBlank(mobile)) {
////			return Result.error(CodeMsg.MOBILE_EMPTY);
////		}
////		if(!ValidatorUtil.isMobile(mobile)) {
////			return Result.error(CodeMsg.MOBILE_ERROR);
////		}
//		// µÇÂ¼
//		CodeMsg sMsg = miaoshaUserService.login(loginVo);
//		if(sMsg.getCode() == 0) {
//			return Result.success(true);
//		} else {
//			return Result.error(sMsg);
//		}
//		
////		UserService.login(response, loginVo);
//	}
//}
