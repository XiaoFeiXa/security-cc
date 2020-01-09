package com.security.cc.learn.securitycc.repository;

import com.security.cc.learn.securitycc.bean.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, String> {

    SysUser findByUsername(String username);
}
