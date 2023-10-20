package com.hex.wetech.controller;

import com.hex.wetech.core.commons.valid.Create;
import com.hex.wetech.core.commons.valid.Query;
import com.hex.wetech.core.models.R;
import com.hex.wetech.core.to.EventTO;
import com.hex.wetech.utils.CacheMapUtil;
import com.hex.wetech.utils.SeqUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EventController
 *
 * @author Guofeng Lin
 * @since 2023/10/6
 */
@RestController
@RequestMapping("/event")
public class EventController {
    private static final String CACHE_KEY = "event";

    @PostMapping("/get")
    public R getEvent(@RequestBody @Validated({Query.class}) EventTO to) {
        ConcurrentHashMap<String, Map<String, EventTO>> map = CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY);
        Map<String, EventTO> eventMap = map.get(to.getUserId());
        if (eventMap == null) {
            return R.error(HttpStatus.NOT_FOUND.name());
        }
        return eventMap.containsKey(to.getEventId()) ? R.ok(eventMap.get(to.getEventId())) : R.error(HttpStatus.NOT_FOUND.name());
    }

    @PostMapping("/list")
    public R listEvent(@RequestBody @Validated({Query.class}) EventTO to) {
        ConcurrentHashMap<String, Map<String, EventTO>> map = CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY);
        Map<String, EventTO> eventMap = map.get(to.getUserId());
        if (eventMap == null) {
            return R.error(HttpStatus.NOT_FOUND.name());
        }
        return R.ok(new ArrayList<>(eventMap.values()));
    }

    @PostMapping("/new")
    public R newEvent(@RequestBody @Validated({Create.class}) EventTO to, HttpServletRequest request) {
        ConcurrentHashMap<String, Map<String, EventTO>> map = CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY);
        Map<String, EventTO> eventMap = map.getOrDefault(to.getUserId(), new HashMap<>(16));
        if (eventMap.size() > 16) {
            return R.error("more than 16 events, please subscribe our premium service");
        }
        to.setEventId(SeqUtils.getId());
        eventMap.put(to.getEventId(), to);
        CacheMapUtil.set(CACHE_KEY, to.getUserId(), eventMap);
        return R.ok(to.getEventId());
    }
}
