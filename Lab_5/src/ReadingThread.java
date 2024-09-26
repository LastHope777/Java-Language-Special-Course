public class ReadingThread extends Thread{
    private LibraryI libraryI;

    public ReadingThread(LibraryI libraryI) {
        this.libraryI = libraryI;
    }

    @Override
    public void run() {
        if (libraryI == null) {
            System.out.println("Объекта не существует");
            return;
        }

        for (int index = 0; index < libraryI.getAmountOfBooks(); index++) {
            System.out.println("READ  " + libraryI.getAmountOfPagesInBook(index) + " from position " + index);
        }
    }
}
