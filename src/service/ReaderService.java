package service;

import classes.Library;
import classes.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderService {

    private static ReaderService readerService = null;
    private WriteCsv write = WriteCsv.getInstance();
    private LibraryService libraryService = LibraryService.getInstance();
    private ReaderService () {

    }
    public static ReaderService getInstance(){
        if(readerService == null){
            readerService = new ReaderService();
        }
        return readerService;
    }
    public Reader newReader(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("First Name:");
        String FirstName = scanner.nextLine();

        System.out.println("Last Name:");
        String LastName = scanner.nextLine();

        System.out.println("Phone Number:");
        String phoneNumber = scanner.nextLine();
        Reader reader = new Reader(FirstName, LastName, phoneNumber);
        return reader;
    }
    //---------------
    public void addNewReader() throws IOException {
        Reader reader = newReader();
        libraryService.getLibrary().addReader(reader);

        write.WriteToCsv("src/resources/RW/reader_data.csv", libraryService.getLibrary().getReaders());


    }

    //------------
    public void allReaders() {
        ArrayList<Reader> readers = libraryService.getLibrary().getReaders();
        for (Reader reader : readers) {
            System.out.println(reader);
        }
    }

}
