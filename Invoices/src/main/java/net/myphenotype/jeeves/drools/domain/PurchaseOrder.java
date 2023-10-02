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

public class PurchaseOrder {
  private int poId;
  private String poNumber;
  private LocalDate purchaseStartDate;
  private LocalDate purchaseOrderDate;

  private Validation validation = Validation.UNKNOWN;

  private Outcome outcome = Outcome.UNMATCHED;

  public String getPoNumber() {
    return poNumber;
  }

  public LocalDate getPurchaseStartDate() {
    return purchaseStartDate;
  }

  public LocalDate getPurchaseOrderDate() {
    return purchaseOrderDate;
  }

  public Validation getValidation() {
    return validation;
  }

  public void setValidation(Validation validation) {
    this.validation = validation;
  }

  public Outcome getOutcome() {
    return outcome;
  }

  public void setOutcome(Outcome outcome) {
    this.outcome = outcome;
  }

  @Override
  public String toString() {
    if (outcome == Outcome.MATCHED)
      return "Purchase Order with ID #" + poId + ", matching the invoice with Purchase Order Number:" + poNumber + ".";
    else
      return "Purchase Order with ID #" + poId + " and Purchase Order Number " + poNumber + ", does not have a matching invoice";
  }

  public static PurchaseOrderBuilder newBuilder() {
    return new PurchaseOrderBuilder();
  }

  public static final class PurchaseOrderBuilder {
    private int poId;
    private String passportNumber;
    private LocalDate purchaseStartDate;
    private LocalDate purchaseEndDate;

    private PurchaseOrderBuilder() {
    }

    public PurchaseOrderBuilder withPoId(int poId) {
      this.poId = poId;
      return this;
    }

    public PurchaseOrderBuilder withPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
      return this;
    }

    public PurchaseOrderBuilder withPurchaseStartDate(LocalDate purchaseStartDate) {
      this.purchaseStartDate = purchaseStartDate;
      return this;
    }

    public PurchaseOrderBuilder withPurchaseOrderDate(LocalDate purchaseEndDate) {
      this.purchaseEndDate = purchaseEndDate;
      return this;
    }

    public PurchaseOrder build() {
      PurchaseOrder purchaseOrder = new PurchaseOrder();
      purchaseOrder.poId = poId;
      purchaseOrder.poNumber = passportNumber;
      purchaseOrder.purchaseStartDate = purchaseStartDate;
      purchaseOrder.purchaseOrderDate = purchaseEndDate;
      return purchaseOrder;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PurchaseOrder)) return false;
    PurchaseOrder that = (PurchaseOrder) o;
    return poId == that.poId &&
        Objects.equals(poNumber, that.poNumber) &&
        Objects.equals(purchaseStartDate, that.purchaseStartDate) &&
        Objects.equals(purchaseOrderDate, that.purchaseOrderDate) &&
        validation == that.validation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(poId, poNumber, purchaseStartDate, purchaseOrderDate, validation);
  }
}
