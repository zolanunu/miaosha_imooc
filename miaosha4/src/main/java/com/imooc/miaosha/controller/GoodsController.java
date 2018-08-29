package com.imooc.miaosha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
    @RequestMapping("/to_list")
    public String list(Model model,MiaoshaUser user) {
    	model.addAttribute("user", user);
    	// ��ѯ��Ʒ�б�
    	List<GoodsVo> goodsVos = goodsService.listGoods();
    	
    	model.addAttribute("goodsList", goodsVos);
        return "goods_list";
    }
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model,MiaoshaUser user,
    		@PathVariable("goodsId") long goodsId) {
    	model.addAttribute("user", user);
    	// ��ѯ��Ʒ�б�����ҳ��
    	
    	GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
    	model.addAttribute("goods", goodsVo);
    	
    	long startTime = goodsVo.getStartDate().getTime();
    	long endTime = goodsVo.getEndDate().getTime();
    	long nowTime = System.currentTimeMillis();
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(nowTime < startTime ) {//��ɱ��û��ʼ������ʱ
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startTime - nowTime )/1000);
    	}else  if(nowTime > endTime){//��ɱ�Ѿ�����
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//��ɱ������
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	model.addAttribute("miaoshaStatus", miaoshaStatus);
    	model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
