from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import RecipeTemplate, RecoloringRecipeTemplate, Template, EmptyRecipeTemplate


"""
=> Recipe
can be crafted inside the weaving_station and can be recolored from other wool stairs

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool
both recipe types (crafting, stonecutter) are triggered

=> Loot table
drops itself
"""


class WoolStairs (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "stairs")
        self.advancement = Advancement(self.full_id, self.color, self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["stairs", "blockstate.json"])
        self.item_model = Template(self, ["stairs", "item_model.json"])
        self.recipe_creating = RecipeTemplate(self, 1)
        self.recipe_creating_empty = EmptyRecipeTemplate(self, 1)

    def createSpecialTemplates(self):
        self.model = Template(self, ["stairs", "block_model.json"])
        self.model_inner = Template(self, ["stairs", "block_model.json"])
        self.model_outer = Template(self, ["stairs", "block_model.json"])
        self.recipe_recoloring = RecoloringRecipeTemplate(self)

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/stairs")
        self.model_inner.replace("%parent%", "minecraft:block/inner_stairs")
        self.model_outer.replace("%parent%", "minecraft:block/outer_stairs")
        self.recipe_recoloring.replace("%type%", "stair")

    def save(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_inner.save(WoolBlock.path_block_models, self.name + "_inner.json")
        self.model_outer.save(WoolBlock.path_block_models, self.name + "_outer.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe_creating.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_creating_empty.save(WoolBlock.path_recipes, self.name + "_empty.json")
        self.recipe_recoloring.save(WoolBlock.path_recipes, self.name + "_recoloring.json")
        self.advancement.save()
        self.loot_table.save()
