import json
from block import WoolBlock
from util import loadAsStr, saveAsJson
from os.path import dirname, realpath

current_path = dirname(realpath(__file__))
template_path = current_path + "/templates"

template_blockstate = loadAsStr(template_path + "/fence/blockstate.json")
template_model_post = loadAsStr(template_path + "/fence/block_model_post.json")
template_model_side = loadAsStr(template_path + "/fence/block_model_side.json")
template_model_inventory = loadAsStr(template_path + "/fence/block_model_inventory.json")
template_item_model = loadAsStr(template_path + "/fence/item_model.json")
template_loot_table = loadAsStr(template_path + "/loot_table.json")
template_recipe = loadAsStr(template_path + "/recipe.json")
template_advancement = loadAsStr(template_path + "/advancement.json")


class WoolFence (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "fence")

    def save(self):
        self.saveBlockstate()
        self.saveBlockModelPost()
        self.saveBlockModelSide()
        self.saveBlockModelInventory()
        self.saveItemModel()
        self.saveLootTable()
        self.saveRecipe()
        self.saveAdvancement()

    def saveBlockstate(self):
        path = WoolBlock.path_blockstates + self.name + ".json"
        self.saveFromTemplate(template_blockstate, path)

    def saveBlockModelPost(self):
        path = WoolBlock.path_block_models + self.name + "_post.json"
        self.saveFromTemplate(template_model_post, path)

    def saveBlockModelSide(self):
        path = WoolBlock.path_block_models + self.name + "_side.json"
        self.saveFromTemplate(template_model_side, path)

    def saveBlockModelInventory(self):
        path = WoolBlock.path_block_models + self.name + "_inventory.json"
        self.saveFromTemplate(template_model_inventory, path)

    def saveItemModel(self):
        path = WoolBlock.path_item_models + self.name + ".json"
        self.saveFromTemplate(template_item_model, path)

    def saveLootTable(self):
        path = WoolBlock.path_loot_tables + self.name + ".json"
        self.saveFromTemplate(template_loot_table, path)

    def saveRecipe(self):
        if self.color == "white":
            return
        # TODO recipe different for forge and fabric (different dye tags)
        path = WoolBlock.path_recipes + self.name + ".json"
        self.saveFromTemplate(template_recipe, path)

    def saveAdvancement(self):
        path = WoolBlock.path_advancements + self.name + ".json"
        if self.color == "white":
            temp = template_advancement.replace("%trigger_item%", "minecraft:white_wool")
            self.saveFromTemplate(temp, path)
            return
        temp = template_advancement.replace("%trigger_item%", "more_wool_blocks:white_wool_fence")
        self.saveFromTemplate(temp, path)
