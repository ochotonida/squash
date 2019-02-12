package squash;

import com.artemis.artemislib.util.attributes.ArtemisLibAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

@Mod.EventBusSubscriber
@SuppressWarnings("unused")
@Mod(modid = "squash", name = "Squash", version = "1.12.2-1.0.0", dependencies = "required-after:artemislib", updateJSON = "https://github.com/ochotonida/squash/blob/master/update.json")
public class Squash {

    private static final UUID MODIFIER_UUID = UUID.fromString("90a34e4b-8956-4d7d-93bd-281409e25298");
    private static final AttributeModifier MODIFIER = new AttributeModifier(MODIFIER_UUID, "Squash shrink", -0.9, 2);

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource() == DamageSource.ANVIL) {
            IAttributeInstance attributeInstance = event.getEntityLiving().getAttributeMap().getAttributeInstance(ArtemisLibAttributes.ENTITY_HEIGHT);
            if (!attributeInstance.hasModifier(MODIFIER)) {
                attributeInstance.applyModifier(MODIFIER);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        IAttributeInstance attributeInstance = event.getEntityLiving().getAttributeMap().getAttributeInstance(ArtemisLibAttributes.ENTITY_HEIGHT);
        if (attributeInstance.hasModifier(MODIFIER) && event.getEntityLiving().getRNG().nextInt(5) == 0) {
            attributeInstance.removeModifier(MODIFIER);
        }
    }
}
