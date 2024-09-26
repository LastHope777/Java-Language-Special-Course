import java.io.*;
import java.util.Scanner;

public class InputOutputBooks { //Класс для взаимодействия с потоками
    public static void outputBooksI(LibraryI o, OutputStream out) { //Запись в байтовый поток
        o.output(out);
    }

    public static LibraryI inputLibraryI(InputStream in) { //Чтение из байтового потока
        LibraryI o;
        DataInputStream inputStream = new DataInputStream(in);

        try {
            String className = inputStream.readUTF();
            String title = inputStream.readUTF();
            int amountOfBooks = inputStream.readInt();
            int amountOfPagesInStart = inputStream.readInt();

            if (className.equals(Comics.class.getName())) {
                o = new Comics(title, amountOfBooks, amountOfPagesInStart);
            } else if (className.equals(Books.class.getName())) {
                o = new Books(title, amountOfBooks, amountOfPagesInStart);
            } else o = null;
            if (o != null) {
                final int len = o.getAmountOfBooks();
                String book;
                for (int i = 0; i < len; i++) {
                    book = inputStream.readUTF();
                    int amountOfPagesInBook = inputStream.readInt();

                    o.setBooks(i, book);
                    o.setAmountOfPagesInBook(i, amountOfPagesInBook);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            o = null;
        }
        return o;
    }

    public static void writeLibraryI(LibraryI o, Writer out) { //Запись в текстовый поток
        o.write(out);
    }

    public static LibraryI readLibraryI(BufferedReader reader) {//Чтение из текстового потока
        LibraryI o = null;

        try {
            while (reader.ready()) {
                String className = reader.readLine();
                String title = reader.readLine();
                int amountOfBooks = Integer.parseInt(reader.readLine());
                int amountOfPagesInStart = Integer.parseInt(reader.readLine());

                if (className.equals(Comics.class.getName())) {
                    o = new Comics(title, amountOfBooks, amountOfPagesInStart);
                } else if (className.equals(Books.class.getName())) {
                    o = new Books(title, amountOfBooks, amountOfPagesInStart);
                } else o = null;
                if (o != null) {
                    final int len = o.getAmountOfBooks();
                    String book;
                    for (int i = 0; i < len; i++) {
                        book = reader.readLine();
                        int amountOfPagesInBook = Integer.parseInt(reader.readLine());

                        o.setBooks(i, book);
                        o.setAmountOfPagesInBook(i, amountOfPagesInBook);
                    }
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            o = null;
        }
        return o;
    }

    public static void serializeLibraryI (LibraryI o, OutputStream out){ //Сериализация
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(o);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
    public static LibraryI deserializeLibraryI(InputStream in){ //Десериализация
        LibraryI o;
        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            o = (LibraryI) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            o = null;
        }
        return o;
    }

    public static void writeFormatLibraryI(LibraryI o, Writer out) { //Запись в форматный поток

        // Создание потока и запись базовых данных в поток
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(o.getClass().getName());
        printWriter.println(o.getTitle());
        printWriter.println(o.getAmountOfBooks());
        printWriter.println(o.getAmountOfPagesInStart());

        for (int i = 0; i < o.getAmountOfBooks(); i++) {
            printWriter.println(o.getBooks(i));
            printWriter.println(o.getAmountOfPagesInBook(i));
        }

        // Запись
        printWriter.flush();
    }


    public static LibraryI readFormatLibraryI(Scanner in) { //Чтение из форматного потока
        LibraryI o = null;
        try {
            String className = in.nextLine();
            String title = in.nextLine();
            int amountOfFilms = in.nextInt();
            int amountOfMainRoles = in.nextInt();
            in.nextLine();

            if (className.equals(Comics.class.getName()))
                o = new Comics(title, amountOfFilms, amountOfMainRoles);
            else if (className.equals(Books.class.getName()))
                o = new Books(title, amountOfFilms, amountOfMainRoles);

            if (o != null)
                for (int i = 0; i < o.getAmountOfBooks(); i++) {
                    String book = in.nextLine();
                    int amountOfRoles = in.nextInt();
                    in.nextLine();

                    o.setBooks(i, book);
                    o.setAmountOfPagesInBook(i, amountOfRoles);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return o;
    }


public static LibraryI getSynchronizedBooks(LibraryI libraryI) {
        return new SynchronizedBooks(libraryI);
    }

}