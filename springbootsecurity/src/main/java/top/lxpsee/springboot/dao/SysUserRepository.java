package top.lxpsee.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.lxpsee.springboot.model.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
