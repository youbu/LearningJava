package com.yangwu.designpattern.guaededSuspension;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class AlertAgent {
	// 是否连接服务器
	private volatile boolean connectedToServer = false;

	// 模式角色 GuardedSuspension.Predicate
	private final Predicate agentConnected = new Predicate() {

		@Override
		public boolean evaluate() {
			return connectedToServer;
		}
	};

	// 模式角色：GuardedSuspension.Blocker
	private final Blocker blocker = new ConditionVarBlocker();

	// 心跳定时器
	private final Timer heartbeatTimer = new Timer(true);

	public void sendAlerm(String alerm) throws Exception {
		GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {

			@Override
			public Void call() throws Exception {
				doSendAlerm(alerm);
				return null;
			}
		};
		blocker.callWithGuard(guardedAction);
	}

	private void doSendAlerm(String alerm) {
		System.out.println("sending alerm : " + alerm);
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		Thread connectingThread = new Thread(new ConnectingTask());

//		connectingThread.start();

		heartbeatTimer.schedule(new HeartBeatTask(), 6000, 2000);
	}

	public void discinnect() {
		System.out.println("disconnected from server !");
		connectedToServer = false;
	}

	public void onConnected() {
		try {
			System.out.println("onConnected");
			blocker.signalAfter(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					Thread.sleep(10000);
					connectedToServer = true;
					System.out.println("sonnected to Server !");
					return Boolean.TRUE;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onDisconnected() {
		connectedToServer = false;
	}

	private class ConnectingTask implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			onConnected();
		}
	}

	private class HeartBeatTask extends TimerTask {

		@Override
		public void run() {
			if (testConnection()) {
				onDisconnected();
				reConnected();
			}
		}

		private boolean testConnection() {
			return true;
		}

		private void reConnected() {
			ConnectingTask connectingTask = new ConnectingTask();
			connectingTask.run();
		}
	}

	public static void main(String[] args) throws Exception {
		AlertAgent agent = new AlertAgent();
		agent.init();
		agent.sendAlerm("alerm! alerm!");
	}
}
