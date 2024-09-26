import java.util.Random;

public class WritingRunnableThread implements Runnable{
    private LibrarySynchronizer librarySynchronizer;

    public WritingRunnableThread(LibrarySynchronizer booksSynchronizer) {
        this.librarySynchronizer = booksSynchronizer;
    }
    @Override
    public void run() {
        try {
            int amountOfBooks;
            for (int index = 0; index < librarySynchronizer.getSynchNumOfBooks(); index++) {
                Random rand = new Random();
                amountOfBooks = 3 + rand.nextInt(30 - 3 + 1);
                librarySynchronizer.write(amountOfBooks);
            }
        } catch (InterruptedException exc) {
            Thread.currentThread().interrupt();
        }
    }
}
