from os.path import dirname, realpath, join
import os


class Globals():

    modid = "wool_collection"
    modbase_dir = dirname(dirname(dirname(realpath(__file__))))
    generator_dir = join(modbase_dir, "generator")
    common_dir = join(modbase_dir, "Common", "src", "generated", "resources")
    forge_dir = join(modbase_dir, "Forge", "src", "generated", "resources")
    fabric_dir = join(modbase_dir, "Fabric", "src", "generated", "resources")
    colors = [
        "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray",
        "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
    ]

    def clearAllDirs():
        import shutil
        if os.path.exists(Globals.common_dir):
            shutil.rmtree(Globals.common_dir)
        if os.path.exists(Globals.forge_dir):
            shutil.rmtree(Globals.forge_dir)
        if os.path.exists(Globals.fabric_dir):
            shutil.rmtree(Globals.fabric_dir)

    def loadAsJson(path: str):
        import json
        with open(path, "r") as f:
            data = json.load(f)
            if data is None:
                return {}
            return data

    def saveAsJson(path: str, data, indent=0, sort_keys=False):
        import json
        with open(path, "w") as f:
            if indent == 0:
                json.dump(data, f)
            else:
                json.dump(data, f, indent=indent, sort_keys=sort_keys)

    def saveAsJsonPretty(path: str, data):
        Globals.saveAsJson(path, data, indent=4, sort_keys=True)

    def loadAsStr(path: str):
        with open(path, "r") as f:
            data = f.read()
            return data


if __name__ == "__main__":
    print(Globals.modbase_dir)
    print(Globals.generator_dir)
    print(Globals.common_dir)
    print(Globals.forge_dir)
    print(Globals.fabric_dir)
