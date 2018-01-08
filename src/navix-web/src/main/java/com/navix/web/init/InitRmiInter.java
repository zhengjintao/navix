package com.navix.web.init;

import java.net.MalformedURLException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.navix.parameter.FarmParameterService;
import com.navix.api.NavixAppInter;
import com.navix.core.web.task.ServletInitJobInter;
import com.navix.web.rmi.impl.NavixAppImpl;

public class InitRmiInter implements ServletInitJobInter {
	private static final Logger log = Logger.getLogger(InitRmiInter.class);
	@Override
	public void execute(ServletContext context) {
		try {
			if(FarmParameterService.getInstance().getParameter("config.local.rmi.state").toUpperCase().equals("FALSE")){
				return;
			}
			int port = Integer.valueOf(FarmParameterService.getInstance().getParameter("config.local.rmi.port"));
			String rui = "rmi://127.0.0.1:" + port + "/navixapp";
			NavixAppInter wda = new NavixAppImpl();
			LocateRegistry.createRegistry(port);
			Naming.rebind(rui, wda);
			log.info("启动RMI服务" + rui);
		} catch (RemoteException e) {
			System.out.println("创建远程对象发生异常！");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.out.println("发生重复绑定对象异常！");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("发生URL畸形异常！");
			e.printStackTrace();
		}

	}

}
