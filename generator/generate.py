import shutil
import os
from util import saveAsJson
from translation import Translation
from os.path import dirname, realpath
from block_types.fence import WoolFence
from block_types.fence_gate import WoolFenceGate


colors = ["white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"]
types = ["fence", "fence_gate", "stairs", "slab", "wall"]
translation = Translation()


parent_dir = dirname(dirname(dirname(realpath(__file__))))
common_dir = parent_dir + "/Common/src/generated/resources/"
forge_dir = parent_dir + "/Forge/src/generated/resources/"
fabric_dir = parent_dir + "/Fabric/src/generated/resources/"

tags = {
    "fences": {"values": []},
    "fence_gates": {"values": []},
    # "walls": {"values": []},
    # "slabs": {"values": []},
    # "stairs": {"values": []}
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


if __name__ == "__main__":
    # clear folders before generating
    if os.path.exists(common_dir):
        shutil.rmtree(common_dir)
    if os.path.exists(forge_dir):
        shutil.rmtree(forge_dir)
    if os.path.exists(fabric_dir):
        shutil.rmtree(fabric_dir)

    # generate content
    for color in colors:
        createFence(color)
        createFenceGate(color)
        # createWall(color)
        # createSlab(color)
        # createStair(color)

    # create lang file
    path_lang = common_dir + "/assets/more_wool_blocks/lang/en_us.json"
    saveAsJson(path_lang, translation.content, indent=4, sort_keys=True)
    print("Created %d translations" % len(translation.content))

    # create tags
    for tag in tags:
        # save minecraft tags
        path = common_dir + "/data/minecraft/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag]}
        saveAsJson(path.replace("%", "blocks"), ref)  # minecraft:fences => reference #more_wool_blocks:fences
        saveAsJson(path.replace("%", "items"), ref)  # minecraft:fences => reference #more_wool_blocks:fences

        # save custom tags
        path = common_dir + "/data/more_wool_blocks/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # more_wool_blocks:fences => reference #more_wool_blocks:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # more_wool_blocks:fences => reference #more_wool_blocks:fences/wool
        path = common_dir + "/data/more_wool_blocks/tags/%/" + tag + "/wool.json"
        saveAsJson(path.replace("%", "blocks"), tags[tag])  # more_wool_blocks:fences/wool ====> all custom wool fences
        saveAsJson(path.replace("%", "items"), tags[tag])  # more_wool_blocks:fences/wool ====> all custom wool fences

        # save forge tags
        path = forge_dir + "/data/forge/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#forge:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # forge:fences => reference #forge:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # forge:fences => reference #forge:fences/wool
        path = forge_dir + "/data/forge/tags/%/" + tag + "/wool.json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # forge:fences/wool => reference #more_wool_blocks:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # forge:fences/wool => reference #more_wool_blocks:fences/wool

        # save fabric tags
        path = fabric_dir + "/data/c/tags/%/" + tag + ".json"
        ref = {"replace": False, "values": ["#more_wool_blocks:" + tag]}
        saveAsJson(path.replace("%", "blocks"), ref)  # c:fences => reference #more_wool_blocks:fences
        saveAsJson(path.replace("%", "items"), ref)  # c:fences => reference #more_wool_blocks:fences
