import json
from os.path import dirname, realpath
from util import saveAsJson


class WoolBlock:

    parent_dir = dirname(dirname(dirname(realpath(__file__))))
    path_blockstates = parent_dir + "/Common/src/generated/resources/assets/more_wool_blocks/blockstates/"
    path_block_models = parent_dir + "/Common/src/generated/resources/assets/more_wool_blocks/models/block/"
    path_item_models = parent_dir + "/Common/src/generated/resources/assets/more_wool_blocks/models/item/"
    path_loot_tables = parent_dir + "/Common/src/generated/resources/data/more_wool_blocks/loot_tables/blocks/"
    path_recipes = parent_dir + "/Common/src/generated/resources/data/more_wool_blocks/recipes/"
    path_advancements = parent_dir + "/Common/src/generated/resources/data/more_wool_blocks/advancements/building_blocks/"

    def __init__(self, color: str, type: str):
        self.color = color
        self.type = type
        self.name = color + "_wool_" + type
        self.full_id = "more_wool_blocks:" + self.name
        self.translation_key = "block.more_wool_blocks." + self.name
        self.translation = color.replace("_", " ").title() + " Wool " + type.replace("_", " ").title()

    def getColor(self):
        return self.color

    def getTranslationKey(self):
        return self.translation_key

    def getTranslation(self):
        return self.translation

    def getFullId(self):
        return self.full_id

    def save(self):
        pass

    def saveFromTemplate(self, template: str, save_path: str):
        template = template.replace("%color%", self.color)
        template = template.replace("%name%", self.name)
        template = template.replace("%block_id%", self.full_id)
        template = template.replace("%group%", "wool_" + self.type)
        template = template.replace("%type%", self.type)
        template = json.loads(template)
        saveAsJson(save_path, template)
