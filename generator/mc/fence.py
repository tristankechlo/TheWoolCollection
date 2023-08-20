from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import RecipeTemplate, RecoloringRecipeTemplate, Template

"""
=> Recipe
can be crafted inside the wool_processor and can be recolored from other wool fences

=> Advancement
white fence has a separate advancement, triggerd by crafting white wool
the other colors have a shared advancement, triggerd by crafting a white fence

=> Loot table
drops itself
"""


class WoolFence (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "fence")
        self.advancement = Advancement(self.full_id, self.color, self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["fence", "blockstate.json"])
        self.item_model = Template(self, ["fence", "item_model.json"])
        self.recipe_creating = RecipeTemplate(self, 1)
        self.recipe_recoloring = RecoloringRecipeTemplate(self)

    def createSpecialTemplates(self):
        self.model_post = Template(self, ["fence", "block_model.json"])
        self.model_side = Template(self, ["fence", "block_model.json"])
        self.model_inventory = Template(self, ["fence", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model_post.replace("%parent%", "minecraft:block/fence_post")
        self.model_side.replace("%parent%", "minecraft:block/fence_side")
        self.model_inventory.replace("%parent%", "minecraft:block/fence_inventory")

    def save(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.advancement.save("decorations")
        self.model_post.save(WoolBlock.path_block_models, self.name + "_post.json")
        self.model_side.save(WoolBlock.path_block_models, self.name + "_side.json")
        self.model_inventory.save(WoolBlock.path_block_models, self.name + "_inventory.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe_creating.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_recoloring.save(WoolBlock.path_recipes, self.name + "_recoloring.json")
        self.loot_table.save()
