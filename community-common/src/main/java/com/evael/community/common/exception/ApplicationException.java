package com.evael.community.common.exception;

/**
 * App Base Exception
 *
 * @author wanguohui
 *
 * X XX XX
 *
 * 第1位(区分异常类型):
 * - 1: 系统异常
 * - 3: 程序异常
 * - 5: 业务异常
 *
 * 第2,3位(区分模块):
 * - 01: 用户
 * - 02: 群组
 * - 03: 文件系统
 * - 04: 帖子
 * - 05: Session Manager
 * - 06: transcoding
 * - 07: Preview Service
 * - 08: Post
 * - 09: Task
 * - 10: Organization
 * - 11: community-cloud provider
 * - 12: community-nodemgr
 * - 13: community-session
 * - 14: community-whitelist
 * 第4,5位(具体异常编号)
 *
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -6485405088144036307L;

    protected int errorCode = 500;
    private Exception ex;

    public ApplicationException() {
    }

    public ApplicationException(Throwable t) {
        super(t);
    }

    public ApplicationException(int errorCode) {
        this.errorCode = errorCode;
    }

    public ApplicationException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }
    public ApplicationException(int errorCode, Exception ex, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.ex=ex;
    }

    public ApplicationException(int errorCode, String msg, Throwable t) {
        super(msg, t);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
