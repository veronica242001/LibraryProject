package service;

import models.*;
import models.Reader;

import java.io.*;
import java.util.List;

public class WriteCsv {
    private static WriteCsv write = null;

    private WriteCsv() {
    }

    ;

    public static WriteCsv getInstance() {
        if (write == null) {
            write = new WriteCsv();
        }
        return write;
    }

    private static <E> void writeReaders(BufferedWriter output, List<E> objects) throws IOException {
        output.write("FirstName,LastName,PhoneNumber");
        output.newLine();
        for (E obj : objects) {

            Reader r = (Reader) obj;
            output.write(r.getFirstName());
            output.write(",");
            output.write(r.getLastName());
            output.write(",");
            output.write(r.getPhoneNumber());
            output.newLine();
            output.flush();
        }

    }

    private static <E> void writeLibrarians(BufferedWriter output, List<E> objects) throws IOException {
        output.write("FirstName,LastName,PhoneNumber,Salary");
        output.newLine();
        for (E obj : objects) {
            Librarian l = (Librarian) obj;
            output.write(l.getFirstName());
            output.write(",");
            output.write(l.getLastName());
            output.write(",");
            output.write(l.getPhoneNumber());
            output.write(",");
            output.write(Float.toString(l.getSalary()));
            output.newLine();
            output.flush();
        }


    }

    private static <E> void writeBookForRent(BufferedWriter output, List<E> objects) throws IOException {
        output.write("Title,FirstNameAuthor,LastNameAuthor,OriginCountry,Section,PublisherName,City,Country,Status");
        output.newLine();
        for (E obj : objects) {
            BookForRent bk = (BookForRent) obj;
            output.write(bk.getTitle());
            output.write(",");
            Author author = bk.getAuthor();
            output.write(author.getFirstName());
            output.write(",");
            output.write(author.getLastName());
            output.write(",");
            output.write(author.getOriginCountry());
            output.write(",");
            output.write(bk.getSection().toString());
            output.write(",");
            Publisher publisher = bk.getPublisher();
            output.write(publisher.getName());
            output.write(",");
            output.write(publisher.getCity().getName());
            output.write(",");
            output.write(publisher.getCity().getCountry());
            output.write(",");
            output.write(bk.getStatus());
            output.newLine();
            output.flush();
        }
    }

    private static <E> void writeLibraryBook(BufferedWriter output, List<E> objects) throws IOException {

        output.write("Title,FirstNameAuthor,LastNameAuthor,OriginCountry,Section,PublisherName,City,Country");
        output.newLine();
        for (E obj : objects) {
            LibraryBook bk = (LibraryBook) obj;
            output.write(bk.getTitle());
            output.write(",");
            Author author = bk.getAuthor();
            output.write(author.getFirstName());
            output.write(",");
            output.write(author.getLastName());
            output.write(",");
            output.write(author.getOriginCountry());
            output.write(",");
            output.write(bk.getSection().toString());
            output.write(",");
            Publisher publisher = bk.getPublisher();
            output.write(publisher.getName());
            output.write(",");
            output.write(publisher.getCity().getName());
            output.write(",");
            output.write(publisher.getCity().getCountry());

            output.newLine();
            output.flush();
        }

    }

    private static <E> void writeForms(BufferedWriter output, List<E> objects) throws IOException {
        output.write("Title,FirstNameLibrarian,LastNameLibrarian,FirstNameReader,LastNameReader,Date");
        output.newLine();
        for (E obj : objects) {
            LoanForm form = (LoanForm) obj;
            output.write(form.getBookForRent().getTitle());
            output.write(",");
            output.write(form.getLibrarian().getFirstName());
            output.write(",");
            output.write(form.getLibrarian().getLastName());
            output.write(",");
            output.write(form.getReader().getFirstName());
            output.write(",");
            output.write(form.getReader().getLastName());
            output.write(",");
            output.write(form.getLoanDate().toString());
            output.newLine();
            output.flush();

        }
    }

    private static <E> void writePublishers(BufferedWriter output, List<E> objects) throws IOException {
        output.write("Name,CityName,Country");
        output.newLine();
        for (E obj : objects) {
            Publisher publisher = (Publisher) obj;
            output.write(publisher.getName());
            output.write(",");
            output.write(publisher.getCity().getName());
            output.write(",");
            output.write(publisher.getCity().getCountry());
            output.newLine();
            output.flush();
        }
    }

    public static <E> void WriteToCsv(String fileName, List<E> objects) throws IOException {
        File f = new File(fileName);

        BufferedWriter output = new BufferedWriter(new FileWriter(f));
        switch (objects.get(0).getClass().getSimpleName().toLowerCase()) {
            case "reader" -> {
                writeReaders(output, objects);
            }
            case "librarian" -> {
                writeLibrarians(output, objects);
            }


            case "bookforrent" -> {
                writeBookForRent(output, objects);
            }
            case "librarybook" -> {
                writeLibraryBook(output, objects);
            }
            case "loanform" -> {
                writeForms(output, objects);
            }
            case "publisher" -> {
                writePublishers(output, objects);
            }
        }
    }
}
