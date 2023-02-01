package com.tristankechlo.more_wool_blocks;

import com.tristankechlo.more_wool_blocks.init.ModRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod(MoreWoolBlocks.MOD_ID)
public class ForgeMoreWoolBlocks {

    public ForgeMoreWoolBlocks() {
        ModRegistry.loadClass(); // load ModRegistry to register everything
    }

}