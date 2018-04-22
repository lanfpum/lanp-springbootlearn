package top.lxpsee.hessian.service.impl;

import top.lxpsee.hessian.service.HelloService;

/**
 * 努力常态化  2018/4/22 23:41
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}
