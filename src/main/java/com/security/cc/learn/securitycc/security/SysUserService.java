package com.security.cc.learn.securitycc.security;

import com.security.cc.learn.securitycc.bean.SysRole;
import com.security.cc.learn.securitycc.bean.SysUser;
import com.security.cc.learn.securitycc.repository.SysRoleRepository;
import com.security.cc.learn.securitycc.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserService implements UserDetailsService {


    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 先初始化两个用户。
     *
     */
    @PostConstruct
    private void init(){
        if (sysUserRepository.findByUsername("admin") == null) {
            SysRole roleAdmin = new SysRole();
            roleAdmin.setName("ROLE_ADMIN");

            SysUser sysAdmin = new SysUser();
            sysAdmin.setUsername("admin");
            sysAdmin.setPassword(passwordEncoder.encode("123"));
            List<SysRole> sysRolesAdmin = new ArrayList<>();
            sysRolesAdmin.add(roleAdmin);
            sysAdmin.setSysRoles(sysRolesAdmin);

            sysRoleRepository.save(roleAdmin);
            sysUserRepository.save(sysAdmin);
        }

        if (sysUserRepository.findByUsername("user") == null) {
            SysRole roleUser = new SysRole();
            roleUser.setName("ROLE_USER");

            SysUser sysUser = new SysUser();
            sysUser.setUsername("user");
            sysUser.setPassword(passwordEncoder.encode("123"));
            List<SysRole> sysRolesUser = new ArrayList<>();
            sysRolesUser.add(roleUser);
            sysUser.setSysRoles(sysRolesUser);

            sysRoleRepository.save(roleUser);
            sysUserRepository.save(sysUser);

        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return sysUserRepository.findByUsername(username);
    }
}
