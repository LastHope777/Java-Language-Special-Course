import java.util.Random;

public class WritingThread extends Thread{
    private LibraryI libraryI;

    public WritingThread(LibraryI libraryI) {
        this.libraryI = libraryI;
    }

    @Override
    public void run() {
        if (libraryI == null) {
            System.out.println("Объекта не существует");
            return;
        }

        int amountOfPagesInBook;
        for (int index = 0; index < libraryI.getAmountOfBooks(); index++) {
            Random rand = new Random();
            amountOfPagesInBook = 3 + rand.nextInt(30 - 3 + 1);
            libraryI.setAmountOfPagesInBook(index, amountOfPagesInBook);
            System.out.println("WRITE " + amountOfPagesInBook+ " to   position " + index);
        }
    }
}
