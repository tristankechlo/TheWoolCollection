from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template


class WoolStairs (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "stairs")
        trigger = "minecraft:" + self.color + "_wool"
        self.advancement = Advancement(self.full_id, trigger, self.name + ".json")
        self.advancement_stonecutting = Advancement(self.full_id + "_from_stonecutting", trigger, self.name + "_from_stonecutting.json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["stairs", "blockstate.json"])
        self.model = Template(self, ["stairs", "block_model.json"])
        self.model_inner = Template(self, ["stairs", "block_model_inner.json"])
        self.model_outer = Template(self, ["stairs", "block_model_outer.json"])
        self.item_model = Template(self, ["stairs", "item_model.json"])
        self.recipe = Template(self, ["stairs", "recipe.json"])
        self.recipe_stonecutting = Template(self, ["stairs", "recipe_stonecutting.json"])

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
