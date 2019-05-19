package com.javayh.service;

import com.javayh.mapper.UserDao;
import com.javayh.entity.SysPermission;
import com.javayh.entity.SysRole;
import com.javayh.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
@Service
public class MyUserDetailService  implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        SysUserVO sysUserVO = userDao.findByUserName(memberName);
        if (sysUserVO == null) {
            throw new UsernameNotFoundException(memberName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (SysRole role : sysUserVO.getSysRoles()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (SysPermission permission : role.getSysPermissions()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermissionUri());
                grantedAuthorities.add(authority);
            }
        }
        User user = new User(sysUserVO.getUsername(), sysUserVO.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
