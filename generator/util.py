import json
import os


def saveAsJson(path: str, data, indent=0, sort_keys=False):
    # create folders if they don't exist
    folders = path.split("\\")
    folders.pop()  # remove file name
    folder = ""
    for f in folders:
        folder += f + "/"
        if not os.path.exists(folder):
            os.mkdir(folder)

    with open(path, "w") as f:
        if indent == 0:
            json.dump(data, f)
        else:
            json.dump(data, f, indent=indent, sort_keys=sort_keys)


def loadAsJson(path: str):
    with open(path, "r") as f:
        data = json.load(f)
        if data is None:
            return {}
        return data


def loadAsStr(path: str):
    with open(path, "r") as f:
        data = f.read()
        return data

