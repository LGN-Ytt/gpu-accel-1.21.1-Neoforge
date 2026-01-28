package com.lgnightmares.gpuaccel.gpuengine;
import com.lgnightmares.gpuaccel.Gpukernals;

import net.minecraft.world.level.levelgen.DensityFunction;

import java.security.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Gpuworker {

    private static final ExecutorService EXECUTOR =
            Executors.newSingleThreadExecutor(r -> {
                Thread t = new Thread(r, "WorldgenGPU");
                t.setDaemon(true);
                return t;
            });

    public static CompletableFuture<double[]> submit(
            DensityFunction function,
            DensityFunction.ContextProvider ctx,
            int size,

            Provider GpuKernels) {
        return CompletableFuture.supplyAsync(() -> {
            long start = System.nanoTime();
            try {
                double[] out = new double[size];


                GpuKernels.compute(function, ctx, out); // your kernel
                return out;
            } finally {
                long time = System.nanoTime() - start;
                WorldgenScheduler.markDispatch();
                WorldgenScheduler.reportGpuTime(time);
            }
        }, EXECUTOR);
    }

}
