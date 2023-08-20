from util import saveAsJson
from os.path import join
from translation import Translation
from mc.fence import WoolFence
from mc.fence_gate import WoolFenceGate
from mc.stairs import WoolStairs
from mc.slabs import WoolSlabs
from mc.walls import WoolWalls
from mc.buttons import WoolButtons
from mc.pressure_plate import WoolPressurePlate
from mc.carpet import WoolCarpet
from mc.banner import WoolBanner
from mc.globals import Globals


translation = Translation()
translation.addRaw("block.wool_collection.wool_processor", "Wool Processor")


tags = {
    "fences": {"values": []},
    "fence_gates": {"values": []},
    "stairs": {"values": []},
    "slabs": {"values": []},
    "walls": {"values": []},
    "buttons": {"values": []},
    "pressure_plates": {"values": []}
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


def createSlabs(color: str):
    slabs = WoolSlabs(color)
    tags["slabs"]["values"].append(slabs.getFullId())
    translation.add(slabs)
    slabs.save()


def createWalls(color: str):
    walls = WoolWalls(color)
    tags["walls"]["values"].append(walls.getFullId())
    translation.add(walls)
    walls.save()


def createButtons(color: str):
    buttons = WoolButtons(color)
    tags["buttons"]["values"].append(buttons.getFullId())
    translation.add(buttons)
    buttons.save()


def createPressurePlates(color: str):
    pressure_plate = WoolPressurePlate(color)
    tags["pressure_plates"]["values"].append(pressure_plate.getFullId())
    translation.add(pressure_plate)
    pressure_plate.save()


def createCarpet(color: str):
    carpet = WoolCarpet(color)
    carpet.save()


def createBanner(color: str):
    banner = WoolBanner(color)
    banner.save()


if __name__ == "__main__":
    # clear folders before generating
    Globals.clearAllDirs()

    # generate content
    for color in Globals.colors:
        createFence(color)
        createFenceGate(color)
        createStairs(color)
        createSlabs(color)
        createWalls(color)
        createButtons(color)
        createPressurePlates(color)
        createCarpet(color)
        createBanner(color)
        print(f'generated {len(tags.keys())} blocks for color "{color}"')
        print(f'generated {Globals.save_counter} files')

    # create lang file
    path_lang = join(Globals.common_dir, "assets", "wool_collection", "lang", "en_us.json")
    saveAsJson(path_lang, translation.content, indent=4, sort_keys=True)
    print("created %d translations" % len(translation.content))

    # create tags
    for tag in tags:
        # save minecraft tags
        path = join(Globals.common_dir, "data", "minecraft", "tags", "%", tag + ".json")
        ref = {"replace": False, "values": ["#wool_collection:" + tag]}
        saveAsJson(path.replace("%", "blocks"), ref)  # minecraft:fences => reference #wool_collection:fences
        saveAsJson(path.replace("%", "items"), ref)  # minecraft:fences => reference #wool_collection:fences

        # save custom tags
        path = join(Globals.common_dir, "data", "wool_collection", "tags", "%", tag + ".json")
        ref = {"replace": False, "values": ["#wool_collection:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # wool_collection:fences => reference #wool_collection:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # wool_collection:fences => reference #wool_collection:fences/wool
        path = join(Globals.common_dir, "data", "wool_collection", "tags", "%", tag, "wool.json")
        saveAsJson(path.replace("%", "blocks"), tags[tag])  # wool_collection:fences/wool ====> all custom wool fences
        saveAsJson(path.replace("%", "items"), tags[tag])  # wool_collection:fences/wool ====> all custom wool fences

        # save forge tags
        path = join(Globals.forge_dir, "data", "forge", "tags", "%", tag + ".json")
        ref = {"replace": False, "values": ["#forge:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # forge:fences => reference #forge:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # forge:fences => reference #forge:fences/wool
        path = join(Globals.forge_dir, "data", "forge", "tags", "%", tag, "wool.json")
        ref = {"replace": False, "values": ["#wool_collection:" + tag + "/wool"]}
        saveAsJson(path.replace("%", "blocks"), ref)  # forge:fences/wool => reference #wool_collection:fences/wool
        saveAsJson(path.replace("%", "items"), ref)  # forge:fences/wool => reference #wool_collection:fences/wool

        # save fabric tags
        path = join(Globals.fabric_dir, "data", "c", "tags", "%", tag + ".json")
        ref = {"replace": False, "values": ["#wool_collection:" + tag]}
        saveAsJson(path.replace("%", "blocks"), ref)  # c:fences => reference #wool_collection:fences
        saveAsJson(path.replace("%", "items"), ref)  # c:fences => reference #wool_collection:fences
