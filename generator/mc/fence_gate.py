from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template


class WoolFenceGate (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "fence_gate")
        self.advancement_white = Advancement(self.full_id, "minecraft:white_wool", self.name + ".json")
        self.advancement = Advancement(self.full_id, "more_wool_blocks:white_wool_fence_gate", self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["fence_gate", "blockstate.json"])
        self.model = Template(self, ["fence_gate", "block_model.json"])
        self.model_open = Template(self, ["fence_gate", "block_model_open.json"])
        self.model_wall = Template(self, ["fence_gate", "block_model_wall.json"])
        self.model_wall_open = Template(self, ["fence_gate", "block_model_wall_open.json"])
        self.item_model = Template(self, ["fence_gate", "item_model.json"])
        self.recipe = Template(self, ["recipe.json"])

    def saveForAllVariants(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_open.save(WoolBlock.path_block_models, self.name + "_open.json")
        self.model_wall.save(WoolBlock.path_block_models, self.name + "_wall.json")
        self.model_wall_open.save(WoolBlock.path_block_models, self.name + "_wall_open.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.loot_table.save()

    def saveForWhiteVariant(self):
        self.advancement_white.save()

    def saveForNonWhiteVariants(self):
        self.recipe.save(WoolBlock.path_recipes, self.name + ".json")
        self.advancement.save()
