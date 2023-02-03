from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import Template, StoneCuttingTemplate
from mc.globals import Globals


class WoolWalls (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "wall")
        trigger = "minecraft:" + self.color + "_wool"
        self.advancement = Advancement(self.full_id, trigger, self.name + ".json")
        self.advancement_stonecutting = Advancement(self.full_id + "_from_stonecutting", trigger, self.name + "_from_stonecutting.json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["walls", "blockstate.json"])
        self.item_model = Template(self, ["walls", "item_model.json"])
        self.recipe = Template(self, ["walls", "recipe.json"])
        self.stonecutting = StoneCuttingTemplate(self, 1)

    def createTemplates(self):
        self.model_post = Template(self, ["walls", "block_model.json"])
        self.model_side = Template(self, ["walls", "block_model.json"])
        self.model_side_tall = Template(self, ["walls", "block_model.json"])
        self.model_inventory = Template(self, ["walls", "block_model.json"])

    def setup(self):
        self.model_post.replace("%parent%", "minecraft:block/template_wall_post")
        self.model_side.replace("%parent%", "minecraft:block/template_wall_side")
        self.model_side_tall.replace("%parent%", "minecraft:block/template_wall_side_tall")
        self.model_inventory.replace("%parent%", "minecraft:block/wall_inventory")

    def saveForAllVariants(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.model_post.save(WoolBlock.path_block_models, self.name + "_post.json")
        self.model_side.save(WoolBlock.path_block_models, self.name + "_side.json")
        self.model_side_tall.save(WoolBlock.path_block_models, self.name + "_side_tall.json")
        self.model_inventory.save(WoolBlock.path_block_models, self.name + "_inventory.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.recipe.save(WoolBlock.path_recipes, self.name + ".json")
        self.stonecutting.save(WoolBlock.path_recipes, self.name + "_from_stonecutting.json")
        self.advancement.save("decorations")
        self.advancement_stonecutting.save("decorations")
        self.loot_table.save()
