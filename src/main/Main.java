package main;

import models.*;
import service.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;


public class Main {

    public static LibraryService libraryService;
    public static LibrarianService librarianService;
    public  static ReaderService readerService;
    public static PublisherService publisherService;

    public static LibraryService InfoFromCsv() throws IOException {
        City city1 = new City("Targu-Jiu", "Romania");

        Library library = new Library("Christian Tell", city1);
        ReadCsv read = ReadCsv.getInstance();

        ArrayList<Reader> readers;
        readers = read.readFromCsv("reader", "src/resources/RW/reader_data.csv", library);
        for (Reader r : readers) {
            library.addReader(r);
        }
        ArrayList<Librarian> librarians;
        librarians = read.readFromCsv("librarian", "src/resources/RW/librarian_data.csv", library);
        for (Librarian l : librarians) {
            library.addLibrarian(l);
        }

        ArrayList<BookForRent> booksForRent;
        booksForRent = read.readFromCsv("bookforrent", "src/resources/RW/bookForRent_data.csv", library);
        for (BookForRent bk : booksForRent) {
            library.addBookForRent(bk);
        }


        ArrayList<LibraryBook> libraryBooks;
        libraryBooks = read.readFromCsv("librarybook", "src/resources/RW/libraryBook_data.csv", library);
        for (LibraryBook b : libraryBooks) {
            library.addLibraryBook(b);
        }
        LibraryService libraryService = LibraryService.getInstance();

        libraryService.updateInfo(library);

        ArrayList<LoanForm> forms;
        forms = read.readFromCsv("form", "src/resources/RW/forms_data.csv", library);
        for (LoanForm f : forms) {
            libraryService.addForm(f);
        }
        ArrayList<Publisher> publishers;
        publishers = read.readFromCsv("publisher", "src/resources/RW/publisher_data.csv", library);
        for (Publisher p : publishers) {
            publisherService.addPublisher(p);
        }
        return libraryService;

    }

    public static LibraryService Info() throws IOException {
        City city1 = new City("Targu-Jiu", "Romania");
        City city2 = new City("Bucharest", "Romania");
        Publisher publisher1 = new Publisher("Humanitas", city2);
        Publisher publisher2 = new Publisher("Litera", city1);
        Publisher publisher3 = new Publisher("Arthur", city2);
        Library library = new Library("Christian Tell", city1);

        Author author1 = new Author("Eric-Emmanuel", "Schmitt", "France");
        Author author2 = new Author("Alex", "Michaelides", "Cyprus");
        Author author3 = new Author("Agatha", "Christie", "England");
        Author author4 = new Author("Stephen", "Hawking", "England");

        BookForRent book1 = new BookForRent("Noah's Child", author1, Section.PHILOSOPHY, publisher1, "available");
        BookForRent book2 = new BookForRent("Monsieur Ibrahim and the Flowers of the Koran", author1, Section.PHILOSOPHY, publisher1, "available");
        BookForRent book3 = new BookForRent("The Alternative Hypothesis", author1, Section.FICTION, publisher1, "available");
        BookForRent book4 = new BookForRent("The Silent Patient", author2, Section.PSYCHOLOGY, publisher2, "available");
        BookForRent book5 = new BookForRent("Murder on the Orient Express", author3, Section.CRIME, publisher2, "unavailable");
        BookForRent book9 = new BookForRent("Death on the Nile", author3, Section.CRIME, publisher2, "available");

        LibraryBook book6 = new LibraryBook("Brief History Of Time", author4, Section.SCIENCE, publisher3);
        LibraryBook book7 = new LibraryBook("Black Holes and Baby Universes and Other Essays", author4, Section.SCIENCE, publisher3);
        LibraryBook book8 = new LibraryBook("The Universe in a Nutshell", author4, Section.SCIENCE, publisher3);


        library.addBookForRent(book1);
        library.addBookForRent(book2);
        library.addBookForRent(book3);
        library.addBookForRent(book4);
        library.addBookForRent(book5);
        library.addBookForRent(book9);
        library.addLibraryBook(book6);
        library.addLibraryBook(book7);
        library.addLibraryBook(book8);


        Librarian librarian1 = new Librarian("Alexandru", "Pop", "0738989790", 3000);
        Librarian librarian2 = new Librarian("Amalia", "Dumitrescu", "07333567", 3250);
        Librarian librarian3 = new Librarian("Ioana", "Dumitrascu", "0745678734", 2890);
        library.addLibrarian(librarian1);
        library.addLibrarian(librarian2);
        library.addLibrarian(librarian3);

        Reader reader1 = new Reader("Vladimir", "Ion", "0734567868");
        Reader reader2 = new Reader("Athena", "Apostolescu", "0789995678");
        Reader reader3 = new Reader("Ioana", "Popescu", "0789775679");
        Reader reader4 = new Reader("Noora", "Anastasescu", "0727995876");
        Reader reader5 = new Reader("Mircea", "Popa", "0784567890");

        library.addReader(reader1);
        library.addReader(reader2);
        library.addReader(reader3);
        library.addReader(reader4);
        library.addReader(reader5);

        LibraryService libraryService = LibraryService.getInstance();
        libraryService.updateInfo(library);
        LoanForm lf1 = new LoanForm(book1, reader1, librarian1, LocalDate.parse("2021-01-24"));
        LoanForm lf2 = new LoanForm(book2, reader1, librarian2, LocalDate.parse("2021-01-24"));
        lf2.setDueDate(LocalDate.parse("2021-02-24"));
        LoanForm lf3 = new LoanForm(book5, reader2, librarian3, LocalDate.parse("2021-03-24"));
        libraryService.addForm(lf1);
        libraryService.addForm(lf2);
        libraryService.addForm(lf3);


        return libraryService;
    }


