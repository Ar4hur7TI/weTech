package com.hex.wetech.controller;

import com.hex.wetech.core.models.R;
import com.hex.wetech.core.to.EventTO;
import com.hex.wetech.core.to.I18NTO;
import com.hex.wetech.utils.CacheMapUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class I18NController {

    private static final String  CACHE_KEY = "I18N";
    @PostMapping("/getI18N")
    public R getI18N(@RequestBody I18NTO to) {
        I18NTO result = (I18NTO) CacheMapUtil.get(CACHE_KEY, to.getI18N());
        return R.ok(result);
    }

    @PostMapping("/switchEvent")
    public R switchEvent(@RequestBody I18NTO to, HttpServletRequest request) {
        I18NTO result = (I18NTO) CacheMapUtil.get(CACHE_KEY, to.getI18N());
        if (result != null) {
            return R.error("event already exists");
        }
        CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put(to.getI18N(), to);
        return R.ok("created");
    }
}
