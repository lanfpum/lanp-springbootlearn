package com.lpgxp.chapter.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author 努力常态化
 * date 2018/4/13  0:08
 */
@WithStateMachine
public class EventConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("订单创建，待支付");
    }

    @OnTransition(source = "UNPAID",target = "WAITING_FOR_RECEIVE")
    public void pay() {
        logger.info("用户完成支付，待收货");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE",target = "DONE")
    public void receive() {
        logger.info("用户已收货，订单完成");
    }


}
