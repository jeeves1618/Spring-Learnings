/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package net.myphenotype.jeeves.drools.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
  private String invoiceNumber;
  private String supplierName;
  private LocalDate expiresOn;
  private int invoiceAmount;
  private int age;

  private Validation validation = Validation.UNKNOWN;

  private String cause = "";

  private Invoice() {
  }

  public LocalDate getExpiresOn() {
    return expiresOn;
  }

  public void setExpiresOn(LocalDate expiresOn) {
    this.expiresOn = expiresOn;
  }

  public boolean isExpired() {
    return expiresOn.isBefore(LocalDate.now());
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public int getInvoiceAmount() {
    return invoiceAmount;
  }

  public Validation getValidation() {
    return validation;
  }

  public void setValidation(Validation validation) {
    this.validation = validation;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  @Override
  public String toString() {
    return String.format("Passport[no:%s, supplierName:%s]", invoiceNumber, supplierName);
  }

  public static PassportBuilder newBuilder() {
    return new PassportBuilder();
  }

   public static final class PassportBuilder {
    private String invoiceNumber;
    private String supplierName;
    private LocalDate expiresOn;
    private int invoiceAmount;
    private int age;

    private PassportBuilder() {
    }

    public PassportBuilder withInvoiceNumber(String invoiceNumber) {
      this.invoiceNumber = invoiceNumber;
      return this;
    }

    public PassportBuilder withSupplierName(String supplierName) {
      this.supplierName = supplierName;
      return this;
    }

    public PassportBuilder withExpiresOn(LocalDate expiresOn) {
      this.expiresOn = expiresOn;
      return this;
    }

    public PassportBuilder withInvoiceAmount(int invoiceAmount) {
      this.invoiceAmount = invoiceAmount;
      return this;
    }

    public PassportBuilder withAge(int age) {
      this.age = age;
      return this;
    }

    public Invoice build() {
      Invoice passport = new Invoice();
      passport.invoiceNumber = invoiceNumber;
      passport.supplierName = supplierName;
      passport.expiresOn = expiresOn;
      passport.invoiceAmount = invoiceAmount;
      passport.age = age;
      return passport;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Invoice)) return false;
    Invoice passport = (Invoice) o;
    return invoiceAmount == passport.invoiceAmount &&
        age == passport.age &&
        Objects.equals(invoiceNumber, passport.invoiceNumber) &&
        Objects.equals(supplierName, passport.supplierName) &&
        Objects.equals(expiresOn, passport.expiresOn) &&
        validation == passport.validation &&
        Objects.equals(cause, passport.cause);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoiceNumber, supplierName, expiresOn, invoiceAmount, age, validation, cause);
  }

}
