package com.stone.shop.admin.web.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.stone.shop.common.utils.StringUtils;
import com.stone.shop.domain.manage.role.MenuEntity;
import com.stone.shop.domain.manage.role.RoleEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.service.manage.role.IMenuRoleService;
import com.stone.shop.service.manage.role.IMenuService;
import com.stone.shop.service.manage.role.IRoleService;
import com.stone.shop.utils.log.JscnLogger;

public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private PathMatcher pathMatcher = new AntPathMatcher();;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IMenuRoleService menuRoleService;

	@Autowired
	private IMenuService menuService;

	public MyInvocationSecurityMetadataSource() {
		// loadResourceDefine();
	}

	public void loadResourceDefine() {
		try {
			ResultList<RoleEntity> rolesRes = roleService.queryRoles(null, null);
			if (rolesRes == null) {
				return;
			}
			if (rolesRes.getResults() == null) {
				return;
			}
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			for (Iterator<RoleEntity> iterator = rolesRes.getResults().iterator(); iterator.hasNext();) {
				RoleEntity roleEntity = iterator.next();
				if (roleEntity == null) {
					continue;
				}
				Collection<ConfigAttribute> atts = new HashSet<ConfigAttribute>();
				ConfigAttribute ca = new SecurityConfig(roleEntity.getCode());
				atts.add(ca);
				List<MenuEntity> menus = menuRoleService.queryMenuRoles(roleEntity.getCode());
				for (Iterator<MenuEntity> iterator2 = menus.iterator(); iterator2.hasNext();) {

					MenuEntity menuEntity = iterator2.next();
					if (menuEntity == null || StringUtils.isEmpty(menuEntity.getUrl())) {
						continue;
					}
					if (resourceMap.containsKey(menuEntity.getUrl())) {
						Collection<ConfigAttribute> oldAtts = resourceMap.get(menuEntity.getUrl());
						Collection<ConfigAttribute> oldAtts2 = new HashSet<ConfigAttribute>();
						oldAtts2.addAll(oldAtts);
						oldAtts2.addAll(atts);
						resourceMap.put(menuEntity.getUrl(), oldAtts2);
						oldAtts2 = null;
						oldAtts = null;
					} else {
						resourceMap.put(menuEntity.getUrl(), atts);
					}

				}
			}
			// Map<String, Collection<ConfigAttribute>> aaa2 = resourceMap;
			ResultList<MenuEntity> menus2 = menuService.queryMenus(null, null);
			Collection<ConfigAttribute> atts = new HashSet<ConfigAttribute>();
			ConfigAttribute ca = new SecurityConfig("ROLE_");
			atts.add(ca);
			for (Iterator<MenuEntity> iterator = menus2.getResults().iterator(); iterator.hasNext();) {
				MenuEntity menuEntity = iterator.next();
				if (menuEntity == null || StringUtils.isEmpty(menuEntity.getUrl())) {
					continue;
				}
				if (resourceMap.containsKey(menuEntity.getUrl())) {
					Collection<ConfigAttribute> oldAtts = resourceMap.get(menuEntity.getUrl());
					oldAtts.addAll(atts);
					resourceMap.put(menuEntity.getUrl(), oldAtts);
				} else {
					resourceMap.put(menuEntity.getUrl(), atts);
				}

			}

			// Map<String, Collection<ConfigAttribute>> aaa = resourceMap;
			// aaa.toString();

		} catch (Exception e) {
			JscnLogger.error(e.getMessage(), e, this.getClass());
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		String[] da = url.split("[?]");
		url = da[0];
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (StringUtils.isEmpty(resURL)) {
				continue;
			}
			if (pathMatcher.match(url, resURL)) {
				return resourceMap.get(resURL);
			}
		}
		return null;

	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
