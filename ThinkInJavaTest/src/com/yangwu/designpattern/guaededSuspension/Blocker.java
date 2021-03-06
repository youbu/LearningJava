package com.yangwu.designpattern.guaededSuspension;

import java.util.concurrent.Callable;

public interface Blocker {
	/**
	 * 在保护条件成立时执行动作，否则阻塞当前线程，直到条件成立
	 * @param guardedAction
	 * @return
	 * @throws Exception
	 */
	<V>V callWithGuard(GuardedAction<V>guardedAction)throws Exception;
	
	/**
	 * 执行stateOperation所指定的操作后，决定是否唤醒本Blocker
	 * 所暂挂的所有线程中的一个线程
	 * @param stateOperation
	 * 					更改状态的操作，返回值为true时，该方法才会唤醒暂挂的线程
	 * @throws Exception
	 */
	void signalAfter(Callable<Boolean>stateOperation)throws Exception;
	
	void signal() throws InterruptedException;
	
	/**
	 * 	执行stateOperation所指定的操作后，决定是否唤醒本Blocker
	 * 所暂挂的所有线程
	 * @param stateOperation
	 * 	  					更改状态的操作，返回值为true时，该方法才会唤醒暂挂的线程
	 * @throws Exception
	 */
	void broadcastAfter(Callable<Boolean>stateOperation)throws Exception;
}
