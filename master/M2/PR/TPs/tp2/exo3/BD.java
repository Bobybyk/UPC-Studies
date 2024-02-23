package exo3;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BD {
	int tab[];
	ReadWriteLock lock;

	public BD(int l) {
		tab = new int[l];
		lock = new ReentrantReadWriteLock(true);
	}
}