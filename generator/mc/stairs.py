from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template, StoneCuttingTemplate


"""
=> Recipe
can be crafted from white wool, similar to the other stairs
can be crafted from stonecutting

=> Advancement
each color has its own advancement, triggered by crafting the corresponding colored wool
both recipe types (crafting, stonecutter) are triggered

=> Loot table
drops itself
"""


class WoolStairs (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "stairs")
        trigger = "minecraft:" + self.color + "_wool"
        self.advancement = Advancement(self.full_id, trigger, self.name + ".json")
        self.advancement_stonecutting = Advancement(self.full_id + "_from_stonecutting", trigger, self.name + "_from_stonecutting.json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["stairs", "blockstate.json"])
        self.item_model = Template(self, ["stairs", "item_model.json"])
        self.recipe = Template(self, ["stairs", "recipe.json"])
        self.recipe_stonecutting = StoneCuttingTemplate(self, 1)

    def createSpecialTemplates(self):
        self.model = Template(self, ["stairs", "block_model.json"])
        self.model_inner = Template(self, ["stairs", "block_model.json"])
        self.model_outer = Template(self, ["stairs", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/stairs")
        self.model_inner.replace("%parent%", "minecraft:block/inner_stairs")
        self.model_outer.replace("%parent%", "minecraft:block/outer_stairs")

    def saveForAllVariants(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_inner.save(WoolBlock.path_block_models, self.name + "_inner.json")
        self.model_outer.save(WoolBlock.path_block_models, self.name + "_outer.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_stonecutting.save(WoolBlock.path_recipes, self.name + "_from_stonecutting.json")
        self.advancement.save()
        self.advancement_stonecutting.save()
        self.loot_table.save()
