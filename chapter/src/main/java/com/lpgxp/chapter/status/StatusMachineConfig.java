package com.lpgxp.chapter.status;

import com.lpgxp.chapter.util.Events;
import com.lpgxp.chapter.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;


/**
 * @author 努力常态化
 * date 2018/4/12  23:31
 */

@Configuration
@EnableStateMachine
public class StatusMachineConfig extends EnumStateMachineConfigurerAdapter<Status,Events> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure(StateMachineStateConfigurer<Status,Events> status) throws Exception {
        status.withStates()
                .initial(Status.UNPAID)
                .states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(Status.UNPAID).target(Status.WAITING_FOR_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(Status.WAITING_FOR_RECEIVE).target(Status.DONE)
                .event(Events.RECEIVE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<Status, Events> config) throws Exception {
        config
                .withConfiguration()
                .listener(listener());
    }

    public StateMachineListener<Status,Events> listener() {
        return new StateMachineListenerAdapter<Status,Events>() {
            @Override
            public void transition(Transition<Status, Events> transition) {
                if (transition.getTarget().getId() == Status.UNPAID) {
                    logger.info("订单支付，待支付");
                    return;
                }

                if (transition.getSource().getId() == Status.UNPAID
                        && transition.getTarget().getId() == Status.WAITING_FOR_RECEIVE) {
                    logger.info("用户完成支付，待收货");
                    return;
                }

                if (transition.getSource().getId() == Status.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == Status.DONE) {
                    logger.info("用户已收货，订单完成");
                    return;
                }

            }
        };
    }
}
