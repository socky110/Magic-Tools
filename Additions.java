/*
* Add to ManaPick.java
* used in jsons
*/
this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
        	@Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    ItemStack itemstack = entityIn.getActiveItemStack();
                    return itemstack != null && itemstack.getItem() == Items.BOW ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
        	@Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
/*
* blockstate for ManaPick.java
* .json format
*/  
    {
    "parent": "item/generated",
    "textures": {
        "layer0": "capability:items/mana2_standby"
    },
    "display": {
        "thirdperson_righthand": {
            "rotation": [ -80, 260, -40 ],
            "translation": [ -1, -2, 2.5 ],
            "scale": [ 0.9, 0.9, 0.9 ]
        },
        "thirdperson_lefthand": {
            "rotation": [ -80, -280, 40 ],
            "translation": [ -1, -2, 2.5 ],
            "scale": [ 0.9, 0.9, 0.9 ]
        },
        "firstperson_righthand": {
            "rotation": [ 0, -90, 25 ],
            "translation": [ 1.13, 3.2, 1.13],
            "scale": [ 0.68, 0.68, 0.68 ]
        },
        "firstperson_lefthand": {
            "rotation": [ 0, 90, -25 ],
            "translation": [ 1.13, 3.2, 1.13],
            "scale": [ 0.68, 0.68, 0.68 ]
        }
    },
    "overrides": [
        {
            "predicate": {
                "pulling": 1
            },
            "model": "capability:item/mana2_pulling_0"
        },
        {
            "predicate": {
                "pulling": 1,
                "pull": 0.65
            },
            "model": "capability:item/mana2_pulling_1"
        },
        {
            "predicate": {
                "pulling": 1,
                "pull": 0.9
            },
            "model": "capability:item/mana2_pulling_2"
        }
    ]
}
/*
* model for ManaPick.java
* .json format
*/  
{
    "parent": "item/generated",
    "textures": {
        "layer0": "capability:items/mana2_pulling_0"
    }
}
// Add sword, axe and shovel   OR   Make a multitool with ony multitool and shovel

//


@SubscribeEvent

public void onPlayerClone(PlayerEvent.Clone event)

{

 EntityPlayer player = event.getEntityPlayer();

 mana = player.getCapability(manaprovider.MANA_CAP, null);
stam = player.getCapability(stamprovider.MANA_CAP, null);
// IMana oldMana = event.getOriginal().getCapability(ManaProvider.MANA_CAP, null);



 //mana.set(oldMana.getMana());
mana.set(250);
stam.set(5000);
