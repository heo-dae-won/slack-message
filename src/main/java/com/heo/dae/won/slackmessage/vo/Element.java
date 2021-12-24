package com.heo.dae.won.slackmessage.vo;

import com.heo.dae.won.slackmessage.enums.ElementType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Element {
    ElementType type;
    Text text;
    String style;
    String value;
}
