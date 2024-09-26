import java.io.OutputStream;
import java.io.Writer;

public class SynchronizedBooks implements LibraryI{
    private final LibraryI libraryI;

    public SynchronizedBooks(LibraryI libraryI) {
        this.libraryI = libraryI;
    }

    @Override
    public synchronized String getTitle() {
        return libraryI.getTitle();
    }

    @Override
    public synchronized void setTitle(String title) {
        libraryI.setTitle(title);
    }

    @Override
    public synchronized int getAmountOfBooks() {
        return libraryI.getAmountOfBooks();
    }

    @Override
    public synchronized String getBooks(int num){
        return libraryI.getBooks(num);
    }

    @Override
    public synchronized void setBooks(int index, String game){
        libraryI.setBooks(index, game);
    }

    @Override
    public synchronized int getAmountOfPagesInStart() {
        return libraryI.getAmountOfPagesInStart();
    }

    @Override
    public synchronized void setAmountOfPagesInStart(int num) {
        libraryI.setAmountOfPagesInStart(num);
    }

    @Override
    public synchronized int getAmountOfPagesInBook(int index) {
        return libraryI.getAmountOfPagesInBook(index);
    }

    @Override
    public synchronized int  getAmountOfPages() {
        return libraryI.getAmountOfPages();
    }

    @Override
    public synchronized void setAmountOfPagesInBook(int index, int num) {
        libraryI.setAmountOfPagesInBook(index, num);
    }

    @Override
    public synchronized int getAmountOfPagesWithoutStart() throws AmountOfMissionsException {
        return libraryI.getAmountOfPagesWithoutStart();
    }

    @Override
    public synchronized void output(OutputStream out) {
        libraryI.output(out);
    }

    @Override
    public synchronized void write(Writer out) {
        libraryI.write(out);
    }
}
