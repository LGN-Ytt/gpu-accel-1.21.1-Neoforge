package com.lgnightmares.gpuaccel.gpuengine;
import com.lgnightmares.gpuaccel.gpuengine.GpuWorker;
import com.lgnightmares.gpuaccel.gpuengine.GpuKernels;
import java.util.concurrent.*;

import com.google.common.util.concurrent.AbstractListeningExecutorService;
import net.minecraft.world.level.levelgen.DensityFunction;
import com.lgnightmares.gpuaccel.gpuengine.Gpuworker;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public final class HybridDensityRunner {

    public static void fill(
            DensityFunction function,
            DensityFunction.ContextProvider ctx,
            double[] out
    ) {
        int samples = out.length;

        if (WorldgenScheduler.shouldUseGpu(samples)) {

            CompletableFuture<double[]> f =
                    GpuWorker.submit(function, ctx, samples);

            if (f.isDone()) {
                double[] gpu = f.getNow(null);
                if (gpu != null) {
                    System.arraycopy(gpu, 0, out, 0, samples);
                    return;
                }
            }
        }

        // CPU fallback (SIMD or scalar)
        function.fillArray(out, ctx);
    }
}
