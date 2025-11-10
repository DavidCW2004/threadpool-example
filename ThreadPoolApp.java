import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * The ThreadPoolApp class demonstrates how to use a thread pool
 * to execute multiple jobs concurrently using a fixed number of threads.
 *
 * Usage:
 *     java ThreadPoolApp <numberOfJobs> <numberOfThreads>
 *
 * Example:
 *     java ThreadPoolApp 5 2
 *
 * This will create 5 Job tasks and process them using 2 threads.
 */
public class ThreadPoolApp {

    /**
     * The main method validates command-line arguments, creates a fixed thread pool,
     * and submits Job tasks for execution.
     *
     * @param args Command-line arguments:
     *             args[0] = number of jobs to execute
     *             args[1] = number of threads in the pool
     */
    public static void main(String[] args) {
        if (args.length < 2)
            ThreadPoolApp.error();

        try {
            int numberOfJobs = Integer.parseInt(args[0]);
            int numberOfThreads = Integer.parseInt(args[1]);

            if (numberOfJobs < 1 || numberOfThreads < 1)
                ThreadPoolApp.error();

            // Create a thread pool with a fixed number of threads
            ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

            // Create and submit Job tasks to the pool
            Job[] jobs = new Job[numberOfJobs];
            for (int i = 0; i < numberOfJobs; i++) {
                jobs[i] = new Job(i);
                pool.execute(jobs[i]); // Executes the task using one of the pool threads
            }

            // Prevents new tasks from being submitted; waits for existing ones to finish
            pool.shutdown();

            System.out.println("Last line " + Thread.currentThread().getName());

        } catch (NumberFormatException e) {
            ThreadPoolApp.error();
        }
    }

    /**
     * Prints an error message explaining the correct program usage
     * and exits the application.
     */
    private static void error() {
        System.out.println("ThreadPoolApp must be run with two positive integer arguments.\n"
                + "The first is the number of jobs, and the second is the number of threads in the pool.");
        System.exit(0);
    }
}