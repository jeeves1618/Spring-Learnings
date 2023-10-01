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
  private String PoNumber;
  private String supplierName;
  private LocalDate dueDate;
  private int invoiceAmount;
  private int ageInDays;

  private Validation validation = Validation.UNKNOWN;

  private String cause = "";

  private Invoice() {
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public boolean isExpired() {
    return dueDate.isAfter(LocalDate.now());
  }

  public String getPoNumber() {
    return PoNumber;
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

  public int getAgeInDays() {
    return ageInDays;
  }

  public void setAgeInDays(int ageInDays) {
    this.ageInDays = ageInDays;
  }

  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  @Override
  public String toString() {
    return String.format(" no:%s, from the supplier :%s for the amount %d, with a due date of %s", PoNumber, supplierName, invoiceAmount, dueDate.toString());
  }

  public static PassportBuilder newBuilder() {
    return new PassportBuilder();
  }

   public static final class PassportBuilder {
    private String invoiceNumber;
    private String supplierName;
    private LocalDate dueDate;
    private int invoiceAmount;
    private int ageInDays;

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

    public PassportBuilder withDueDate(LocalDate dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public PassportBuilder withInvoiceAmount(int invoiceAmount) {
      this.invoiceAmount = invoiceAmount;
      return this;
    }

    public PassportBuilder withAgeInDays(int ageInDays) {
      this.ageInDays = ageInDays;
      return this;
    }

    public Invoice build() {
      Invoice passport = new Invoice();
      passport.PoNumber = invoiceNumber;
      passport.supplierName = supplierName;
      passport.dueDate = dueDate;
      passport.invoiceAmount = invoiceAmount;
      passport.ageInDays = ageInDays;
      return passport;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Invoice)) return false;
    Invoice passport = (Invoice) o;
    return invoiceAmount == passport.invoiceAmount &&
            ageInDays == passport.ageInDays &&
        Objects.equals(PoNumber, passport.PoNumber) &&
        Objects.equals(supplierName, passport.supplierName) &&
        Objects.equals(dueDate, passport.dueDate) &&
        validation == passport.validation &&
        Objects.equals(cause, passport.cause);
  }

  @Override
  public int hashCode() {
    return Objects.hash(PoNumber, supplierName, dueDate, invoiceAmount, ageInDays, validation, cause);
  }

}
