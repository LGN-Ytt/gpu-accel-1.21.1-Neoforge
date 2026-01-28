package com.lgnightmares.gpuaccel;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.IntValue MAX_GPU_BATCH;
    public static final ModConfigSpec.LongValue MIN_DISPATCH_INTERVAL_NS;
    public static final ModConfigSpec.LongValue MAX_GPU_TIME_NS;
    public static final ModConfigSpec.BooleanValue ENABLE_GPU;

    static {
        BUILDER.push("gpu");

        ENABLE_GPU = BUILDER
                .comment("Enable GPU acceleration for chunk generation")
                .define("enableGpu", true);

        MAX_GPU_BATCH = BUILDER
                .comment("Maximum number of samples per GPU dispatch")
                .defineInRange("maxGpuBatch", 4096, 512, 131072);

        MIN_DISPATCH_INTERVAL_NS = BUILDER
                .comment("Minimum nanoseconds between GPU dispatches")
                .defineInRange("minDispatchIntervalNs", 3_000_000L, 0L, 100_000_000L);

        MAX_GPU_TIME_NS = BUILDER
                .comment("Maximum allowed GPU kernel time before fallback (ns)")
                .defineInRange("maxGpuTimeNs", 4_000_000L, 100_000L, 50_000_000L);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }


}
