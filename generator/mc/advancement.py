from os.path import join

if __name__ == "__main__":
    from globals import Globals
else:
    from mc.globals import Globals


class Advancement():

    path = join(Globals.common_dir, "data", Globals.modid, "advancements", "recipes")

    def __init__(self, recipe_id: str, trigger_item: str, filename: str):
        self.recipe_id = recipe_id
        self.trigger_item = trigger_item
        self.filename = filename
        self.template = Globals.loadAsStr(join(Globals.generator_dir, "templates",  "advancement.json"))

    def save(self, category: str = "building_blocks"):
        import json
        import os
        path = join(Advancement.path, category)
        if not os.path.exists(path):
            os.makedirs(path)
        temp = self.template.replace("%trigger_item%", self.trigger_item)
        temp = temp.replace("%recipe_id%", self.recipe_id)
        temp = json.loads(temp)
        Globals.saveAsJson(join(path, self.filename), temp)


if __name__ == "__main__":
    print(Advancement.path)
