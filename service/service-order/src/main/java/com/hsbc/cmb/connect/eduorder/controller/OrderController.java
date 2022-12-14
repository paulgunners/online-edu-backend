package com.hsbc.cmb.connect.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsbc.cmb.connect.commonutils.JwtUtils;
import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduorder.entity.Order;
import com.hsbc.cmb.connect.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Paul
 * @since 2022-10-18
 */
@RestController
@RequestMapping("/eduorder/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    //1 生成订单的方法
    @PostMapping("createOrder/{courseId}")
    public ResponseEntity saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        //创建订单，返回订单号
        String orderNo = orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return ResponseEntity.success().data("orderId",orderNo);
    }

    //2 根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public ResponseEntity getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return ResponseEntity.success().data("item",order);
    }

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 1代表已经支付
        int count = orderService.count(wrapper);
        if (count > 0) { //已经支付
            return true;
        } else {
            return false;
        }
    }

}

