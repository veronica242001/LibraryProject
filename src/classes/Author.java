package classes;

import java.util.ArrayList;
import java.util.Objects;

public class Author extends Person{
       private String originCountry;

       public Author( String firstName, String lastName, String originCountry) {
              super(firstName, lastName);
              this.originCountry = originCountry;
       }

       public String getOriginCountry() {
              return originCountry;
       }

       public void setOriginCountry(String originCountry) {
              this.originCountry = originCountry;
       }

       @Override
       public String toString() {
              return "Author: " + super.toString()+
                      ", originCountry= '" + originCountry + '\'';
       }

       @Override
       public boolean equals(Object o) {
              if (this == o) return true;
              if (o == null || getClass() != o.getClass()) return false;
              Author author = (Author) o;
              return  super.equals(o) && Objects.equals(originCountry, author.originCountry);
       }

       @Override
       public int hashCode() {
              return Objects.hash(originCountry);
       }
}
