import os
from os.path import join

if __name__ == "__main__":
    from globals import Globals
else:
    from mc.globals import Globals


class WoolBlock:

    path_blockstates = join(Globals.common_dir, "assets", Globals.modid, "blockstates") + os.sep
    path_block_models = join(Globals.common_dir, "assets", Globals.modid, "models", "block") + os.sep
    path_item_models = join(Globals.common_dir, "assets", Globals.modid, "models", "item") + os.sep
    path_recipes = join(Globals.common_dir, "data", Globals.modid, "recipes") + os.sep

    def __init__(self, color: str, type: str):
        self.color = color
        self.type = type
        self.name = color + "_wool_" + type
        self.full_id = Globals.modid + ":" + self.name
        self.translation_key = "block." + Globals.modid + "." + self.name
        self.translation = color.replace("_", " ").title() + " Wool " + type.replace("_", " ").title()
        self.createSpecialTemplates()
        self.setupSpecialTemplates()

    def createSpecialTemplates(self):
        pass

    def setupSpecialTemplates(self):
        pass

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


if __name__ == "__main__":
    print(WoolBlock.path_blockstates)
    print(WoolBlock.path_block_models)
    print(WoolBlock.path_item_models)
    print(WoolBlock.path_recipes)
