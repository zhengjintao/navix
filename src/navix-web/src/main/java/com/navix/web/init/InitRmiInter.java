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
			if(FarmParameterService.getInstance().getParameter("rmi.config.local.state").toUpperCase().equals("FALSE")){
				log.info("RMI服务未配置");
				return;
			}
			
			log.info("RMI服务启动开始");
			int port = Integer.valueOf(FarmParameterService.getInstance().getParameter("rmi.config.local.port"));
			String rui = "rmi://127.0.0.1:" + port + "/navixapp";
			NavixAppInter wda = new NavixAppImpl();
			LocateRegistry.createRegistry(port);
			Naming.rebind(rui, wda);
			log.info("RMI服务启动结束");
		} catch (RemoteException e) {
			log.error("创建远程对象发生异常！");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			log.error("发生重复绑定对象异常！");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			log.error("发生URL畸形异常！");
			e.printStackTrace();
		}

	}

}
