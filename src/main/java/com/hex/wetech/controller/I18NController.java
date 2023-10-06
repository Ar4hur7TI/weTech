package com.hex.wetech.controller;

import com.hex.wetech.core.models.R;
import com.hex.wetech.core.to.I18NTO;
import com.hex.wetech.utils.CacheMapUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/I18N")
public class I18NController {

    private static final String CACHE_KEY = "I18N";

    @PostMapping("/getI18N")
    public R getI18N() {
        String result = (String) CacheMapUtil.get(CACHE_KEY, "now");
        if (result == null) {
            CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put("now", "English");
            result = (String) CacheMapUtil.get(CACHE_KEY, "now");
        }
        return R.ok(result);
    }

    @PostMapping("/switch18N")
    public R switch18N(@RequestBody I18NTO to, HttpServletRequest request) {
        String result = (String) CacheMapUtil.get(CACHE_KEY, "now");
        CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put("now", to.getI18N());
        return R.ok(to.getI18N());
    }
}
