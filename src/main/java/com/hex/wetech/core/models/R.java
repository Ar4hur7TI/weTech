package com.hex.wetech.core.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.HashMap;


/**
 * R
 *
 * @author Guofeng Lin
 * @since 2023/10/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class R extends HashMap<String, Object> implements java.io.Serializable {
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";
    private static final long serialVersionUID = 1L;

    public R() {
    }

    public R(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (null != data) {
            super.put(DATA_TAG, data);
        }
    }

    public static R ok() {
        return R.ok("ok");
    }

    public static R ok(Object data) {
        return R.ok("ok", data);
    }

    public static R ok(String msg) {
        return R.ok(msg, null);
    }

    public static R ok(String msg, Object data) {
        return new R(HttpStatus.OK.value(), msg, data);
    }

    public static R error() {
        return R.error("error");
    }

    public static R error(HttpStatusCode status, String msg) {
        return R.error(status.value(), msg);
    }

    public static R error(String msg) {
        return R.error(msg, null);
    }

    public static R error(String msg, Object data) {
        return new R(HttpStatus.FORBIDDEN.value(), msg, data);
    }

    public static R error(int code, String msg) {
        return new R(code, msg, null);
    }

    public String getMsg() {
        return (String) this.get(R.MSG_TAG);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public int getCode() {
        return (int) this.get(R.CODE_TAG);
    }
}
