package com.navix.parameter.service;

import com.navix.core.auth.domain.LoginUser;
import com.navix.parameter.domain.AloneDictionaryType;


public interface DictionaryTypeServiceInter {
	/**
	 * 通过一个键获得一个应用配置字符串值
	 */
	public String getConfigValue(String key);


	public void deleteEntity(String entity, LoginUser user);

	public AloneDictionaryType editEntity(AloneDictionaryType entity,
			LoginUser user);

	public AloneDictionaryType getEntity(String id);

	public AloneDictionaryType insertEntity(AloneDictionaryType entity,
			LoginUser user);

}
