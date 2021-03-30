package main;

import classes.*;
import service.LibraryService;

import java.time.LocalDate;
import java.util.Scanner;


public class Main {

    public static LibraryService libraryService;

    public static LibraryService Info() {
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
        BookForRent book9 = new BookForRent("Death on the Nile", author3, Section.CRIME,publisher2,"available");

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

         LibraryService libraryService = new LibraryService(library);
         LoanForm lf1 = new LoanForm(book1,reader1,librarian1, LocalDate.parse("2021-01-24"));
         LoanForm lf2 = new LoanForm(book2,reader1,librarian2, LocalDate.parse("2021-01-24"));
         lf2.setDueDate(LocalDate.parse("2021-02-24"));
         LoanForm lf3  = new LoanForm(book5,reader2,librarian3,LocalDate.parse("2021-03-24"));
         libraryService.addForm(lf1);
         libraryService.addForm(lf2);
         libraryService.addForm(lf3);


         return libraryService;
    }


    private static void menuLoop() {
        Scanner scanner = new Scanner(System.in);
        int input;
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
            System.out.println("0-Exit");

            input = scanner.nextInt();
            if ( input == 0) {
                break;
            }
            else if ( input == 1) {
                libraryService.addNewReader();
            }
            else if ( input == 2) {
                libraryService.addNewPublisher();
            }
            else if ( input == 3) {
                libraryService.addLibraryBook();
            }
            else if ( input == 4) {
                libraryService.allPublishers();
            }
            else if ( input == 5) {
                libraryService.allBooks();
            }
            else if ( input == 6){
                libraryService.allReaders();
            }
            else if ( input == 7) {
                libraryService.getBooksForRentBySection();
            }
            else if ( input == 8) {
                libraryService.sectionsByPublisher();
            }
            else if( input == 9){
                libraryService.borrowBook();
            }
            else if ( input == 10) {
                libraryService.removeBooksByAuthor();
            }
            else if ( input == 11) {
                libraryService.increaseTheSalary();
            }
            else if ( input == 12) {
                libraryService.averageSalary();
            }
            else if ( input == 13) {
                 libraryService.librariansDescendingBySalary();
            }
            else if( input == 14){
                libraryService.allForms();
            }

        }


    }

    public static void main(String[] args) {

        libraryService = Info();
        menuLoop();
    }

}
