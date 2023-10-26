package com.hex.wetech.core.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hex.wetech.core.commons.valid.Create;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventTO {
    @NotNull
    private String userId;
    private String eventId;
    @NotNull(groups = {Create.class}, message = "event name cannot be null")
    private String eventName;
    private String eventDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private String eventDate;
    private String eventLocation;
    private Integer eventStatus;
    private String eventOwnerId;
}
