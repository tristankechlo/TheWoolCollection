from os.path import join

if __name__ == "__main__":
    from globals import Globals
else:
    from mc.globals import Globals


class LootTable():

    path = join(Globals.common_dir, "data", Globals.modid, "loot_tables", "blocks")

    def __init__(self, block_id: str, filename: str):
        self.block_id = block_id
        self.filename = filename
        self.template = Globals.loadAsStr(join(Globals.generator_dir, "templates",  "loot_table.json"))

    def save(self):
        import json
        import os
        if not os.path.exists(LootTable.path):
            os.makedirs(LootTable.path)
        temp = self.template.replace("%block_id%", self.block_id)
        temp = json.loads(temp)
        Globals.saveAsJson(join(LootTable.path, self.filename), temp)
