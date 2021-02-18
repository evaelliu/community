package com.evael.community.account.management.domain;

import com.evael.community.common.util.IdWorker;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8707380519636734108L;
	@Id
	protected Long id;
	protected int version;
	protected Date createTime;
	protected Date lastModifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void generateNextId() {
		if (this.id == null) {
			this.id = generateId();
		}
	}

	public long generateId() {
		return IdWorker.randomId();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getVersion() {
		return version;
	}

	public void newVersion() {
		this.version = version + 1;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
