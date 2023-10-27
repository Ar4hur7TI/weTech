package com.hex.wetech.controller;

import com.hex.wetech.core.models.R;
import com.hex.wetech.core.to.I18nTO;
import com.hex.wetech.utils.CacheMapUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/i18n")
public class I18NController {

    private static final String CACHE_KEY = "I18N";
    private static final String I18N_KEY = "now";

    @PostMapping("/get")
    public R getI18N() {
        String result = (String) CacheMapUtil.get(CACHE_KEY, I18N_KEY);
        if (result == null) {
            CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put(I18N_KEY, "English");
            result = (String) CacheMapUtil.get(CACHE_KEY, I18N_KEY);
        }
        return R.ok(result);
    }

    @PostMapping("/switch")
    public R switch18N(@RequestBody @Validated I18nTO to) {
        CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put(I18N_KEY, to.getLanguage());
        return R.ok(to.getLanguage());
    }
}
