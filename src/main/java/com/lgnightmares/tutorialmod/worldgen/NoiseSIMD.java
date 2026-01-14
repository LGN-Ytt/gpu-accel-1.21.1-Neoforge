package com.lgnightmares.tutorialmod.worldgen;
import com.lgnightmares.tutorialmod.worldgen.density.GpuNoiseDensity;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;
import net.minecraft.world.level.levelgen.DensityFunction;

public final class NoiseSIMD {
    DensityFunction function = new GpuNoiseDensity();
    private static final VectorSpecies<Float> SPECIES =
            FloatVector.SPECIES_PREFERRED;

    public static void add(float[] a, float[] b, float[] out) {
        int i = 0;
        int upper = SPECIES.loopBound(a.length);

        for (; i < upper; i += SPECIES.length()) {
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            va.add(vb).intoArray(out, i);
        }

        for (; i < a.length; i++) {
            out[i] = a[i] + b[i];
        }
    }

    public static void compute(double[] values, DensityFunction.ContextProvider ctx) {
    }
}