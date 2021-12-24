package com.heo.dae.won.slackmessage.vo;

import com.heo.dae.won.slackmessage.enums.TextType;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Text {
    TextType type;
    String text;
    boolean emoji;
}
