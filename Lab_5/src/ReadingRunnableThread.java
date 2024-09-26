public class ReadingRunnableThread implements Runnable{
    private LibrarySynchronizer librarySynchronizer;

    public ReadingRunnableThread(LibrarySynchronizer booksSynchronizer) {
        this.librarySynchronizer = booksSynchronizer;
    }

    @Override
    public void run() {
        try {
            for (int index = 0; index < librarySynchronizer.getSynchNumOfBooks(); index++) {
                librarySynchronizer.read();
            }
        } catch (InterruptedException exc) {
            Thread.currentThread().interrupt();
        }
    }
}
