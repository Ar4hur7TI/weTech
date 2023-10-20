package com.hex.wetech.core.to;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String language;
}
