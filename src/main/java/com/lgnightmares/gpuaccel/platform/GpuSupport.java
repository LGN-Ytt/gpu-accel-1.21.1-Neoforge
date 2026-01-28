package com.lgnightmares.gpuaccel.platform;

import org.jocl.CL;

public final class GpuSupport {
    private static Boolean available;

    public static boolean isAvailable() {
        if (available != null) return available;

        try {
            if (!JOCLLoader.load()) {
                available = false;
                return false;
            }

            CL.setExceptionsEnabled(true);
            int[] platforms = new int[1];
            CL.clGetPlatformIDs(0, null, platforms);

            available = platforms[0] > 0;
            return available;

        } catch (Throwable t) {
            available = false;
            return false;
        }
    }
}