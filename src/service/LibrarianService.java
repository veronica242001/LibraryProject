package service;

import models.Librarian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LibrarianService {

    private static LibrarianService librarianService = null;
    private WriteCsv write = WriteCsv.getInstance();
    private LibraryService libraryService = LibraryService.getInstance();

    private LibrarianService(){};

    public static LibrarianService getInstance(){
        if(librarianService == null){
            librarianService = new LibrarianService();
        }
        return librarianService;
    }
    public Librarian newLibrarian(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("First Name:");
        String FirstName = scanner.nextLine();

        System.out.println("Last Name:");
        String LastName = scanner.nextLine();

        System.out.println("Phone Number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Salary:");
        String s = scanner.nextLine();
        float salary = Float.parseFloat(s);

        Librarian lb = new Librarian(FirstName, LastName, phoneNumber, salary);
        return lb;
    }

    //---------------
    public void addNewLibrarian() throws IOException {
        Librarian lb = newLibrarian();
        libraryService.getLibrary().addLibrarian(lb);

        write.WriteToCsv("src/resources/RW/librarian_data.csv", libraryService.getLibrary().getLibrarians());


    }

    //------------
    public void increaseTheSalary() throws IOException {
        float p;
        int id;
        Scanner scanner = new Scanner(System.in);
        System.out.println("The percentage - [0,1]");
        p = scanner.nextFloat();
        libraryService.getLibrary().increaseTheSalary(p);

        System.out.println("The changes have been made successfully");
        System.out.println(libraryService.getLibrary().getLibrarians());
        write.WriteToCsv("src/resources/RW/librarian_data.csv", libraryService.getLibrary().getLibrarians());

    }
    //-----------------
    public void librariansDescendingBySalary() {
        ArrayList<Librarian> librarians;
        librarians = libraryService.getLibrary().getLibrarians();

        Collections.sort(librarians);

        for (Librarian librarian : librarians) {
            System.out.println(librarian.getFirstName() + " " + librarian.getLastName() + " " + librarian.getSalary());
        }
    }
    //-------------
    public void averageSalary() {

        ArrayList<Librarian> librarians = libraryService.getLibrary().getLibrarians();

        float sum = 0f;
        for (Librarian librarian : librarians) {
            sum += librarian.getSalary();
        }

        float avg = sum / librarians.size();
        System.out.println("The average Salary: " + avg);

    }

}
