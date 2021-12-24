package com.heo.dae.won.slackmessage.vo;

import com.heo.dae.won.slackmessage.enums.BlockType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block {
    private BlockType type;
    private Text text;
    private Field field;

    @Builder
    public Block(BlockType type, Text text){
        this.type = type;
        this.text = text;
    }
}
