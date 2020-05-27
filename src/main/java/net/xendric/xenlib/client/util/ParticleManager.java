package net.xendric.xenlib.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleManager {
	private static Minecraft minecraft = Minecraft.getMinecraft();

	/**
	 * Particle manager spawning particles and not crashing client or server!
	 * @param world
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param clazz
	 * @param red
	 * @param green
	 * @param blue
	 * @param scaleBy
	 * @param maxAge
	 * @param nextIndexX
	 * @return Particle
	 */
	@SideOnly(Side.CLIENT)
	public static Particle spawnParticle(World world, double posX, double posY, double posZ, Particle clazz, float red,
			float green, float blue, float scaleBy, int maxAge, boolean nextIndexX) {
		if (minecraft != null && minecraft.getRenderViewEntity() != null && minecraft.effectRenderer != null) {
			int particleSetting = minecraft.gameSettings.particleSetting;
			if (particleSetting == 1 && world.rand.nextInt(3) == 0)
				particleSetting = 2;

			double entityX = minecraft.getRenderViewEntity().posX - posX;
			double entityY = minecraft.getRenderViewEntity().posY - posY;
			double entityZ = minecraft.getRenderViewEntity().posZ - posZ;

			if (entityX * entityX + entityY * entityY + entityZ * entityZ > 16d * 16d) {
				return null;
			} else if (particleSetting > 1) {
				return null;
			} else {
				Particle particle = null;

				if (clazz != null) {
					particle = clazz;
					particle.setRBGColorF(red, green, blue);
					particle.multipleParticleScaleBy(scaleBy);
					particle.setMaxAge(maxAge);

					if (nextIndexX)
						particle.nextTextureIndexX();
				}

				if (particle != null) {
					minecraft.effectRenderer.addEffect(particle);
					return particle;
				}
			}
		}
		return null;
	}
}
