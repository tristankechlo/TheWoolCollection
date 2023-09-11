from os.path import join

if __name__ == "__main__":
    from globals import Globals
    from block import WoolBlock
else:
    from mc.globals import Globals
    from mc.block import WoolBlock


class Advancement():

    path = join(Globals.common_dir, "data", Globals.modid, "advancements", "recipes")

    def __init__(self, recipe_id: str, color: str, filename: str):
        self.recipe_id = recipe_id
        self.color = color
        self.filename = filename
        self.template = Globals.loadAsStr(join(Globals.generator_dir, "templates",  "advancement.json"))

    def save(self, category: str = "building_blocks"):
        import json
        import os
        path = join(Advancement.path, category)
        if not os.path.exists(path):
            os.makedirs(path)
        temp = self.template.replace("%color%", self.color)
        temp = temp.replace("%recipe_id%", self.recipe_id)
        temp = json.loads(temp)
        Globals.saveAsJson(join(path, self.filename), temp)


if __name__ == "__main__":
    print(Advancement.path)
