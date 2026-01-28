package com.lgnightmares.gpuaccel.gpuengine;

import com.lgnightmares.gpuaccel.Config;

public final class WorldgenScheduler {
    private static volatile long lastDispatchNs = 0;
    private static volatile boolean gpuDisabled = false;

    public static boolean shouldUseGpu(int samples) {
        if (!Config.ENABLE_GPU.get()) return false;
        if (gpuDisabled) return false;

        if (samples > Config.MAX_GPU_BATCH.get()) return false;

        long now = System.nanoTime();
        if (now - lastDispatchNs < Config.MIN_DISPATCH_INTERVAL_NS.get())
            return false;

        return true;
    }

    public static void markDispatch() {
        lastDispatchNs = System.nanoTime();
    }

    public static void reportGpuTime(long timeNs) {
        if (timeNs > Config.MAX_GPU_TIME_NS.get()) {
            gpuDisabled = true;
        }
    }

    public static void resetGpu() {
        gpuDisabled = false;
    }
}

