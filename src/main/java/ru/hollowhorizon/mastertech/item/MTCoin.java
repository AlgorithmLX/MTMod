package ru.hollowhorizon.mastertech.item;

import net.minecraft.util.ResourceLocation;
import ru.hollowhorizon.mastertech.api.item.ItemBase;

public class MTCoin extends ItemBase {
    public MTCoin() {
        super();
        addPropertyOverride(new ResourceLocation("count"), (s, w, e) -> count2texture(s.getCount()));
    }

    private float count2texture(int count) {
        if (count > 4 && count <= 6) return 1F;
        else if (count > 7 && count <= 12) return 2F;
        else if (count > 13 && count <= 20) return 3F;
        else if (count > 21 && count <= 40) return 4F;
        else if (count > 54) return 5F;
        return 0F;
    }
}
