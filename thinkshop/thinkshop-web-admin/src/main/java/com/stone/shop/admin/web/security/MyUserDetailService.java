package com.stone.shop.admin.web.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.stone.shop.admin.dao.mapper.manage.role.IOperatorMapper;
import com.stone.shop.admin.dao.mapper.manage.role.IOperatorRoleMapper;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.domain.manage.role.OperatorEntity;
import com.stone.shop.domain.manage.role.OperatorRoleEntity;

public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private IOperatorMapper operatorMapper;

	@Autowired
	private IOperatorRoleMapper operatorRoleMapper;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		try {
			OperatorEntity user = operatorMapper.getOperator(username);
			if (user == null) {
				throw new UsernameNotFoundException("SwitchUserProcessingFilter.usernameNotFound");
			}
			boolean isEnable = false;
			if (user.getStatus().equals("1")) {
				isEnable = true;
			}
			List<OperatorRoleEntity> roles = operatorRoleMapper
					.queryOperatorRoles(username);
			List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
			if (roles != null) {
				for (Iterator<OperatorRoleEntity> iterator = roles.iterator(); iterator
						.hasNext();) {
					OperatorRoleEntity roleEntity = iterator.next();
					if (roleEntity == null) {
						continue;
					}
					GrantedAuthorityImpl authority = new GrantedAuthorityImpl(
							roleEntity.getRoleCode());
					authoritiesList.add(authority);
				}
			}
			UserInfo userInfo = new UserInfo(user.getOperatorId(),
					user.getPassword(), isEnable, true, true, true,
					authoritiesList);
			userInfo.setOperator(user);
			return userInfo;
		} catch (SQLException e) {
			JscnLogger.error("登录获取用户信息错误", e, this.getClass());
			throw new UsernameNotFoundException("用户不存在");
		}

	}

}
