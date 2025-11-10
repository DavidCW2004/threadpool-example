public class Job implements Runnable {
    private final int jobNumber;

    public Job(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Override
    public void run() {
        System.out.println("Job " + jobNumber + " is being processed by thread: "
                + Thread.currentThread().getName());

        try {
            Thread.sleep(1000); // simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt status
        }

        System.out.println("Job " + jobNumber + " is ending in thread: "
                + Thread.currentThread().getName());
    }
}

/**
 * im so skibidi
 */