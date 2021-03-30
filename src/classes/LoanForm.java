package classes;

import java.time.LocalDate;
import java.util.Date;

public class LoanForm {

    private BookForRent bookForRent;
    private Reader reader;
    private Librarian librarian;
    private LocalDate loanDate;
    private LocalDate dueDate;

    public LoanForm(BookForRent bookForRent, Reader reader, Librarian librarian, LocalDate loanDate) {
        this.bookForRent = bookForRent;
        this.reader = reader;
        this.librarian = librarian;
        this.loanDate = loanDate;
        this.dueDate = null;
    }

    public BookForRent getBookForRent() {
        return bookForRent;
    }

    public void setBookForRent(BookForRent bookForRent) {
        this.bookForRent = bookForRent;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "LoanForm:" + "bookTitle = \'"+ bookForRent.getTitle() +"\',  " + bookForRent.getAuthor()+
                ", reader = \'" + reader.getFirstName() +" " + reader.getLastName()+
                "\' , librarian = \'" + librarian.getFirstName() + " " + reader.getLastName()+
                "\' , loanDate = " + loanDate +
                ", dueDate=" + dueDate;
    }
}
