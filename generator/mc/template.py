from os.path import join
from mc.globals import Globals
from mc.block import WoolBlock


class Template():

    def __init__(self, block: WoolBlock, template_path=[]):
        self.block = block
        self.template = Globals.loadAsStr(join(Globals.generator_dir, "templates",  *template_path))

    def save(self, path: str, filename: str):
        import os
        import json
        temp = self.template.replace("%color%", self.block.color)
        temp = temp.replace("%name%", self.block.name)
        temp = temp.replace("%block_id%", self.block.full_id)
        temp = temp.replace("%group%", "wool_" + self.block.type)
        temp = temp.replace("%type%", self.block.type)
        temp = json.loads(temp)
        if not os.path.exists(path):
            os.makedirs(path)
        Globals.saveAsJson(join(path, filename), temp)
        pass
