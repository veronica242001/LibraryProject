package service;

import classes.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class ReadCsv {
    private static ReadCsv read = null;

    private ReadCsv() {
    }

    public static ReadCsv getInstance() {
        if (read == null) {
            read = new ReadCsv();
        }
        return read;
    }

    private static <E> ArrayList<E> forReader(BufferedReader input) throws IOException {
        String line;
        ArrayList<E> objects = new ArrayList<E>();
        line = input.readLine();
        while ((line = input.readLine()) != null) {
            String[] values = line.split(",");
            String firstName = values[0];
            String lastName = values[1];
            String phoneNumber = values[2];
            Reader reader = new Reader(firstName, lastName, phoneNumber);
            objects.add((E) reader);
        }
        return objects;
    }

    private static <E> ArrayList<E> forBookForRent(BufferedReader input) throws IOException {
        String line;
        ArrayList<E> objects = new ArrayList<E>();
       line = input.readLine();
        while ((line = input.readLine()) != null) {
            String[] values = line.split(",");
            String title = values[0];
            String firstName = values[1];
            String lastName = values[2];
            String originCountry = values[3];
            Author author = new Author(firstName, lastName, originCountry);
            String sectionName = values[4];
            Section section = Section.valueOf(sectionName.toUpperCase());
            String publisherName = values[5];
            String cityName = values[6];
            String country = values[7];
            City city = new City(cityName, country);
            Publisher publisher = new Publisher(publisherName, city);
            String status = values[8];
            BookForRent book = new BookForRent(title, author, section, publisher, status);

            objects.add((E) book);
        }

        return objects;

    }

    private static <E> ArrayList<E> forLibrarian(BufferedReader input) throws IOException {
        String line;
        ArrayList<E> objects = new ArrayList<E>();
        line = input.readLine();
        while ((line = input.readLine()) != null) {
            String[] values = line.split(",");
            String firstName = values[0];
            String lastName = values[1];
            String phoneNumber = values[2];
            float salary = Float.parseFloat(values[3]);
            Librarian librarian = new Librarian(firstName, lastName, phoneNumber, salary);
            objects.add((E) librarian);

        }

        return objects;

    }
    private static <E> ArrayList<E> forLibraryBook(BufferedReader input) throws IOException {
        String line;
        ArrayList<E> objects = new ArrayList<E>();
        line = input.readLine();
        while ((line = input.readLine()) != null) {
            String[] values = line.split(",");

            String title = values[0];
            String firstName = values[1];
            String lastName = values[2];
            String originCountry = values[3];
            Author author = new Author(firstName, lastName, originCountry);
            String sectionName = values[4];
            Section section = Section.valueOf(sectionName.toUpperCase());
            String publisherName = values[5];
            String cityName = values[6];
            String country = values[7];
            City city = new City(cityName, country);
            Publisher publisher = new Publisher(publisherName, city);

            LibraryBook book = new LibraryBook(title, author, section, publisher);

            objects.add((E) book);
        }
        return objects;

    }

    private static <E> ArrayList<E> forForm(BufferedReader input, Library library) throws IOException {
        String line;
        ArrayList<E> objects = new ArrayList<E>();
        line = input.readLine();
        while ((line = input.readLine()) != null) {
            String[] values = line.split(",");
            String title = values[0];

            BookForRent bk = library.getBookForRentByTitle(title);
            if (bk != null) {

                if (bk.getStatus().equals("available")) {
                    String firstNameLibrarian = values[1];
                    String lastNameLibrarian = values[2];

                    Librarian librarian = library.getLibrarianByName(firstNameLibrarian, lastNameLibrarian);

                    if (librarian != null) {
                        String firstNameReader = values[3];
                        String lastNameReader = values[4];
                        Reader reader = library.getReaderByName(firstNameReader, lastNameReader);
                        if (reader != null) {
                            String date = values[5];

                            LocalDate loanDate = LocalDate.parse(date);
                            LoanForm loanForm = new LoanForm(bk, reader, librarian, loanDate);
                            objects.add((E) loanForm);

                        } else {
                            System.out.println("This person isn't registered.");
                        }

                    } else {
                        System.out.println("This person isn't a librarian");
                    }

                } else {
                    System.out.println("Sorry, this book is unavailable");
                }

            } else {
                System.out.println("The book isn't in this library");

            }
        }

        return objects;

    }
    private static <E> ArrayList<E> forPublisher(BufferedReader input) throws IOException {
        String line;
        ArrayList<E> objects = new ArrayList<E>();
        line = input.readLine();
        while ((line = input.readLine()) != null) {
            String[] values = line.split(",");
            String publisherName = values[0];
            String cityName = values[1];
            String country = values[2];
            City city = new City(cityName, country);
            Publisher publisher = new Publisher(publisherName, city);
            objects.add((E) publisher);


        }

        return objects;

    }
    public static <E> ArrayList<E> readFromCsv(String type, String fileName, Library library) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        ArrayList<E> objects = new ArrayList<E>();
        switch (type.toLowerCase()) {
            case "reader" -> {
                objects = forReader(input);

            }
            case "librarian" -> {
                objects = forLibrarian(input);
            }
            case "bookforrent" -> {
                objects = forBookForRent(input);

            }
            case "librarybook" -> {
               objects = forLibraryBook(input);

            }
            case "form" -> {
                objects = forForm(input, library);

            }
            case "publisher" -> {
                objects = forPublisher(input);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type.toLowerCase());
        }

        return objects;
    }


}
