package com.hex.wetech.core.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hex.wetech.core.commons.valid.Create;
import com.hex.wetech.core.commons.valid.Query;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * EventTO
 *
 * @author Guofeng Lin
 * @since 2023/10/6
 */
@Data
public class EventTO {
    @NotNull(groups = {Create.class, Query.class}, message = "userId is required when creating event")
    private String userId;
    private String eventId;
    private String eventName;
    private String eventDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private String eventDate;
    private String eventLocation;
    private Integer eventStatus;
    private String eventOwnerId;
}
