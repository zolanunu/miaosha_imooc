package com.imooc.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.miaosha.dao.GoodsDao;
import com.imooc.miaosha.domain.Goods;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.vo.GoodsVo;

@Service
public class MiaoshaService {
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Transactional
	public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
		//ºıø‚¥Ê œ¬∂©µ• –¥»Î√Î…±∂©µ•
		goodsService.reduceStock(goods);
		//order_info maiosha_order
		return orderService.createOrder(user, goods);
	}
}
