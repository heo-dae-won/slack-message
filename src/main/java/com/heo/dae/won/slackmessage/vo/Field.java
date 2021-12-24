package com.heo.dae.won.slackmessage.vo;

import com.heo.dae.won.slackmessage.enums.FieldType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Field {
    FieldType type;
    Text text;
}
