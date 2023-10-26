package com.hex.wetech.controller;

import com.hex.wetech.core.commons.valid.Create;
import com.hex.wetech.core.commons.valid.Query;
import com.hex.wetech.core.models.R;
import com.hex.wetech.core.to.EventTO;
import com.hex.wetech.service.IFileService;
import com.hex.wetech.utils.CacheMapUtil;
import com.hex.wetech.utils.SeqUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
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
    private final IFileService fileService;

    public EventController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("get")
    public R getEvent(@RequestBody @Validated({Query.class}) EventTO to) {
        Map<String, EventTO> eventMap = getEventMap(to.getUserId());
        return to.getEventId() != null && eventMap.containsKey(to.getEventId()) ?
                R.ok(eventMap.get(to.getEventId())) : R.ok(new ArrayList<>(getEventMap(to.getUserId()).values()));
    }

//    @PostMapping("list")
//    public R listEvent(@RequestBody @Validated({Query.class}) EventTO to) {
//        return R.ok(new ArrayList<>(getEventMap(to.getUserId()).values()));
//    }

    @PostMapping("new")
    public R newEvent(@RequestBody @Validated({Create.class}) EventTO to) {
        ConcurrentHashMap<String, Map<String, EventTO>> map = CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY);
        Map<String, EventTO> eventMap = map.getOrDefault(to.getUserId(), new HashMap<>(16));
        if (eventMap.size() > 16) {
            return R.error("more than 16 events, please subscribe our premium service");
        }
        if (fileService.createEventFile(to.getUserId(), to.getEventName())) {
            to.setEventId(SeqUtils.getId());
            eventMap.put(to.getEventId(), to);
            CacheMapUtil.set(CACHE_KEY, to.getUserId(), eventMap);
            return R.ok(to.getEventId());
        }
        return R.error("create event failed, you might enter same event name");
    }

    private Map<String, EventTO> getEventMap(String userId) {
        ConcurrentHashMap<String, Map<String, EventTO>> map = CacheMapUtil.newCacheMapIfAbsent(CACHE_KEY);
        Map<String, EventTO> eventMap = map.get(userId);
        return eventMap == null ? Collections.emptyMap() : eventMap;
    }
}
