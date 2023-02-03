from os.path import join

if __name__ == "__main__":
    from globals import Globals
else:
    from mc.globals import Globals


class Advancement():

    path = join(Globals.common_dir, "data", "more_wool_blocks", "advancements", "recipes", "building_blocks")

    def __init__(self, recipe_id: str, trigger_item: str, filename: str):
        self.recipe_id = recipe_id
        self.trigger_item = trigger_item
        self.filename = filename
        self.template = Globals.loadAsStr(join(Globals.generator_dir, "templates",  "advancement.json"))

    def save(self):
        import json
        import os
        if not os.path.exists(Advancement.path):
            os.makedirs(Advancement.path)
        temp = self.template.replace("%trigger_item%", self.trigger_item)
        temp = temp.replace("%recipe_id%", self.recipe_id)
        temp = json.loads(temp)
        Globals.saveAsJson(join(Advancement.path, self.filename), temp)


if __name__ == "__main__":
    print(Advancement.path)
