package net.xendric.xenlib.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleManager {
	private static Minecraft minecraft = Minecraft.getMinecraft();
	private static float particleRed, particleGreen, particleBlue, multipleParticleScaleBy;
	private static int maxAge;
	private static boolean nextTextureIndexX;

	/**
	 * Particle manager spawning particles and not crashing client or server!
	 * 
	 * @param world
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param clazz
	 * @return Particle
	 */
	public static Particle spawnParticle(World world, double posX, double posY, double posZ, Particle clazz) {
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
				Particle fx = null;

				if (clazz != null) {
					fx = clazz;
					fx.setRBGColorF(particleRed, particleGreen, particleBlue);
					fx.multipleParticleScaleBy(multipleParticleScaleBy);
					fx.setMaxAge(maxAge);

					if (nextTextureIndexX)
						fx.nextTextureIndexX();
				}

				if (fx != null) {
					minecraft.effectRenderer.addEffect(fx);
					return fx;
				}
			}
		}
		return null;
	}

	public static void setParticleRGB(float red, float green, float blue) {
		particleRed = red;
		particleGreen = green;
		particleBlue = blue;
	}

	public static void setMultipleParticleScaleBy(float scale) {
		multipleParticleScaleBy = scale;
	}

	public static void setMaxAge(int age) {
		maxAge = age;
	}

	public static void doNextTextureIndexX(boolean doFunction) {
		nextTextureIndexX = doFunction;
	}
}
