from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template
from mc.globals import Globals

"""
=> Recipe
all buttons can be crafted from the corresponding colored wool

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool

=> Loot table
drops itself
"""


class WoolButtons (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "button")
        self.advancement = Advancement(self.full_id, "minecraft:" + self.color + "_wool", self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["buttons", "blockstate.json"])
        self.item_model = Template(self, ["buttons", "item_model.json"])
        self.recipe = Template(self, ["buttons", "recipe.json"])

    def createSpecialTemplates(self):
        self.model = Template(self, ["buttons", "block_model.json"])
        self.model_pressed = Template(self, ["buttons", "block_model.json"])
        self.model_inventory = Template(self, ["buttons", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/button")
        self.model_pressed.replace("%parent%", "minecraft:block/button_pressed")
        self.model_inventory.replace("%parent%", "minecraft:block/button_inventory")

    def saveForAllVariants(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_pressed.save(WoolBlock.path_block_models, self.name + "_pressed.json")
        self.model_inventory.save(WoolBlock.path_block_models, self.name + "_inventory.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe.save(WoolBlock.path_recipes, self.name + ".json")
        self.advancement.save("redstone")
        self.loot_table.save()
