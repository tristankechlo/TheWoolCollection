from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template
from mc.globals import Globals


class WoolSlabs (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "slab")
        trigger = "minecraft:" + self.color + "_wool"
        self.advancement = Advancement(self.full_id, trigger, self.name + ".json")
        self.advancement_stonecutting = Advancement(self.full_id + "_from_stonecutting", trigger, self.name + "_from_stonecutting.json")
        self.loot_table = Template(self, ["slabs", "loot_table.json"])
        self.blockstate = Template(self, ["slabs", "blockstate.json"])
        self.item_model = Template(self, ["slabs", "item_model.json"])
        self.recipe = Template(self, ["slabs", "recipe.json"])
        self.recipe_stonecutting = Template(self, ["slabs", "recipe_stonecutting.json"])

    def createTemplates(self):
        self.model = Template(self, ["slabs", "block_model.json"])
        self.model_top = Template(self, ["slabs", "block_model.json"])

    def setup(self):
        self.model.replace("%parent%", "minecraft:block/slab")
        self.model_top.replace("%parent%", "minecraft:block/slab_top")

    def saveForAllVariants(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_top.save(WoolBlock.path_block_models, self.name + "_top.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_stonecutting.save(WoolBlock.path_recipes, self.name + "_from_stonecutting.json")
        self.advancement.save()
        self.advancement_stonecutting.save()
        self.loot_table.save(LootTable.path, self.name + ".json")
