package com.yangwu.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(5);

		List<Future<String>> list = new ArrayList<Future<String>>();

		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
//					Thread.sleep(3000L);
					return "Ïß³Ì £º"+Thread.currentThread().getName() ;
				}
			}));
		}

		for (Future<String> future : list) {

			System.out.println(future.isDone());

			try {
				System.out.println(future.get());
//				pool.shutdown();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();

	}

}
