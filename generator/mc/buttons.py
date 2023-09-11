from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import RecipeTemplate, RecoloringRecipeTemplate, EmptyRecipeTemplate, Template
from os.path import join
from mc.globals import Globals

"""
=> Recipe
can be crafted inside the weaving_station and can be recolored from other wool buttons

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool

=> Loot table
drops itself
"""


class WoolButtons (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "button")
        self.advancement = Advancement(self.full_id, self.color, self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["buttons", "blockstate.json"])
        self.item_model = Template(self, ["buttons", "item_model.json"])
        self.recipe_creating = RecipeTemplate(self, 4)
        self.recipe_creating_empty = EmptyRecipeTemplate(self, 4)
        self.recipe_recoloring = RecoloringRecipeTemplate(self)

    def createSpecialTemplates(self):
        self.model = Template(self, ["buttons", "block_model.json"])
        self.model_pressed = Template(self, ["buttons", "block_model.json"])
        self.model_inventory = Template(self, ["buttons", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/button")
        self.model_pressed.replace("%parent%", "minecraft:block/button_pressed")
        self.model_inventory.replace("%parent%", "minecraft:block/button_inventory")

    def save(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_pressed.save(WoolBlock.path_block_models, self.name + "_pressed.json")
        self.model_inventory.save(WoolBlock.path_block_models, self.name + "_inventory.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe_creating.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_creating_empty.save(WoolBlock.path_recipes, self.name + "_empty.json")
        self.recipe_recoloring.save(WoolBlock.path_recipes, self.name + "_recoloring.json")
        self.advancement.save("redstone")
        self.loot_table.save()
