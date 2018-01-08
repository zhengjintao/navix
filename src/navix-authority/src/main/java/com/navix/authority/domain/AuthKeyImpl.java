package com.navix.authority.domain;

import com.navix.core.auth.domain.AuthKey;

public class AuthKeyImpl implements AuthKey {
	private boolean ischeck;
	private boolean islogin;
	private boolean isUseAble;
	private String title;

	@Override
	public boolean isCheck() {
		return ischeck;
	}

	@Override
	public boolean isLogin() {
		return islogin;
	}

	@Override
	public boolean isUseAble() {
		return isUseAble;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}

	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}

	public void setUseAble(boolean isUseAble) {
		this.isUseAble = isUseAble;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
