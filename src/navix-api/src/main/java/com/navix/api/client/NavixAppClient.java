package com.navix.api.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.navix.api.NavixAppInter;

public class NavixAppClient {
	/**
	 * 执行navix接口
	 * 
	 * @param rmiUrl
	 *            rmi://127.0.0.1:8701/navixapp
	 * @return
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static NavixAppInter getServer(String rmiUrl)
			throws MalformedURLException, RemoteException, NotBoundException {
		NavixAppInter navixApp = (NavixAppInter) Naming.lookup(rmiUrl);
		return navixApp;
	}
}
