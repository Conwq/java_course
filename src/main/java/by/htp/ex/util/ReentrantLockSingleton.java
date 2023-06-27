package by.htp.ex.util;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSingleton extends ReentrantLock {

	private static final ReentrantLockSingleton instance = new ReentrantLockSingleton();

	private ReentrantLockSingleton(){
		super();
	}

	public static ReentrantLockSingleton getInstance(){
		return instance;
	}
}
