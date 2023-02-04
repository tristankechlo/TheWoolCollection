from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template


"""
=> Recipe
can be crafted from white wool, similar to the other pressure plates,

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool

=> Loot table
drops itself
"""

class WoolPressurePlate (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "pressure_plate")
        trigger = "minecraft:" + self.color + "_wool"
        self.advancement = Advancement(self.full_id, trigger, self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["pressure_plates", "blockstate.json"])
        self.item_model = Template(self, ["pressure_plates", "item_model.json"])
        self.recipe = Template(self, ["pressure_plates", "recipe.json"])

    def createSpecialTemplates(self):
        self.model = Template(self, ["pressure_plates", "block_model.json"])
        self.model_down = Template(self, ["pressure_plates", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/pressure_plate_up")
        self.model_down.replace("%parent%", "minecraft:block/pressure_plate_down")

    def saveForAllVariants(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_down.save(WoolBlock.path_block_models, self.name + "_down.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe.save(WoolBlock.path_recipes, self.name + ".json")
        self.advancement.save("redstone")
        self.loot_table.save()
