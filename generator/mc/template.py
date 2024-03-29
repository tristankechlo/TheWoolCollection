from os.path import join
from mc.globals import Globals
from mc.block import WoolBlock


class Template():

    def __init__(self, block: WoolBlock, template_path=[]):
        self.block = block
        self.template = Globals.loadAsStr(join(Globals.generator_dir, "templates",  *template_path))
        self.replacements = {}

    def replace(self, key: str, value: str):
        self.replacements[key] = value

    def replaceAll(self, replacements: dict):
        for key, value in replacements.items():
            self.replace(key, value)

    def save(self, path: str, filename: str):
        import os
        import json
        temp = self.template
        # replace all other replacements
        for key, value in self.replacements.items():
            temp = temp.replace(key, value)
        # replace default replacements
        temp = temp.replace("%color%", self.block.color)
        temp = temp.replace("%block_id%", self.block.full_id)
        temp = temp.replace("%type%", self.block.type)
        temp = temp.replace("%modid%", Globals.modid)
        # save file
        temp = json.loads(temp)
        if not os.path.exists(path):
            os.makedirs(path)
        Globals.saveAsJson(join(path, filename), temp)


class RecipeTemplate(Template):

    def __init__(self, block: WoolBlock, count: int = 1):
        super().__init__(block, ["recipe_creating.json"])
        self.replace("%count%", str(count))


class EmptyRecipeTemplate(Template):

    def __init__(self, block: WoolBlock, count: int = 1):
        super().__init__(block, ["recipe_creating_empty.json"])
        self.replace("%count%", str(count))


class RecoloringRecipeTemplate(Template):

    def __init__(self, block: WoolBlock):
        super().__init__(block, ["recipe_recoloring.json"])
