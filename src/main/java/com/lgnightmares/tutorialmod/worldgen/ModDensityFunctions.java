package com.lgnightmares.tutorialmod.worldgen;

import com.lgnightmares.tutorialmod.worldgen.density.GpuNoiseDensity;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;



import static com.lgnightmares.tutorialmod.TutorialMod.MOD_ID;

public class ModDensityFunctions {
    public static final ResourceKey<Registry<MapCodec<? extends DensityFunction>>> GPU_NOISE_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.parse("tutorialmod:gpu_noise"));


    public static void register() {
    }
}
