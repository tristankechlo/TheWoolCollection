from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import RecipeTemplate, RecoloringRecipeTemplate, Template, EmptyRecipeTemplate


"""
=> Recipe
can be crafted inside the weaving_station and can be recolored from other wool slabs

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool
both recipe types (crafting, stonecutter) are triggered

=> Loot table
drops like normal slabs, one slab per block, or two slabs per double slab
"""


class WoolSlabs (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "slab")
        self.advancement = Advancement(self.full_id, self.color, self.name + ".json")
        self.loot_table = Template(self, ["slabs", "loot_table.json"])
        self.blockstate = Template(self, ["slabs", "blockstate.json"])
        self.item_model = Template(self, ["slabs", "item_model.json"])
        self.recipe_creating = RecipeTemplate(self, 2)
        self.recipe_creating_empty = EmptyRecipeTemplate(self, 2)
        self.recipe_recoloring = RecoloringRecipeTemplate(self)

    def createSpecialTemplates(self):
        self.model = Template(self, ["slabs", "block_model.json"])
        self.model_top = Template(self, ["slabs", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/slab")
        self.model_top.replace("%parent%", "minecraft:block/slab_top")

    def save(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_top.save(WoolBlock.path_block_models, self.name + "_top.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe_creating.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_creating_empty.save(WoolBlock.path_recipes, self.name + "_empty.json")
        self.recipe_recoloring.save(WoolBlock.path_recipes, self.name + "_recoloring.json")
        self.advancement.save()
        self.loot_table.save(LootTable.path, self.name + ".json")