    private static void menuLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int input;
        AuditWriteCsv write = AuditWriteCsv.getInstance();
        while (true) {
            System.out.println("--Main Menu--");
            System.out.println("Options:");
            System.out.println("1-Add new reader");
            System.out.println("2-Add new publisher");
            System.out.println("3-Add LibraryBook");
            System.out.println("4- See all publishers");
            System.out.println("5-See all Books");
            System.out.println("6-See all readers");
            System.out.println("7-See BooksForRent by section");
            System.out.println("8-See sections by a publisher");
            System.out.println("9-Borrow a book");
            System.out.println("10-Remove books by author");
            System.out.println("11- Increases the salary of librarians");
            System.out.println("12-Calculate the average salary");
            System.out.println("13-Librarians descending by salary");
            System.out.println("14- See all forms");
            System.out.println("15-Add new BookForRent");
            System.out.println("16-Add new Librarian");
            System.out.println("0-Exit");

            input = scanner.nextInt();
            Timestamp timestamp = new Timestamp(currentTimeMillis());
            if (input == 0) {
                break;
            } else if (input == 1) {
                readerService.addNewReader();
                write.writeAudit("Add new reader", timestamp.toString());
            } else if (input == 2) {
               publisherService.addNewPublisher();
                write.writeAudit("Add new publisher", timestamp.toString());

            } else if (input == 3) {
                libraryService.addLibraryBook();
                write.writeAudit("Add LibraryBook", timestamp.toString());
            } else if (input == 4) {
                publisherService.allPublishers();
                write.writeAudit("See all publishers", timestamp.toString());
            } else if (input == 5) {
                libraryService.allBooks();
                write.writeAudit("See all books", timestamp.toString());
            } else if (input == 6) {
                readerService.allReaders();
                write.writeAudit("See all readers", timestamp.toString());
            } else if (input == 7) {
                libraryService.getBooksForRentBySection();
                write.writeAudit("See BooksForRent by section", timestamp.toString());
            } else if (input == 8) {
                publisherService.sectionsByPublisher(libraryService);
                write.writeAudit("See sections by a publisher", timestamp.toString());

            } else if (input == 9) {
                libraryService.borrowBook();
                write.writeAudit("Borrow a book", timestamp.toString());
            } else if (input == 10) {
                libraryService.removeBooksByAuthor();
                write.writeAudit("Remove books by author", timestamp.toString());
            } else if (input == 11) {
                librarianService.increaseTheSalary();
                write.writeAudit("Increases the salary of librarians", timestamp.toString());
            } else if (input == 12) {
                librarianService.averageSalary();
                write.writeAudit("Calculate the average salary", timestamp.toString());
            } else if (input == 13) {
               librarianService.librariansDescendingBySalary();
                write.writeAudit("Librarians descending by salary", timestamp.toString());
            } else if (input == 14) {
                libraryService.allForms();
                write.writeAudit("See all forms", timestamp.toString());
            } else if (input == 15) {
                libraryService.addBookForRent();
                write.writeAudit("Add new BookForRent", timestamp.toString());
            } else if (input == 16) {
                librarianService.addNewLibrarian();
                write.writeAudit("Add new Librarian", timestamp.toString());
            }

        }


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
          //create services
          librarianService = LibrarianService.getInstance();
          readerService = ReaderService.getInstance();
          publisherService = PublisherService.getInstance();

          //libraryService = Info();
          libraryService = InfoFromCsv();
          menuLoop();

    }

}
