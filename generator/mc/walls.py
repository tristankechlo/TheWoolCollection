from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import RecipeTemplate, RecoloringRecipeTemplate, Template, EmptyRecipeTemplate


"""
=> Recipe
can be crafted inside the wool_processor and can be recolored from other wool walls

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool
both recipe types (crafting, stonecutter) are triggered

=> Loot table
drops itself
"""


class WoolWalls (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "wall")
        self.advancement = Advancement(self.full_id, self.color, self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["walls", "blockstate.json"])
        self.item_model = Template(self, ["walls", "item_model.json"])
        self.recipe_creating = RecipeTemplate(self, 1)
        self.recipe_creating_empty = EmptyRecipeTemplate(self, 1)
        self.recipe_recoloring = RecoloringRecipeTemplate(self)

    def createSpecialTemplates(self):
        self.model_post = Template(self, ["walls", "block_model.json"])
        self.model_side = Template(self, ["walls", "block_model.json"])
        self.model_side_tall = Template(self, ["walls", "block_model.json"])
        self.model_inventory = Template(self, ["walls", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model_post.replace("%parent%", "minecraft:block/template_wall_post")
        self.model_side.replace("%parent%", "minecraft:block/template_wall_side")
        self.model_side_tall.replace("%parent%", "minecraft:block/template_wall_side_tall")
        self.model_inventory.replace("%parent%", "minecraft:block/wall_inventory")

    def save(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model_post.save(WoolBlock.path_block_models, self.name + "_post.json")
        self.model_side.save(WoolBlock.path_block_models, self.name + "_side.json")
        self.model_side_tall.save(WoolBlock.path_block_models, self.name + "_side_tall.json")
        self.model_inventory.save(WoolBlock.path_block_models, self.name + "_inventory.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe_creating.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_creating_empty.save(WoolBlock.path_recipes, self.name + "_empty.json")
        self.recipe_recoloring.save(WoolBlock.path_recipes, self.name + "_recoloring.json")
        self.advancement.save("decorations")
        self.loot_table.save()
