import java.io.OutputStream;
import java.io.Writer;

public interface LibraryI { //Интерфейс
    String getTitle();//Получить название

    void setTitle(String var1);//Установить название

    String getBooks(int var1);//Получить книгу

    int getAmountOfBooks();//Получить количество книг

    void setBooks(int var1, String var2);//Установить книгу

    int getAmountOfPagesInStart();//Получить количество страниц в предысловии

    void setAmountOfPagesInStart(int var1); //Установить количество страниц в предысловии

    int getAmountOfPagesInBook(int var1); //

    int getAmountOfPages(); //Получить количество страниц

    void setAmountOfPagesInBook(int var1, int var2); //Установить количество страниц в книге

    int getAmountOfPagesWithoutStart() throws AmountOfMissionsException; //Получить количество страниц без предысловия

    void output(OutputStream out); //Запись информации

    void write(Writer out); //Вывод информации
}
