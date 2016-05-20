/*
 * @(#) MyInvocationSecurityMetadataSourceService.java  2011-3-23 锟斤拷锟斤拷02:58:29
 *
 * Copyright 2011 by Sparta 
 */

package org.ytgs.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.ytgs.security.service.SecurityDaoService;


/**
 * 锟斤拷锟斤拷牡牡胤锟斤拷锟斤拷锟斤拷锟斤拷峁┠筹拷锟斤拷锟皆达拷锟接︼拷锟饺拷薅锟斤拷澹拷锟絞etAttributes锟斤拷锟斤拷锟斤拷锟截的斤拷锟?锟斤拷锟斤拷锟节筹拷始锟斤拷时锟斤拷应锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷源锟斤拷锟斤拷锟接︼拷锟缴拷亩锟斤拷濉?
 * 
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	@Autowired
	private SecurityDaoService securityDaoService;
	private AntPathRequestMatcher urlMatcher;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
//
//	public CustomInvocationSecurityMetadataSourceService() {
//		loadResourceDefine();
//	}

	@SuppressWarnings("unused")
	private void loadResourceDefine() {
		
		// 锟斤拷Web锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷时锟斤拷锟斤拷取系统锟叫碉拷锟斤拷锟斤拷权锟睫★拷
		
		List<String> query = securityDaoService.getSysAuthoritiesNames();

		/*
		 * 应锟斤拷锟斤拷锟斤拷源为key锟斤拷 权锟斤拷为value锟斤拷 锟斤拷源通锟斤拷为url锟斤拷 权锟睫撅拷锟斤拷锟斤拷些锟斤拷ROLE_为前缀锟侥斤拷色锟斤拷 一锟斤拷锟斤拷源锟斤拷锟斤拷锟缴讹拷锟饺拷锟斤拷锟斤拷锟斤拷省锟?
		 * sparta
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		for (String auth : query) {
			ConfigAttribute ca = new SecurityConfig(auth);
			List<String> query1 = securityDaoService.getSysResourcesString(auth);
			for (String res : query1) {
				String url = res;
				if (resourceMap.containsKey(url)) {

					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}

			}

		}

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	// 锟斤拷锟絩equest锟斤拷锟揭碉拷锟斤拷氐锟饺拷锟斤拷锟斤拷谩锟?
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		
		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {
			String resURL = ite.next();
			urlMatcher = new AntPathRequestMatcher(resURL);
			if (urlMatcher.matches(request)) {
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
