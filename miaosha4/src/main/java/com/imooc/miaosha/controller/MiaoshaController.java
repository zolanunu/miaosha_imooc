package com.imooc.miaosha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imooc.miaosha.domain.MiaoshaOrder;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaService;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.service.OrderService;
import com.imooc.miaosha.vo.GoodsVo;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

	@Autowired
	MiaoshaUserService userService;

	@Autowired
	RedisService redisService;

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	MiaoshaService miaoshaService;

	@RequestMapping("/do_miaosha")
	public String list(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
		model.addAttribute("user", user);
		if (user == null) {
			// 没有登录的话
			return "login";
		}
		// 判断商品是否有库存
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		int stock = goods.getGoodsStock();
		if (0 >= stock) {
			model.addAttribute("errmsg", CodeMsg.GOODSSTOCK_ERROR.getMsg());
			return "miaosha_fail";
		}
		// 判断是否已经秒杀过这个商品，防止重复秒杀
		MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
		if (miaoshaOrder != null) {
			model.addAttribute("errmsg", CodeMsg.REPEATE_ERROR.getMsg());
			return "miaosha_fail";
		}
		// 已经可以秒杀了, 减库存，下订单，写入秒杀订单
		// 考虑上面的操作是一个事务操作，所以将这个动作放在了MiaoshaService
		OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("goods", goods);
		return "order_detail";
	}

}
