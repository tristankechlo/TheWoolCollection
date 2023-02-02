from block_types.block import WoolBlock
from util import loadAsStr
from os.path import dirname, realpath

current_path = dirname(dirname(realpath(__file__)))
template_path = current_path + "/templates"

template_blockstate = loadAsStr(template_path + "/stairs/blockstate.json")
template_model = loadAsStr(template_path + "/stairs/block_model.json")
template_model_inner = loadAsStr(template_path + "/stairs/block_model_inner.json")
template_model_outer = loadAsStr(template_path + "/stairs/block_model_outer.json")
template_item_model = loadAsStr(template_path + "/stairs/item_model.json")
template_loot_table = loadAsStr(template_path + "/loot_table.json")
template_recipe = loadAsStr(template_path + "/stairs/recipe.json")
template_recipe_stonecutting = loadAsStr(template_path + "/stairs/recipe_stonecutting.json")
template_advancement = loadAsStr(template_path + "/advancement.json")


class WoolStairs (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "stairs")

    def save(self):
        self.saveBlockstate()
        self.saveBlockModel()
        self.saveBlockModelInner()
        self.saveBlockModelOuter()
        self.saveItemModel()
        self.saveLootTable()
        self.saveRecipe()
        self.saveAdvancement()

    def saveBlockstate(self):
        path = WoolBlock.path_blockstates + self.name + ".json"
        self.saveFromTemplate(template_blockstate, path)

    def saveBlockModel(self):
        path = WoolBlock.path_block_models + self.name + ".json"
        self.saveFromTemplate(template_model, path)

    def saveBlockModelInner(self):
        path = WoolBlock.path_block_models + self.name + "_inner.json"
        self.saveFromTemplate(template_model_inner, path)

    def saveBlockModelOuter(self):
        path = WoolBlock.path_block_models + self.name + "_outer.json"
        self.saveFromTemplate(template_model_outer, path)

    def saveItemModel(self):
        path = WoolBlock.path_item_models + self.name + ".json"
        self.saveFromTemplate(template_item_model, path)

    def saveLootTable(self):
        path = WoolBlock.path_loot_tables + self.name + ".json"
        self.saveFromTemplate(template_loot_table, path)

    def saveRecipe(self):
        path = WoolBlock.path_recipes + self.name + ".json"
        self.saveFromTemplate(template_recipe, path)  # normal crafting recipe
        path = WoolBlock.path_recipes + self.name + "_stonecutting.json"
        self.saveFromTemplate(template_recipe_stonecutting, path)  # stonecutting recipe

    def saveAdvancement(self):
        path = WoolBlock.path_advancements + self.name + ".json"
        temp1 = template_advancement.replace("%trigger_item%", "minecraft:white_wool")
        self.saveFromTemplate(temp1, path)  # normal crafting advancement
        path = WoolBlock.path_advancements + self.name + "_from_stonecutting.json"
        temp2 = template_advancement.replace("%trigger_item%", "minecraft:" + self.color + "_wool")
        temp2 = temp2.replace("%name%", self.color + "_wool_stairs_from_stonecutting")
        self.saveFromTemplate(temp2, path)  # stonecutting advancement
