from block_types.block import WoolBlock

class Translation:

    def __init__(self):
        self.content = {}

    def add(self, block: WoolBlock):
        self.content[block.getTranslationKey()] = block.getTranslation()
