from block_types.block import WoolBlock
from util import loadAsStr
from os.path import dirname, realpath

current_path = dirname(dirname(realpath(__file__)))
template_path = current_path + "/templates"

template_blockstate = loadAsStr(template_path + "/fence_gate/blockstate.json")
template_model = loadAsStr(template_path + "/fence_gate/block_model.json")
template_model_open = loadAsStr(template_path + "/fence_gate/block_model_open.json")
template_model_wall = loadAsStr(template_path + "/fence_gate/block_model_wall.json")
template_model_wall_open = loadAsStr(template_path + "/fence_gate/block_model_wall_open.json")
template_item_model = loadAsStr(template_path + "/fence_gate/item_model.json")
template_advancement = loadAsStr(template_path + "/advancement.json")
template_loot_table = loadAsStr(template_path + "/loot_table.json")
template_recipe = loadAsStr(template_path + "/recipe.json")


class WoolFenceGate (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "fence_gate")

    def save(self):
        self.saveBlockstate()
        self.saveBlockModel()
        self.saveBlockModelOpen()
        self.saveBlockModelWall()
        self.saveBlockModelWallOpen()
        self.saveItemModel()
        self.saveAdvancement()
        self.saveLootTable()
        self.saveRecipe()

    def saveBlockstate(self):
        path = WoolBlock.path_blockstates + self.name + ".json"
        self.saveFromTemplate(template_blockstate, path)

    def saveBlockModel(self):
        path = WoolBlock.path_block_models + self.name + ".json"
        self.saveFromTemplate(template_model, path)

    def saveBlockModelOpen(self):
        path = WoolBlock.path_block_models + self.name + "_open.json"
        self.saveFromTemplate(template_model_open, path)

    def saveBlockModelWall(self):
        path = WoolBlock.path_block_models + self.name + "_wall.json"
        self.saveFromTemplate(template_model_wall, path)

    def saveBlockModelWallOpen(self):
        path = WoolBlock.path_block_models + self.name + "_wall_open.json"
        self.saveFromTemplate(template_model_wall_open, path)

    def saveItemModel(self):
        path = WoolBlock.path_item_models + self.name + ".json"
        self.saveFromTemplate(template_item_model, path)

    def saveAdvancement(self):
        path = WoolBlock.path_advancements + self.name + ".json"
        if self.color == "white":
            temp = template_advancement.replace("%trigger_item%", "minecraft:white_wool")
            self.saveFromTemplate(temp, path)
            return
        temp = template_advancement.replace("%trigger_item%", "more_wool_blocks:white_wool_fence_gate")
        self.saveFromTemplate(temp, path)

    def saveLootTable(self):
        path = WoolBlock.path_loot_tables + self.name + ".json"
        self.saveFromTemplate(template_loot_table, path)

    def saveRecipe(self):
        if self.color == "white":
            return
        path = WoolBlock.path_recipes + self.name + ".json"
        self.saveFromTemplate(template_recipe, path)
