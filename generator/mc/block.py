import os
import json
from os.path import dirname, join

if __name__ == "__main__":
    from globals import Globals
else:
    from mc.globals import Globals


class WoolBlock:

    path_blockstates = join(Globals.common_dir, "assets", "more_wool_blocks", "blockstates") + os.sep
    path_block_models = join(Globals.common_dir, "assets", "more_wool_blocks", "models", "block") + os.sep
    path_item_models = join(Globals.common_dir, "assets", "more_wool_blocks", "models", "item") + os.sep
    path_recipes = join(Globals.common_dir, "data", "more_wool_blocks", "recipes") + os.sep

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

    def saveForAllVariants(self):
        pass

    def saveForWhiteVariant(self):
        pass

    def saveForNonWhiteVariants(self):
        pass

    def save(self):
        # save all files relevant for all variants
        self.saveForAllVariants()

        # save all files relevant for white variants
        if self.color == "white":
            self.saveForWhiteVariant()
            return

        # save all files relevant for non-white variants
        self.saveForNonWhiteVariants()

    def saveFromTemplate(self, template: str, save_path: str):
        template = template.replace("%color%", self.color)
        template = template.replace("%name%", self.name)
        template = template.replace("%block_id%", self.full_id)
        template = template.replace("%group%", "wool_" + self.type)
        template = template.replace("%type%", self.type)
        template = json.loads(template)
        if not os.path.exists(dirname(save_path)):
            os.makedirs(dirname(save_path))
        Globals.saveAsJson(save_path, template)


if __name__ == "__main__":
    print(WoolBlock.path_blockstates)
    print(WoolBlock.path_block_models)
    print(WoolBlock.path_item_models)
    print(WoolBlock.path_recipes)
