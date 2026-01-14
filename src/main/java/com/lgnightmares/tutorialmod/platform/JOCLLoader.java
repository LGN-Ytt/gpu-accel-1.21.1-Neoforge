package com.lgnightmares.tutorialmod.platform;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class JOCLLoader {
    private static boolean loaded = false;

    public static synchronized boolean load() {
        if (loaded) return true;

        try {
            String os = System.getProperty("os.name").toLowerCase();
            String lib;

            if (os.contains("win")) {
                lib = "natives/windows-x86_64/jocl.dll";
            } else if (os.contains("mac")) {
                lib = "natives/macos-x86_64/libjocl.dylib";
            } else {
                lib = "natives/linux-x86_64/libjocl.so";
            }

            try (InputStream in = JOCLLoader.class
                    .getClassLoader()
                    .getResourceAsStream(lib)) {

                if (in == null) throw new RuntimeException("JOCL native missing");

                Path temp = Files.createTempFile("jocl", lib.substring(lib.lastIndexOf('.')));
                Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
                temp.toFile().deleteOnExit();

                System.load(temp.toAbsolutePath().toString());
            }

            loaded = true;
            return true;

        } catch (Throwable t) {
            System.err.println("[MyMod] JOCL disabled (GPU unavailable)");
            t.printStackTrace();
            return false;
        }
    }
}