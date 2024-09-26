import java.io.*;

public class Comics implements LibraryI, Serializable {
    private String titleOfComicsCollection;
    private String[] comicsInCollection;
    private int[] amountOfPagesInComics;
    private int amountOfPagesInStart;

    public Comics() {
        this.titleOfComicsCollection = "Название отсутствует";
        this.comicsInCollection = new String[1];
        this.amountOfPagesInComics = new int[this.comicsInCollection.length];
        this.amountOfPagesInStart = 1;
    }

    public Comics(String titleOfComicsCollection, int amountOfComics, int amountOfPagesInStart) {
        this.titleOfComicsCollection = titleOfComicsCollection;
        this.comicsInCollection = new String[amountOfComics];
        this.amountOfPagesInComics = new int[this.comicsInCollection.length];
        this.amountOfPagesInStart = amountOfPagesInStart;
    }

    public String getTitle() {
        return this.titleOfComicsCollection;
    }

    public void setTitle(String title) {
        this.titleOfComicsCollection = title;
    }

    public String getBooks(int i) {
        return this.comicsInCollection[i];
    }

    public int getAmountOfBooks() {
        return this.comicsInCollection.length;
    }

    public void setBooks(int i, String comics) {
        this.comicsInCollection[i] = comics;
    }

    public int getAmountOfPagesInStart() {
        return this.amountOfPagesInStart;
    }

    public void setAmountOfPagesInStart(int amountOfMissionsInPrologue) {
        this.amountOfPagesInStart = amountOfMissionsInPrologue;
    }

    public int getAmountOfPagesInBook(int i) {
        return this.amountOfPagesInComics[i];
    }

    public int getAmountOfPages() {
        return this.amountOfPagesInComics.length;
    }

    public void setAmountOfPagesInBook(int i, int amountOfPagesInComics) {
        this.amountOfPagesInComics[i] = amountOfPagesInComics;
    }

    public int getAmountOfPagesWithoutStart() throws AmountOfMissionsException {
        int sum = 0;

        for(int i = 0; i < this.amountOfPagesInComics.length; ++i) {
            if (this.amountOfPagesInComics[i] < this.amountOfPagesInStart) {
                throw new AmountOfMissionsException("Ошибка в списке количества миссий. Общее количества миссий не может быть меньше количества миссий в прологе");
            }

            sum += this.amountOfPagesInComics[i];
        }

        return sum - this.amountOfPagesInStart * this.amountOfPagesInComics.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Сборник комиксов: ").append(this.titleOfComicsCollection).append('\n');
        stringBuilder.append("Количество комиксов в сборнике: ").append(this.comicsInCollection.length).append('\n');

        try {
            stringBuilder.append("Общее количество страниц без учёта предысловия: ").append(this.getAmountOfPagesWithoutStart()).append('\n');
            stringBuilder.append("Количество страниц в предысловии: ").append(this.amountOfPagesInStart).append('\n');
            stringBuilder.append("Информация о комиксах:\n");

            for(int i = 0; i < this.comicsInCollection.length; ++i) {
                stringBuilder.append(i).append(") Название: ").append(this.comicsInCollection[i]).append(". Количество страниц: ").append(this.amountOfPagesInComics[i]).append(".").append("\n");
            }
        } catch (AmountOfMissionsException var3) {
            stringBuilder.append(var3.getMessage()).append('\n');
        }

        return stringBuilder.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            Comics newComicsCollection = (Comics)o;
            boolean equals = false;
            if (!this.titleOfComicsCollection.equals(newComicsCollection.getTitle())) {
                return false;
            } else if (this.amountOfPagesInStart != newComicsCollection.getAmountOfPagesInStart()) {
                return false;
            } else {
                int i;
                for(i = 0; this.comicsInCollection[i].equals(newComicsCollection.getBooks(i)); ++i) {
                }

                if (i != this.comicsInCollection.length) {
                    return false;
                } else {
                    int k;
                    for(k = 0; this.amountOfPagesInComics[k] == newComicsCollection.getAmountOfPagesInBook(k); ++k) {
                    }

                    if (k == this.amountOfPagesInComics.length) {
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
    public int hashCode() {
        return super.hashCode();
    }

    public void output(OutputStream out){
        DataOutputStream outputStream = new DataOutputStream(out);
        try {
            outputStream.writeUTF(getClass().getName());
            outputStream.writeUTF(titleOfComicsCollection);
            outputStream.writeInt(comicsInCollection.length);
            outputStream.writeInt(amountOfPagesInStart);

            for (int i = 0; i < comicsInCollection.length; i++) {
                outputStream.writeUTF(comicsInCollection[i]);
                outputStream.writeInt(amountOfPagesInComics[i]);
            }
            outputStream.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public void write(Writer out){
        PrintWriter printWriter = new PrintWriter(out);

        printWriter.println(getClass().getName());
        printWriter.println(titleOfComicsCollection);
        printWriter.println(comicsInCollection.length);
        printWriter.println(amountOfPagesInStart);

        for (int i = 0; i < comicsInCollection.length; i++) {
            printWriter.println(comicsInCollection[i]);
            printWriter.println(amountOfPagesInComics[i]);
        }
        printWriter.flush();
    }
}
