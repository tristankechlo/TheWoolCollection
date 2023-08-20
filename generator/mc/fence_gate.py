from mc.block import WoolBlock
from mc.advancement import Advancement
from mc.loottable import LootTable
from mc.template import RecipeTemplate, RecoloringRecipeTemplate, Template


"""
=> Recipe
can be crafted inside the wool_processor and can be recolored from other wool fence gates

=> Advancement
white fence has a separate advancement, triggerd by crafting white wool
the other colors have a shared advancement, triggerd by crafting a white fence gate

=> Loot table
drops itself
"""


class WoolFenceGate (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "fence_gate")
        self.advancement = Advancement(self.full_id, self.color, self.name + ".json")
        self.loot_table = LootTable(self.full_id, self.name + ".json")
        self.blockstate = Template(self, ["fence_gate", "blockstate.json"])
        self.item_model = Template(self, ["fence_gate", "item_model.json"])
        self.recipe_creating = RecipeTemplate(self, 1)
        self.recipe_recoloring = RecoloringRecipeTemplate(self)

    def createSpecialTemplates(self):
        self.model = Template(self, ["fence_gate", "block_model.json"])
        self.model_open = Template(self, ["fence_gate", "block_model.json"])
        self.model_wall = Template(self, ["fence_gate", "block_model.json"])
        self.model_wall_open = Template(self, ["fence_gate", "block_model.json"])

    def setupSpecialTemplates(self):
        self.model.replace("%parent%", "minecraft:block/template_fence_gate")
        self.model_open.replace("%parent%", "minecraft:block/template_fence_gate_open")
        self.model_wall.replace("%parent%", "minecraft:block/template_fence_gate_wall")
        self.model_wall_open.replace("%parent%", "minecraft:block/template_fence_gate_wall_open")

    def save(self):
        self.blockstate.save(WoolBlock.path_blockstates, self.name + ".json")
        self.advancement.save()
        self.model.save(WoolBlock.path_block_models, self.name + ".json")
        self.model_open.save(WoolBlock.path_block_models, self.name + "_open.json")
        self.model_wall.save(WoolBlock.path_block_models, self.name + "_wall.json")
        self.model_wall_open.save(WoolBlock.path_block_models, self.name + "_wall_open.json")
        self.item_model.save(WoolBlock.path_item_models, self.name + ".json")
        self.loot_table.save()
        self.recipe_creating.save(WoolBlock.path_recipes, self.name + ".json")
        self.recipe_recoloring.save(WoolBlock.path_recipes, self.name + "_recoloring.json")
