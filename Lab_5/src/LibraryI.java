import java.io.OutputStream;
import java.io.Writer;

public interface LibraryI {
    String getTitle();

    void setTitle(String var1);

    String getBooks(int var1);

    int getAmountOfBooks();

    void setBooks(int var1, String var2);

    int getAmountOfPagesInStart();

    void setAmountOfPagesInStart(int var1);

    int getAmountOfPagesInBook(int var1);

    int getAmountOfPages();

    void setAmountOfPagesInBook(int var1, int var2);

    int getAmountOfPagesWithoutStart() throws AmountOfMissionsException;

    void output(OutputStream out);

    void write(Writer out);
}