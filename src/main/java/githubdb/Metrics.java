package githubdb;

/**
 * Gather performance of program.
 */
public class Metrics {
    private static long startTime;
    private static long stopTime;

    private Metrics() throws InstantiationException {
        throw new InstantiationException("Instances of this type are forbidden.");
    }

    /**
     * Starts timer.
     */
    public static boolean start() {
        startTime = System.currentTimeMillis();
        return true;
    }

    /**
     * Stops timer.
     */
    public static boolean stop() {
        stopTime = System.currentTimeMillis();
        return true;
    }

    /**
     * Calculates time of execution.
     */
    public static void getExecutionTime() {
        long estimatedTime = stopTime - startTime;
        long seconds = estimatedTime / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        System.out.println("Execution time is " + estimatedTime + " ms or " + minutes + " min " + seconds + " sec");
    }

    /**
     * Gets memory used by program.
     */
    public static void getUsedMemory() {
        Runtime.getRuntime().gc();
        long usedBytes = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        System.out.println("Used memory is " + convertToMegabytes(usedBytes) + " mb");
    }

    /**
     * Converts bytes into megabytes.
     *
     * @param bytes -  bytes to convert.
     * @return
     */
    private static double convertToMegabytes(long bytes) {
        return bytes / (1024.0 * 1024.0);
    }

    /**
     * Gathers all metrics.
     */
    public static boolean getAllMetrics() {
        getExecutionTime();
        getUsedMemory();
        return true;
    }
}

