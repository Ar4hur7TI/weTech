package com.hex.wetech.core.to;

import lombok.Data;

/**
 * EventTO
 *
 * @author Guofeng Lin
 * @since 2023/10/6
 */
@Data
public class EventTO {
    private String eventId;
    private String eventName;
    private String eventDescription;
    private String eventDate;
    private String eventLocation;
    private Integer eventStatus;
    private String eventOwnerId;
}
