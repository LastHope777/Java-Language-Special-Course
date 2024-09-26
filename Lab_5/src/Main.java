import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryI[] db = null;

        Scanner scan = new Scanner(System.in);
        String menu, str;
        int length;

        String byteFile = "byteBooksI.byte";
        String textFile = "textBooksI.txt";
        String serializableFile = "serializableBooks.bin";
        String formatFile = "formatBooks.form";

        do {
            System.out.print("Меню:\n" +
                    "1 - Автоматически заполнить базу\n" +
                    "2 - Список элементов\n" +
                    "3 - Найти в базе объекты, функциональный метод которых возвращает одинаковый результат, поместить такие объекты в другой массив\n" +
                    "4 - Разбить исходный массив на два массива, в которых будут храниться однотипные элементы\n" +
                    "5 - Записать базу в байтовый поток\n" +
                    "6 - Считать базу из байтового потока\n" +
                    "7 - Записать базу в символьный поток\n" +
                    "8 - Считать базу из текстового потока\n" +
                    "9 - Сериализовать базу\n"+
                    "10 - Десериализовать базу\n" +
                    "11 - Записать базу в форматный поток\n" +
                    "12 - Считать базу из форматного потока\n ==============НОВЫЕ============== \n" +
                    "13 - Write: ### to position ### и Read: ### from position ###\n"+
                    "14 - write-read-write-read\n"+
                    "0 - Выйти\n" +
                    "Выберите пункт меню: ");
            menu = scan.nextLine();
            switch (menu) {
                case "1": {
                    db = createAndFillDbAutomatically();
                    System.out.println("База создана и заполнена автоматически.");
                    break;
                }
                case "2": {
                    System.out.print("База данных: ");
                    if (db == null) {
                        System.out.println("Не существует.\n");
                    } else {
                        System.out.println();
                        for (int i = 0; i < db.length; i++) {
                            System.out.print("[" + i + "] ");
                            if (db[i] == null) {
                                System.out.println("Элемента не существует");
                            } else {
                                System.out.println(db[i].getTitle());
                                System.out.println();
                                System.out.println(db[i]);
                            }
                            System.out.println();
                        }
                    }
                    break;
                }

                case "3": {

                    if (db == null) {
                        System.out.print("База данных не существует ");
                    } else {
                        int amountOfCollectionWithSamePagesAmount = 0;
                        for (int i = 0; i < db.length; i++) {
                            for (int j = i + 1; j < db.length; j++) {
                                try {
                                    if (db[i].getAmountOfPagesWithoutStart() == db[j].getAmountOfPagesWithoutStart()) {
                                        amountOfCollectionWithSamePagesAmount++;
                                    }
                                } catch (AmountOfMissionsException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (amountOfCollectionWithSamePagesAmount > 0) {
                            ArrayList<ArrayList<LibraryI>> arrayList = new ArrayList<ArrayList<LibraryI>>();
                            ArrayList<LibraryI> arr = new ArrayList<LibraryI>();
                            for (int i = 0; i < db.length; i++) {
                                for (int j = i + 1; j < db.length; j++) {
                                    try {
                                        if (db[i].getAmountOfPagesWithoutStart() == db[j].getAmountOfPagesWithoutStart()) {
                                            if(!arr.contains(db[i])){arr.add(db[i]);}
                                            if(!arr.contains(db[j])){arr.add(db[j]);}

                                        }
                                    } catch (AmountOfMissionsException e) {
                                        e.printStackTrace();
                                    }
                                }
                                arrayList.add(arr);
                            }

                            System.out.println("База успешно разделена");
                            System.out.println("База данных: ");
                            for(int j = 0; j < arrayList.size()-1;j++) {
                                for (int i = 0; i < arr.size(); i++) {
                                    System.out.print("[" + j + "." + i + "] ");
                                    System.out.println(arrayList.get(j).get(i).getTitle());
                                    System.out.println();
                                    System.out.println(arrayList.get(j).get(i));
                                    System.out.println();
                                }
                            }
                        } else
                            System.out.println("В базе нет объектов, функциональный метод которых возвращает одинаковый результат\n");

                    }
                    break;
                }

                case "4": {
                    System.out.println("разбить исходный массив на два массива, в которых будут храниться однотипные элементы\n");
                    LibraryI[] arrSeries, arrCollections;
                    if (db == null) {
                        System.out.print("База данных не существует ");
                    } else {

                        int countSeries = 0;
                        int countCollections = 0;
                        for (int i = 0; i < db.length; i++) {
                            if (db[i] instanceof Books) {
                                countSeries++;
                            }
                            if (db[i] instanceof Comics) {
                                countCollections++;
                            }
                        }
                        if (countSeries > 0) {
                            arrSeries = new Books[countSeries];
                            int j = 0;
                            for (int i = 0; i < db.length; i++) {
                                if (db[i] instanceof Books && j < arrSeries.length) {
                                    arrSeries[j] = db[i];
                                    j++;
                                }
                            }
                            System.out.println("Сформирован список книг");
                            System.out.println("Список книг: ");
                            for (int i = 0; i < arrSeries.length; i++) {
                                System.out.print("[" + i + "] ");
                                System.out.println(arrSeries[i].getTitle());
                                System.out.println();
                                System.out.println(arrSeries[i]);
                                System.out.println();
                            }
                        }
                        if (countCollections > 0) {
                            arrCollections = new Comics[countCollections];
                            int j = 0;
                            for (int i = 0; i < db.length; i++) {
                                if (db[i] instanceof Comics && j < arrCollections.length) {
                                    arrCollections[j] = db[i];
                                    j++;
                                }
                            }
                            System.out.println("Сформирован список комиксов");
                            System.out.println("Список сборников комиксов: ");
                            for (int i = 0; i < arrCollections.length; i++) {
                                System.out.print("[" + i + "] ");
                                System.out.println(arrCollections[i].getTitle());
                                System.out.println();
                                System.out.println(arrCollections[i]);
                                System.out.println();
                            }
                        }
                        System.out.println();

                    }
                    break;
                }

                case "5": {
                    if (db == null) {
                        System.out.println("База не задана");
                    } else {
                        FileOutputStream fileOutputStream;
                        try {
                            fileOutputStream = new FileOutputStream(byteFile);


                            for (LibraryI o : db) {
                                InputOutputBooks.outputBooksI(o, fileOutputStream);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();

                            System.out.println("База успешно записана в байтовый поток");
                        } catch (IOException exc) {
                            System.out.println(exc.getMessage());
                        }
                    }
                    break;
                }
                case "6": {

                    FileInputStream fileInputStream;
                    try {
                        fileInputStream = new FileInputStream(byteFile);
                        for (int i = 0; i < db.length; i++) {
                            db[i] = InputOutputBooks.inputLibraryI(fileInputStream);
                            System.out.println(db[i]);
                        }
                        fileInputStream.close();

                        System.out.println("База успешно считана из байтового потока");
                    } catch ( IOException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                }
                case "7": {
                    if (db == null) {
                        System.out.println("База не задана");
                    } else {
                        FileWriter fileWriter;

                        try {
                            fileWriter = new FileWriter(textFile);
                            for (LibraryI book : db)
                                InputOutputBooks.writeLibraryI(book, fileWriter);
                            fileWriter.flush();
                            fileWriter.close();

                            System.out.println("База успешно записана в текстовый поток");
                        } catch (IOException exc) {
                            System.out.println(exc.getMessage());
                        }
                    }
                    break;
                }
                case "8": {
                    BufferedReader fileReader;

                    try {
                        fileReader = new BufferedReader(new FileReader(textFile));
                        for (int i = 0; i < db.length; i++) {
                            db[i] = InputOutputBooks.readLibraryI(fileReader);
                            System.out.println(db[i]);
                        }
                        fileReader.close();

                        System.out.println("База успешно считана из текстового потока");
                    } catch (IOException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                }
                case "9": {
                    if (db == null) {
                        System.out.println("операция невозможна: массив не задан");
                    } else {
                        FileOutputStream fileOutputStream;
                        try {
                            fileOutputStream = new FileOutputStream(serializableFile);
                            for (LibraryI book : db)
                                InputOutputBooks.serializeLibraryI(book, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();

                            System.out.println("База успешно сериализована");
                        } catch (IOException exc) {
                            System.out.println(exc.getMessage());
                        }
                    }
                    break;
                }
                case "10": {
                    FileInputStream fileInputStream;
                    try {
                        fileInputStream = new FileInputStream(serializableFile);
                        for (int i = 0; i < db.length; i++) {
                            db[i] = InputOutputBooks.deserializeLibraryI(fileInputStream);
                            System.out.println(db[i]);
                        }fileInputStream.close();

                        System.out.println("База успешно десериализована");
                    } catch (IOException exc) {
                        System.out.println(exc.getMessage());
                    }
                    System.out.print("Десериализованная база данных: ");
                    break;
                }
                case "11": {
                    if (db == null) {
                        System.out.println("База не задана");
                    } else {
                        PrintWriter formatWriter;

                        try {
                            formatWriter = new PrintWriter(formatFile);
                            for (LibraryI book : db)
                                InputOutputBooks.writeFormatLibraryI(book, formatWriter);
                            formatWriter.flush();
                            formatWriter.close();

                            System.out.println("База успешно записана в форматный поток");
                        } catch (IOException exc) {
                            System.out.println(exc.getMessage());
                        }
                    }
                    break;
                }
                case "12": {
                    Scanner formatReader;

                    try {
                        formatReader = new Scanner(new FileReader(formatFile));
                        for (int i = 0; i < db.length; i++) {
                            db[i] = InputOutputBooks.readFormatLibraryI(formatReader);
                            System.out.println(db[i]);
                        }
                        formatReader.close();

                        System.out.println("База успешно считана из форматного потока");
                    } catch (IOException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                }
                case "13": {
                    System.out.println("\n");
                    Random rand = new Random();
                    int amountOfPagesInStart = 3 + rand.nextInt(30 - 3 + 1);

                    LibraryI books = new SynchronizedBooks( new Books("Новая подборка книг", 4, amountOfPagesInStart));
                    books = InputOutputBooks.getSynchronizedBooks(books);
                    WritingThread wt = new WritingThread(books);
                    ReadingThread rt = new ReadingThread(books);


                    wt.start();


                    rt.start();
                    break;
                }
                case "14": {
                    System.out.println("\n");
                    Random rand = new Random();
                    int amountOfPagesInStart = 3 + rand.nextInt(30 - 3 + 1);

                    LibraryI comics = new Comics("Новая подборка комиксов", 3, amountOfPagesInStart);
                    comics = InputOutputBooks.getSynchronizedBooks(comics);
                    LibrarySynchronizer librarySynchronizer = new LibrarySynchronizer(comics);
                    WritingRunnableThread wrt = new WritingRunnableThread(librarySynchronizer);
                    ReadingRunnableThread rrt = new ReadingRunnableThread(librarySynchronizer);

                    new Thread(wrt).start();
                    new Thread(rrt).start();
                    break;
                }
                default:
                    System.out.println("Выбран неверный пункт меню");
                    break;
            }
            System.out.println();
        } while (!menu.equals("0"));
    }

    public static LibraryI[] createAndFillDbAutomatically() {
        int length = 2;
        LibraryI[] autoDb = new LibraryI[length];
        autoDb[0] = new Comics("Мои любимые комиксы", 2, 2);
        autoDb[0].setBooks(0, "Человек-паук");
        autoDb[0].setAmountOfPagesInBook(0, 40);
        autoDb[0].setBooks(1, "Бэтмен");
        autoDb[0].setAmountOfPagesInBook(1, 96);

        autoDb[1] = new Books("Мои любимые книги", 2, 2);
        autoDb[1].setBooks(0, "Про100Кухня");
        autoDb[1].setAmountOfPagesInBook(0, 40);
        autoDb[1].setBooks(1, "Маленький принц");
        autoDb[1].setAmountOfPagesInBook(1, 96);


        return autoDb;
    }
}
