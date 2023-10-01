package net.myphenotype.jeeves.drools.repository;

import net.myphenotype.jeeves.drools.domain.Invoice;
import net.myphenotype.jeeves.drools.domain.PurchaseOrder;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {

    public static final String PEPSI_INVOICE_NUMBER = "US-PEPSI-1";
    public static final String TATAS_INVOICE_NUMBER = "IN-TATAS-2";
    public static final String APPLE_INVOICE_NUMBER = "US-APPLE-3";
    public static final String TESLA_INVOICE_NUMBER = "US-TESLA-4";
    public static final String CISCO_INVOICE_NUMBER = "US-CISCO-5";

    public static List<Invoice> getInvoices(){

        List<Invoice> invoices = new ArrayList<>();

        invoices.add(Invoice.newBuilder()
                .withInvoiceNumber(PEPSI_INVOICE_NUMBER)
                .withSupplierName("PepsiCo")
                .withDueDate(LocalDate.of(2023, Month.DECEMBER, 17))
                .withInvoiceAmount(117000)
                .withAgeInDays(50)
                .build());

        invoices.add(Invoice.newBuilder()
                .withInvoiceNumber(TATAS_INVOICE_NUMBER)
                .withSupplierName("Tata Sons Ltd.")
                .withDueDate(LocalDate.of(2023, Month.JUNE, 18))
                .withInvoiceAmount(11000)
                .withAgeInDays(31)
                .build());

        invoices.add(Invoice.newBuilder()
                .withInvoiceNumber(APPLE_INVOICE_NUMBER)
                .withSupplierName("Apple Inc.")
                .withDueDate(LocalDate.of(2023, Month.SEPTEMBER, 13))
                .withInvoiceAmount(247000)
                .withAgeInDays(15)
                .build());

        invoices.add(Invoice.newBuilder()
                .withInvoiceNumber(TESLA_INVOICE_NUMBER)
                .withSupplierName("Tesla, Inc.")
                .withDueDate(LocalDate.of(2023, Month.SEPTEMBER, 30))
                .withInvoiceAmount(510000)
                .withAgeInDays(16)
                .build());

        invoices.add(Invoice.newBuilder()
                .withInvoiceNumber(CISCO_INVOICE_NUMBER)
                .withSupplierName("Cisco Systems Inc.")
                .withDueDate(LocalDate.of(2023, Month.OCTOBER, 23))
                .withInvoiceAmount(0)
                .withAgeInDays(21)
                .build());

        return invoices;
    }

    public static List<PurchaseOrder> getPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        purchaseOrders.add(PurchaseOrder.newBuilder()
                .withPoId(1)
                .withPassportNumber(PEPSI_INVOICE_NUMBER)
                .withPurchaseStartDate(LocalDate.of(2023, Month.NOVEMBER, 27))
                .withPurchaseOrderDate(LocalDate.of(2024, Month.JANUARY, 4))
                .build());

        purchaseOrders.add(PurchaseOrder.newBuilder()
                .withPoId(2)
                .withPassportNumber(TATAS_INVOICE_NUMBER)
                .withPurchaseStartDate(LocalDate.of(2022, Month.OCTOBER, 27))
                .withPurchaseOrderDate(LocalDate.of(2022, Month.DECEMBER, 4))
                .build());

        purchaseOrders.add(PurchaseOrder.newBuilder()
                .withPoId(3)
                .withPassportNumber(TESLA_INVOICE_NUMBER)
                .withPurchaseStartDate(LocalDate.of(2022, Month.JANUARY, 1))
                .withPurchaseOrderDate(LocalDate.of(2022, Month.DECEMBER, 31))
                .build());

        purchaseOrders.add(PurchaseOrder.newBuilder()
                .withPoId(4)
                .withPassportNumber(APPLE_INVOICE_NUMBER)
                .withPurchaseStartDate(LocalDate.of(2022, Month.JANUARY, 1))
                .withPurchaseOrderDate(LocalDate.of(2023, Month.SEPTEMBER, 23))
                .build());

        purchaseOrders.add(PurchaseOrder.newBuilder()
                .withPoId(5)
                .withPassportNumber(CISCO_INVOICE_NUMBER)
                .withPurchaseStartDate(LocalDate.of(2022, Month.JANUARY, 1))
                .withPurchaseOrderDate(LocalDate.of(2022, Month.DECEMBER, 10))
                .build());

        return purchaseOrders;
    }
}
