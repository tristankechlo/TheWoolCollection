from mc.block import WoolBlock


class Translation:

    def __init__(self):
        self.content = {}

    def add(self, block: WoolBlock):
        self.content[block.getTranslationKey()] = block.getTranslation()
