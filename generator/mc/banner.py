from mc.block import WoolBlock
from mc.template import Template
from mc.advancement import Advancement
from os.path import join
from mc.globals import Globals


class WoolBanner (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "banner")
        self.full_id = "minecraft:" + self.color + "_banner"
        self.name = self.color + "_banner"
        self.advancement = Advancement(Globals.modid + ":" + self.color + "_banner", self.color, self.name + ".json")
        self.advancement.template = Globals.loadAsStr(join(Globals.generator_dir, "templates", "banner",  "advancement.json"))

    def createSpecialTemplates(self):
        self.recipe = Template(self, ["banner", "recipe.json"])

    def setupSpecialTemplates(self):
        self.recipe.replace("%count%", "1")

    def save(self):
        self.recipe.save(WoolBlock.path_recipes, self.color + "_banner.json")
        self.advancement.save("decorations")
