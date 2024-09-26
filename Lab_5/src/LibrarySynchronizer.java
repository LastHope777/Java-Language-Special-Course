public class LibrarySynchronizer {
    private final LibraryI libraryI;
    private volatile int currIndex = 0;
    private volatile boolean isSet = false;

    public LibrarySynchronizer(LibraryI libraryI) {
        this.libraryI = libraryI;
    }

    void write(int val) throws InterruptedException {
        synchronized (libraryI) {
            if (!canWrite()) {
                throw new InterruptedException();
            }
            while (isSet) {
                libraryI.wait();
            }

            libraryI.setAmountOfPagesInBook(currIndex, val);
            isSet = true;
            System.out.println("WRITE " + val + " to   position " + currIndex);

            libraryI.notifyAll();
        }
    }

    private boolean canWrite() {
        return (!isSet && currIndex < libraryI.getAmountOfBooks() || (isSet && currIndex < libraryI.getAmountOfBooks() - 1));
    }

    void read() throws InterruptedException {
        int val;
        synchronized (libraryI) {
            if (!canRead()) {
                throw new InterruptedException();
            }
            while (!isSet) {
                libraryI.wait();
            }

            val = libraryI.getAmountOfPagesInBook(currIndex);
            isSet = false;
            System.out.println("READ  " + val + " from position " + currIndex);
            currIndex++;

            libraryI.notifyAll();
        }
    }

    private boolean canRead() {
        return currIndex < libraryI.getAmountOfBooks();
    }

    int getSynchNumOfBooks() {
        return libraryI.getAmountOfBooks();
    }
}
