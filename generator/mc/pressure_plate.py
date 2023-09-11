from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import RecipeTemplate, RecoloringRecipeTemplate, Template, EmptyRecipeTemplate


"""
=> Recipe
can be crafted inside the weaving_station and can be recolored from other wool pressure plates

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool

=> Loot table
drops itself
"""

class WoolPressurePlate (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "pressure_plate")
        self.advancement = Advancement(self.full_id, self.color, self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["pressure_plates", "blockstate.json"])
        self.item_model = Template(self, ["pressure_plates", "item_model.json"])
        self.recipe_creating = RecipeTemplate(self, 2)
        self.recipe_creating_empty = EmptyRecipeTemplate(self, 2)
        self.recipe_recoloring = RecoloringRecipeTemplate(self)

    def createSpecialTemplates(self):
        self.model = Template(self, ["pressure_plates", "block_model.json"])
        self.model_down = Template(self, ["pressure_plates", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/pressure_plate_up")
        self.model_down.replace("%parent%", "minecraft:block/pressure_plate_down")

    def save(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_down.save(WoolBlock.path_block_models, self.name + "_down.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe_creating.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_creating_empty.save(WoolBlock.path_recipes, self.name + "_empty.json")
        self.recipe_recoloring.save(WoolBlock.path_recipes, self.name + "_recoloring.json")
        self.advancement.save("redstone")
        self.loot_table.save()
