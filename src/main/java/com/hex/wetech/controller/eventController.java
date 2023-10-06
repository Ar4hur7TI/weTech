package com.hex.wetech.controller;

import com.hex.wetech.core.models.R;
import com.hex.wetech.core.to.EventTO;
import com.hex.wetech.utils.CacheMapUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * eventController
 *
 * @author Guofeng Lin
 * @since 2023/10/6
 */
@RestController
@RequestMapping("/event")
public class eventController {
    private static final String  CACHE_KEY = "event";

    @PostMapping("/getEvent")
    public R getEvent(@RequestBody EventTO to) {
        EventTO result = (EventTO) CacheMapUtil.get(CACHE_KEY, to.getEventId());
        System.out.println(result);
        return R.ok(result);
    }

    @PostMapping("/newEvent")
    public R newEvent(@RequestBody EventTO to, HttpServletRequest request) {
        CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY).put(to.getEventId(), to);
        return R.ok("created");
    }
}
