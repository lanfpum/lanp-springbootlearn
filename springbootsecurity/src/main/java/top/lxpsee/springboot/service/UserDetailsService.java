package top.lxpsee.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.lxpsee.springboot.dao.SysUserRepository;
import top.lxpsee.springboot.model.SysUser;

/**
 * 努力常态化  2018/5/31 7:38
 */
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:" + s);
        System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
        return user;
    }
}
