package com.fengchaoli.ucenter.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author LIQIU
 * @date 2018-3-31
 **/
public class BadVerificatioinCodeException extends AuthenticationException {

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public BadVerificatioinCodeException(String msg) {
        super(msg);
    }

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public BadVerificatioinCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
