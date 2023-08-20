from mc.block import WoolBlock
from mc.template import RecipeTemplate, EmptyRecipeTemplate
from mc.advancement import Advancement
from os.path import join
from mc.globals import Globals


class WoolCarpet (WoolBlock):

    def __init__(self, color: str):
        super().__init__(color, "carpet")
        self.full_id = "minecraft:" + self.color + "_carpet"
        self.name = self.color + "_carpet"
        self.advancement = Advancement(Globals.modid + ":" + self.color + "_carpet", self.color, self.name + ".json")
        self.advancement.template = Globals.loadAsStr(join(Globals.generator_dir, "templates", "carpet",  "advancement.json"))
        self.recipe = RecipeTemplate(self, 2)
        self.recipe_empty = EmptyRecipeTemplate(self, 2)

    def save(self):
        self.recipe.save(WoolBlock.path_recipes, self.color + "_carpet.json")
        self.recipe_empty.save(WoolBlock.path_recipes, self.color + "_carpet_empty.json")
        self.advancement.save("decorations")
