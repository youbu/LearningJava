package com.yangwu.ThreadPool;

import java.lang.Thread.State;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FiexSizeThreadPool {
	// 仓库
	private BlockingQueue<Runnable> taskQueue;

	// 线程集合
	private List<Worker> worker;

	private boolean working = true;

	public FiexSizeThreadPool(int size, int taskQueueSize) {
		if (size <= 0 || taskQueueSize <= 0) {
			throw new IllegalArgumentException("参数错误");
		}

		this.taskQueue = new LinkedBlockingQueue<>(taskQueueSize);

		this.worker = Collections.synchronizedList(new LinkedList());

		// 创建线程
		for (int i = 0; i < size; i++) {
			Worker w = new Worker(this);
			this.worker.add(w);
			w.start();

		}

	}

	public boolean submit(Runnable task) {
		return this.taskQueue.offer(task);
	}

	public void shutDown() {
		System.out.println("sss");
		this.working = false;

		for (Thread w : this.worker) {
			if (w.getState().equals(State.BLOCKED) || w.getState().equals(State.WAITING)) {
				w.interrupt();
			}
		}
	}

	private class Worker extends Thread {
		private FiexSizeThreadPool pool;

		public Worker(FiexSizeThreadPool pool) {
			super();
			this.pool = pool;
		}

		public void run() {
			while (this.pool.working || this.pool.taskQueue.size() > 0) {
				Runnable task = null;

				try {
					task = this.pool.taskQueue.take();
					if (task != null) {
						task.run();
						System.out.println(Thread.currentThread().getName());
						Thread.sleep(3000L);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
			System.out.println("线程"+ Thread.currentThread().getName() + "完成");
		}
	}

	public static void main(String[] args) {
		FiexSizeThreadPool pool = new FiexSizeThreadPool(3, 5);
		for (int i = 0; i < 5; i++) {
			pool.submit(new Runnable() {

				@Override
				public void run() {
					System.out.println("任务开始");

					try {
						Thread.sleep(2000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		pool.shutDown();
	}
}
