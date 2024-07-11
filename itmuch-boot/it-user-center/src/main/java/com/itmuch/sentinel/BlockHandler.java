package com.itmuch.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockHandler {
    public static String blockHandler(String a, BlockException e) {
        return "blockHandler";
    }
}
