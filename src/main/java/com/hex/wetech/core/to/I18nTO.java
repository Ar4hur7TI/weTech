package com.hex.wetech.core.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * EventTO
 *
 * @author Guofeng Lin
 * @since 2023/10/6
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class I18nTO {
    @NotNull
    private String language;
}
