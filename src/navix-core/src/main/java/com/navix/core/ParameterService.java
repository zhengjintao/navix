package com.navix.core;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 参数服务接口
 * 
 */
public interface ParameterService {

	/**
	 * 获得系统参数值(可以获得系统中各种参数:如系统参数、properties参数、系统常量)
	 * 
	 * @return
	 */
	public String getParameter(String key);

	/**
	 * 获得系统参数值(可以获得系统中各种参数:如用户个性化参数、系统参数、properties参数、系统常量)
	 * 
	 * @return
	 */
	public String getParameter(String key, String userId);

	/**
	 * 获得字典列表
	 * 
	 * @param index
	 * @return
	 */
	public List<Entry<String, String>> getDictionaryList(String index);

	/**
	 * 获得字典
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getDictionary(String key);


}
