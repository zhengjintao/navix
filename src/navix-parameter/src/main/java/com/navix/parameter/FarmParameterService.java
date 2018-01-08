package com.navix.parameter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.navix.core.ParameterService;
import com.navix.core.util.spring.BeanFactory;
import com.navix.parameter.service.DictionaryEntityServiceInter;
import com.navix.parameter.service.ParameterServiceInter;
import com.navix.parameter.service.impl.ConstantVarService;
import com.navix.parameter.service.impl.PropertiesFileService;

/**
 * 框架系统参数服务
 * 
 * @author 
 * 
 */
public class FarmParameterService implements ParameterService {
	private static ParameterServiceInter parametersLocal;
	private static DictionaryEntityServiceInter dictionaryentitysLocal;
	private static ParameterService localstatic;

	/**
	 * @return
	 */
	public static ParameterService getInstance() {
		if (localstatic == null) {
			localstatic = new FarmParameterService();
		}
		return localstatic;
	}

	private ParameterServiceInter getParameterService() {
		if (parametersLocal == null) {
			parametersLocal = (ParameterServiceInter) BeanFactory
					.getBean("parameterServiceImpl");
		}
		return parametersLocal;
	}

	private DictionaryEntityServiceInter getDictionaryEntityService() {
		if (dictionaryentitysLocal == null) {
			dictionaryentitysLocal = (DictionaryEntityServiceInter) BeanFactory
					.getBean("dictionaryEntityServiceImpl");
		}
		return dictionaryentitysLocal;
	}

	public void setPropertiesFiles(List<String> propertiesFiles) {
		for (String name : propertiesFiles) {
			if (PropertiesFileService.registConstant(name)) {
				System.out
						.println("注册配置文件:"
								+ name
								+ ".properties(com.farm.parameter.FarmParameterService)");
			}
		}
	}

	

	@Override
	public Map<String, String> getDictionary(String key) {
		return getDictionaryEntityService().getDictionary(key);
	}

	@Override
	public List<Entry<String, String>> getDictionaryList(String key) {
		return getDictionaryEntityService().getDictionaryList(key);
	}

	@Override
	public String getParameter(String key) {
		key = key.trim();
		// 先找用户参数和系统参数
		String value = getParameterService().getValue(key);
		if (value != null) {
			return value;
		}
		// 再找properties文件参数
		value = PropertiesFileService.getValue(key);
		if (value != null) {
			return value;
		}
		// 找常量
		value = ConstantVarService.getValue(key);
		if (value != null) {
			return value;
		}
		throw new RuntimeException("无法获得参数:" + key);
	}

	@Override
	public String getParameter(String key, String userId) {
		String value = getParameterService().getValue(key, userId);
		if (value != null) {
			return value;
		}
		// 再找properties文件参数
		value = PropertiesFileService.getValue(key);
		if (value != null) {
			return value;
		}
		// 找常量
		value = ConstantVarService.getValue(key);
		if (value != null) {
			return value;
		}
		throw new RuntimeException("无法获得参数:" + key);
	}
}
