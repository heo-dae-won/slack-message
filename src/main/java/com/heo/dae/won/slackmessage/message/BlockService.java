package com.heo.dae.won.slackmessage.message;

import com.heo.dae.won.slackmessage.enums.BlockType;
import com.heo.dae.won.slackmessage.enums.TextType;
import com.heo.dae.won.slackmessage.vo.Block;
import com.heo.dae.won.slackmessage.vo.BlockList;
import com.heo.dae.won.slackmessage.vo.Text;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockService {

    public List<Block> createBlockList() {
        List<Block> blocks = new ArrayList<>();

        Text text = new Text();
        text.setType(TextType.plain_text);
        text.setText("Today health-check");

        Block block1 = Block.builder()
                .type(BlockType.header)
                .text(text)
                .build();

        blocks.add(block1);

        return blocks;
    }
}
