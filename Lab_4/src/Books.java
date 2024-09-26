import java.io.*;

public class Books implements LibraryI, Serializable { //Класс книги
    private String titleOfBooksCollection;  //Название сборника книг
    private String[] booksInCollection; //Книги в коллекции
    private int[] amountOfPagesInBook; //Количество страниц в книге
    private int amountOfPagesInStart; //Количество страниц в предысловии

    public Books() { //Конструктор без параметров
        this.titleOfBooksCollection = "Название отсутствует";
        this.booksInCollection = new String[1];
        this.amountOfPagesInBook = new int[this.booksInCollection.length];
        this.amountOfPagesInStart = 1;
    }

    public Books(String titleOfBooksCollection, int amountOfBooks, int amountOfPagesInStart) { //Конструктор с параметрами
        this.titleOfBooksCollection = titleOfBooksCollection;
        this.booksInCollection = new String[amountOfBooks];
        this.amountOfPagesInBook = new int[this.booksInCollection.length];
        this.amountOfPagesInStart = amountOfPagesInStart;
    }

    public String getTitle() { //Получить название сборника
        return this.titleOfBooksCollection;
    }

    public void setTitle(String title) {//Установить название сборника
        this.titleOfBooksCollection = title;
    }

    public String getBooks(int i) {  //Получить книгу
        if (i >= 0 && i < this.booksInCollection.length) {
            return this.booksInCollection[i];
        } else {
            throw new InvalidIndexException("Задан неверный индекс.");
        }
    }

    public int getAmountOfBooks() { //Получить количество книг
        return this.booksInCollection.length;
    }

    public void setBooks(int i, String book) { //Установить книгу
        if (i >= 0 && i < this.booksInCollection.length) {
            this.booksInCollection[i] = book;
        } else {
            throw new InvalidIndexException("Задан неверный индекс.");
        }
    }

    public int getAmountOfPagesInStart() { //Получить количество страниц с предысловием
        return this.amountOfPagesInStart;
    }

    public void setAmountOfPagesInStart(int amountOfMissionsInPrologue) { //Установить количество страниц с предысловием
        this.amountOfPagesInStart = amountOfMissionsInPrologue;
    }

    public int getAmountOfPagesInBook(int i) { //Получить количество страниц в книге
        if (i >= 0 && i < this.booksInCollection.length) {
            return this.amountOfPagesInBook[i];
        } else {
            throw new InvalidIndexException("Задан неверный индекс.");
        }
    }

    public int getAmountOfPages() { //Получить количество страниц
        return this.amountOfPagesInBook.length;
    }

    public void setAmountOfPagesInBook(int i, int amountOfPagesInBook) { //Установить количество страниц в книге
        if (i >= 0 && i < this.booksInCollection.length) {
            this.amountOfPagesInBook[i] = amountOfPagesInBook;
        } else {
            throw new InvalidIndexException("Задан неверный индекс.");
        }
    }

    public int getAmountOfPagesWithoutStart() throws AmountOfMissionsException { //Получить количество страниц без предысловия
        int sum = 0;

        for(int i = 0; i < this.amountOfPagesInBook.length; ++i) {
            if (this.amountOfPagesInBook[i] < this.amountOfPagesInStart) {
                throw new AmountOfMissionsException("Ошибка в списке количества страниц. Общее количество страниц не может быть меньше количества страниц в предысловии");
            }

            sum += this.amountOfPagesInBook[i];
        }

        return sum - this.amountOfPagesInStart * this.amountOfPagesInBook.length;
    }
    @Override
    public String toString() { //Переопределённый метод toString()
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Сборник книг: ").append(this.titleOfBooksCollection).append('\n');
        stringBuilder.append("Количество книг в сборнике: ").append(this.booksInCollection.length).append('\n');

        try {
            stringBuilder.append("Общее количество страниц без учета страниц с предысловием: ").append(this.getAmountOfPagesWithoutStart()).append('\n');
            stringBuilder.append("Количество страниц с предысловием книги: ").append(this.amountOfPagesInStart).append('\n');
            stringBuilder.append("Информация о книгах:\n");

            for(int i = 0; i < this.booksInCollection.length; ++i) {
                stringBuilder.append(i).append(") Название: ").append(this.booksInCollection[i]).append(". Количество страниц: ").append(this.amountOfPagesInBook[i]).append(".").append("\n");
            }
        } catch (AmountOfMissionsException var3) {
            stringBuilder.append(var3.getMessage()).append('\n');
        }

        return stringBuilder.toString();
    }
    @Override
    public boolean equals(Object o) { //Переопределённый метод equals()
        if (o != null && o.getClass() == this.getClass()) {
            Books newBooksCollection = (Books) o;
            boolean equals = false;
            if (!this.titleOfBooksCollection.equals(newBooksCollection.getTitle())) {
                return false;
            } else if (this.amountOfPagesInStart != newBooksCollection.getAmountOfPagesInStart()) {
                return false;
            } else {
                int i;
                for(i = 0; this.booksInCollection[i].equals(newBooksCollection.getBooks(i)); ++i) {
                }

                if (i != this.booksInCollection.length) {
                    return false;
                } else {
                    int k;
                    for(k = 0; this.amountOfPagesInBook[k] == newBooksCollection.getAmountOfPagesInBook(k); ++k) {
                    }

                    if (k == this.amountOfPagesInBook.length) {
                        equals = true;
                    }

                    return equals;
                }
            }
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() { //Переопределённый метод hashCode()
        return super.hashCode();
    }

    public void output(OutputStream out){ //Запись информации
        DataOutputStream outputStream = new DataOutputStream(out);
        try {
            outputStream.writeUTF(getClass().getName());
            outputStream.writeUTF(titleOfBooksCollection);
            outputStream.writeInt(booksInCollection.length);
            outputStream.writeInt(amountOfPagesInStart);

            for (int i = 0; i < booksInCollection.length; i++) {
                outputStream.writeUTF(booksInCollection[i]);
                outputStream.writeInt(amountOfPagesInBook[i]);
            }
            outputStream.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public void write(Writer out){ //Вывод информации
        PrintWriter printWriter = new PrintWriter(out);

        printWriter.println(getClass().getName());
        printWriter.println(titleOfBooksCollection);
        printWriter.println(booksInCollection.length);
        printWriter.println(amountOfPagesInStart);


        for (int i = 0; i < booksInCollection.length; i++) {
            printWriter.println(booksInCollection[i]);
            printWriter.println(amountOfPagesInBook[i]);
        }
        printWriter.flush();
    }
}
