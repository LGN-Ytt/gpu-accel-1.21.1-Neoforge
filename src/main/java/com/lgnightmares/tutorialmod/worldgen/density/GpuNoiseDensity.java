package com.lgnightmares.tutorialmod.worldgen.density;


import com.electronwill.nightconfig.core.utils.ObservedMap;
import com.lgnightmares.tutorialmod.platform.GpuSupport;
import com.lgnightmares.tutorialmod.worldgen.NoiseSIMD;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class GpuNoiseDensity implements DensityFunction {




    public static final MapCodec<GpuNoiseDensity> CODEC = MapCodec.unit(GpuNoiseDensity::new);
    public static final class ResourceLocation
            implements Comparable<ResourceLocation>{



        @Override
        public int compareTo(@NotNull GpuNoiseDensity.ResourceLocation o) {
            return 0;
        }
    }



    public void ResourceLocation(String tutorialmod, String gpuNoise) {
        Registry.register(
                BuiltInRegistries.DENSITY_FUNCTION_TYPE,
                new ResourceLocation().toString(),
                GpuNoiseDensity.CODEC
        );
    }

    public static void register() {

    }



    @Override
    public double compute(FunctionContext ctx) {
        // scalar fallback (used rarely)
        return 0.0;
    }

    @Override
    public void fillArray(double[] values, ContextProvider ctx) {
        // THIS is where SIMD / GPU goes
        if (GpuSupport.isAvailable()) {
            ObservedMap GpuNoise = null;
            GpuNoise.compute((Object) values, (BiFunction) ctx);
        } else {
            NoiseSIMD.compute(values, ctx);
        }
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(this);
    }

    @Override
    public double minValue() {
        return 0;
    }

    @Override
    public double maxValue() {
        return 0;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return null;
    }
}
