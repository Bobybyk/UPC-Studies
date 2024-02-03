package exo5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class MonReadWriteLock implements ReadWriteLock {
	private final MonReadWriteLock.ReadLock readerLock;
	private final MonReadWriteLock.WriteLock writerLock;
	private final Sync sync;

	@Override
	public MonReadWriteLock.WriteLock writeLock() {
		return writerLock;
	}

	@Override
	public MonReadWriteLock.ReadLock readLock() {
		return readerLock;
	}

	public MonReadWriteLock() {
		sync = new Sync();
		readerLock = new MonReadWriteLock.ReadLock(this);
		writerLock = new MonReadWriteLock.WriteLock(this);
	}

	final static class Sync {
		private int readers = 0;
		private int writers = 0;
		private boolean priority = false;

		public synchronized void lockR() {
			while (writers > 0 || priority) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			readers++;
		}

		public synchronized void unlockR() {
			readers--;
			notifyAll();
		}

		public synchronized void lockW() {
			while (readers > 0 || writers > 0) {
				try {
					priority = true;
					wait();
				} catch (InterruptedException e) {
				}
			}
			writers++;
		}

		public synchronized void unlockW() {
			priority = false;
			writers--;
			notifyAll();
		}
	}

	public static class ReadLock implements Lock {
		private final Sync sync;

		protected ReadLock(MonReadWriteLock lock) {
			sync = lock.sync;
		}

		@Override
		public void lock() {
			sync.lockR();
		}

		@Override
		public void unlock() {
			sync.unlockR();
		}

		@Override
		public Condition newCondition() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean tryLock() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	public static class WriteLock implements Lock {
		private final Sync sync;

		protected WriteLock(MonReadWriteLock lock) {
			sync = lock.sync;
		}

		@Override
		public void lock() {
			sync.lockW();
		}

		@Override
		public void unlock() {
			sync.unlockW();
		}

		@Override
		public Condition newCondition() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean tryLock() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
