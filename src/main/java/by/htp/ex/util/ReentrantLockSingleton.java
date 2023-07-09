package by.htp.ex.util;

import java.util.concurrent.locks.ReentrantLock;

public final class ReentrantLockSingleton {
	private static final ReentrantLockSingleton instance = new ReentrantLockSingleton();
	private static final ReentrantLock reentrantLock = new ReentrantLock();

	private ReentrantLockSingleton(){
	}

	public static ReentrantLockSingleton getInstance(){
		return instance;
	}

	public ReentrantLock getReentrantLock(){
		return reentrantLock;
	}
}
