package capability;

import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class capevent

{
public static cap mana = null;
public static stam stam = null;
 @SubscribeEvent

 public void onPlayerLogsIn(PlayerLoggedInEvent event)

 {

 EntityPlayer player = event.player;

 mana = player.getCapability(capprovider.MANA_CAP, null);
 stam = player.getCapability(stamprovider.MANA_CAP, null);


 String message = "Hello there, you have "+ (int) mana.getMana() +" mana left, and " + (int) stam.getStam() + " left.";

 player.addChatMessage(new TextComponentString(message));

 }
 public void loop2(LivingEvent event){
	 boolean var = event.getEntityLiving().isSprinting();
             while (var)
             {
            	 	stam = event.getEntityLiving().getCapability(stamprovider.MANA_CAP, null);
            	 //if(event.getEntity().isSprinting()){
            	 	if(stam.getStam() > 0){
            	 		stam.drain(3F);
            	 	}
     					 //if(stam.getStam() <= 0){
     					//	 event.getEntity().setSprinting(false);
     					 //}
     			 else if(stam.getStam() <= 0){
					event.getEntity().setSprinting(false);
				 }
                 else if(stam.getStam() > 0 )
                 {
                	 
                 }
             }
             stam.regen2(1);
 }
public void loop(LivingUpdateEvent event){
	stam = event.getEntityLiving().getCapability(stamprovider.MANA_CAP, null);
	boolean var = event.getEntityLiving().isSprinting();
	if(stam.getStam() <= 0){
		 event.getEntity().setSprinting(false);
	}
	else if(stam.getStam() > 0 && var == true){
		stam.drain(3F);
	}else if(stam.getStam() <= 0 && var == false){
		stam.regen(1);
	}
}
 @SubscribeEvent

 public void onPlayerRun(LivingEvent event)
 {
	 int staminacount = 0;
	 if(event.getEntityLiving() instanceof EntityPlayer){
	 //if(!event.getEntity().worldObj.isRemote){
				//if(event.getEntity().isSprinting()){
				//	 stam.drain(3F);
				//	 if(stam.getStam() <= 0){
				//		 event.getEntity().setSprinting(false);
				//	 }
		Timer t = new Timer();
		TimerTask tt = new TimerTask(){
		@Override
		public void run() {
			//staminacount++;
			this.loop(event);
			//stam = (staminacount * stamina) + stam;
			//if(stam > 5000.0F) stam = 5000.0F;

		}
	};
	t.scheduleAtFixedRate(tt, 0, 20000);
		 //this.loop2(event);
	// }
				//else
	 } 
				//while(!event.getEntity().isSprinting()){
				//	 stam.regen2(1F); 
				//}
 }

 @SubscribeEvent

 public void onPlayerSleep(PlayerSleepInBedEvent event)

 {

 EntityPlayer player = event.getEntityPlayer();



 if (player.worldObj.isRemote) return;



 mana = player.getCapability(capprovider.MANA_CAP, null);
 stam = player.getCapability(stamprovider.MANA_CAP, null);


 mana.fill(50);
 //stam.fill(50);


 String message = "You refreshed yourself in the bed. You received 50 mana, you have "+ (int) mana.getMana() +" mana left.";

 player.addChatMessage(new TextComponentString(message));

 }



 @SubscribeEvent

 public void onPlayerFalls(LivingFallEvent event)

 {

 Entity entity = event.getEntity();



 if (entity.worldObj.isRemote || !(entity instanceof EntityPlayerMP) || event.getDistance() < 3) return;



 EntityPlayer player = (EntityPlayer) entity;

 mana = player.getCapability(capprovider.MANA_CAP, null);
 stam = player.getCapability(stamprovider.MANA_CAP, null);


 float points = mana.getMana();
 float points2 = stam.getStam();
 float cost = (int) event.getDistance() * 2;



 if (points > cost)

 {

 mana.consume(cost);



 String message = "You absorbed fall damage. It costed "+ (int) cost +" mana, you have "+ (int) mana.getMana() +" mana left.";

 player.addChatMessage(new TextComponentString(message));



 event.setCanceled(true);

 }

 }
 @SubscribeEvent
	public void event1(RenderGameOverlayEvent.Post e){
		if(e.getType() != ElementType.ALL)return;
		new GUIMana();
	}
 public ManaPick pick = new ManaPick(capability.material);
    @SubscribeEvent
	public void event2(BlockEvent.BreakEvent e){
	 if(e.getPlayer().getHeldItemMainhand().getItem() == capability.pickaxe1 && e.getPlayer().getHeldItemMainhand() != null || e.getPlayer().getHeldItemMainhand().getItem() == capability.pickaxe2){
		 mana = e.getPlayer().getCapability(capprovider.MANA_CAP, null);
		 String message = null;
		 if(mana.getMana() >= 15){
			 mana.consume(15);
			 message = "Hello there, you have consumed 15 mana, with "+ (int) mana.getMana() +" mana left.";
		 }
		 else if(mana.getMana() < 15){
			 e.setCanceled(true);
			 message = "You don't have enough mana.";
		 }
		 //e.setExpToDrop(25);
		 //e.getPlayer().getItemInUseCount();
		 e.getPlayer().addChatMessage(new TextComponentString(message));
	 	}
	}
 @SubscribeEvent
 public void event3(PlayerEvent.HarvestCheck e){
 }
}
