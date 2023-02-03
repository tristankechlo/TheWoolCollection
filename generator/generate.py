from util import saveAsJson
from translation import Translation
from mc.fence import WoolFence
from mc.fence_gate import WoolFenceGate
from mc.stairs import WoolStairs
from mc.globals import Globals


types = ["fence", "fence_gate", "stairs", "slab", "wall"]
translation = Translation()


tags = {
    "fences": {"values": []},
    "fence_gates": {"values": []},
    "stairs": {"values": []}
}


def createFence(color: str):
    fence = WoolFence(color)
    tags["fences"]["values"].append(fence.getFullId())
    translation.add(fence)
    fence.save()


def createFenceGate(color: str):
    fence_gate = WoolFenceGate(color)
    tags["fence_gates"]["values"].append(fence_gate.getFullId())
    translation.add(fence_gate)
    fence_gate.save()


def createStairs(color: str):
    stairs = WoolStairs(color)
    tags["stairs"]["values"].append(stairs.getFullId())
    translation.add(stairs)
    stairs.save()


if __name__ == "__main__":
    # clear folders before generating
    Globals.clearAllDirs()

    # generate content
    for color in Globals.colors:
        createFence(color)
        createFenceGate(color)
        createStairs(color)

    # create lang file
    path_lang = Globals.common_dir + "/assets/more_wool_blocks/lang/en_us.json"
    saveAsJson(path_lang, translation.content, indent=4, sort_keys=True)
    print("Created %d translations" % len(translation.content))

    # create tags
    for tag in tags:
        # save minecraft tags
        path = Globals.common_dir + "/data/minecraft/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag]}
        saveAsJson(path.replace("%", "blocks"), ref)  # minecraft:fences => reference #more_wool_blocks:fences
        saveAsJson(path.replace("%", "items"), ref)  # minecraft:fences => reference #more_wool_blocks:fences

        # save custom tags
        path = Globals.common_dir + "/data/more_wool_blocks/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # more_wool_blocks:fences => reference #more_wool_blocks:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # more_wool_blocks:fences => reference #more_wool_blocks:fences/wool
        path = Globals.common_dir + "/data/more_wool_blocks/tags/%/" + tag + "/wool.json"
        saveAsJson(path.replace("%", "blocks"), tags[tag])  # more_wool_blocks:fences/wool ====> all custom wool fences
        saveAsJson(path.replace("%", "items"), tags[tag])  # more_wool_blocks:fences/wool ====> all custom wool fences

        # save forge tags
        path = Globals.forge_dir + "/data/forge/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#forge:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # forge:fences => reference #forge:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # forge:fences => reference #forge:fences/wool
        path = Globals.forge_dir + "/data/forge/tags/%/" + tag + "/wool.json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # forge:fences/wool => reference #more_wool_blocks:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # forge:fences/wool => reference #more_wool_blocks:fences/wool

        # save fabric tags
        path = Globals.fabric_dir + "/data/c/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag]}
        saveAsJson(path.replace("%", "blocks"), ref)  # c:fences => reference #more_wool_blocks:fences
        saveAsJson(path.replace("%", "items"), ref)  # c:fences => reference #more_wool_blocks:fences
