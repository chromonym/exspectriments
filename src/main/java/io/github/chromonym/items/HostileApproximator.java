package io.github.chromonym.items;

import de.dafuqs.revelationary.api.revelations.CloakedItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public class HostileApproximator extends CloakedItem {
    public HostileApproximator() {
        super(new FabricItemSettings().maxDamage(16), new Identifier("spectrum","collect_all_basic_shards"), Items.ENDER_PEARL);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) { // hopefully this works uhhhh
      return false;
   }

   @Override
   public ItemStack getRecipeRemainder(ItemStack stack) {
       stack.damage(1, Random.create(), null);
       return stack;
   }
}
