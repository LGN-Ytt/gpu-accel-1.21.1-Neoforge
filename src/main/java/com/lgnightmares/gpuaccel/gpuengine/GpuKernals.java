package com.lgnightmares.gpuaccel.gpuengine;

import net.minecraft.world.level.levelgen.DensityFunction;

public final class GpuKernals {
    private void GpuKernels() {
    } // static-only utility class

    /**
     * Compute the density function for a batch of samples.
     * For now, this calls the CPU fillArray method.
     *
     * @param function The density function to evaluate
     * @param ctx      Context provider for noise evaluation
     * @param out      Output array to fill
     */
    public static void compute (DensityFunction function, DensityFunction.FunctionContext ctx, double[] out){
        // CPU fallback
        function.fillArray(out, (DensityFunction.ContextProvider) ctx);
    }

}