package com.navix.core.sql.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件的封装类
 */
public class DBRuleList {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private List<DBRule> list = new ArrayList<DBRule>();

	public DBRuleList() {
	}

	public DBRuleList add(DBRule rule) {
		list.add(rule);
		return this;
	}

	public List<DBRule> toList() {
		return list;
	}
}
