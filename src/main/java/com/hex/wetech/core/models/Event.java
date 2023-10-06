package com.hex.wetech.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Event
 *
 * @author Guofeng Lin
 * @since 2023/10/6
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Event {
    private String eventId;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private String eventLocation;
    private Integer eventStatus;
    private String eventOwnerId;
}
