package com.evael.community.common.util;

import java.util.Random;

public class IdWorker {

	private final long workerId;
	private final static long twepoch = 1361753741828L;
	private long sequence = 0L;
	private final static long workerIdBits = 4L;
	public final static long maxWorkerId = ~(-1L << workerIdBits);
	private final static long sequenceBits = 10L;
	private final static long workerIdShift = sequenceBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	public final static long sequenceMask = ~(-1L << sequenceBits);
	private long lastTimestamp = -1L;

	private static IdWorker instance = null;

	public static long randomId(){
		return getRandomInstance().nextId();
	}

	public static IdWorker getRandomInstance(){
		return getInstance(new Random().nextInt(16));
	}

	public static IdWorker getInstance(long workId) {
		if (instance == null) {
			synchronized (IdWorker.class) {
				if (instance == null) {
					instance = new IdWorker(workId);
				}
			}
		}
		return instance;
	}

	public IdWorker(final long workerId) {
		super();
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0",
					maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & sequenceMask;
			if (this.sequence == 0) {
				System.out.println("###########" + sequenceMask);
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				throw new Exception(
						String.format(
								"Clock moved backwards.  Refusing to generate id for %d milliseconds",
								this.lastTimestamp - timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.lastTimestamp = timestamp;

		return ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << workerIdShift) | (this.sequence);
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			System.out.println(IdWorker.getInstance(14).nextId());
		}
	}

}
