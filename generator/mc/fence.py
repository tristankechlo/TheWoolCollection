from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template
from mc.globals import Globals

"""
=> Recipe
white fence can be crafted from white wool, similar to normal fences,
but the other colors can only be crafted from white fences and a dye

=> Advancement
white fence has a separate advancement, triggerd by crafting white wool
the other colors have a shared advancement, triggerd by crafting a white fence

=> Loot table
drops itself
"""


class WoolFence (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "fence")
        self.advancement_white = Advancement(self.full_id, "minecraft:white_wool", self.name + ".json")
        self.advancement = Advancement(self.full_id, Globals.modid + ":white_wool_fence", self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["fence", "blockstate.json"])
        self.item_model = Template(self, ["fence", "item_model.json"])
        self.recipe = Template(self, ["recipe.json"])

    def createSpecialTemplates(self):
        self.model_post = Template(self, ["fence", "block_model.json"])
        self.model_side = Template(self, ["fence", "block_model.json"])
        self.model_inventory = Template(self, ["fence", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model_post.replace("%parent%", "minecraft:block/fence_post")
        self.model_side.replace("%parent%", "minecraft:block/fence_side")
        self.model_inventory.replace("%parent%", "minecraft:block/fence_inventory")

    def saveForAllVariants(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model_post.save(WoolBlock.path_block_models, self.name + "_post.json")
        self.model_side.save(WoolBlock.path_block_models, self.name + "_side.json")
        self.model_inventory.save(WoolBlock.path_block_models, self.name + "_inventory.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.loot_table.save()

    def saveForWhiteVariant(self):
        self.advancement_white.save("decorations")

    def saveForNonWhiteVariants(self):
        self.recipe.save(WoolBlock.path_recipes, self.name + ".json")
        self.advancement.save("decorations")
