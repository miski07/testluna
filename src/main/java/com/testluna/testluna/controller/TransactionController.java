package com.testluna.testluna.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testluna.testluna.model.product;
import com.testluna.testluna.model.skeletons.cart;
import com.testluna.testluna.model.skeletons.report;
import com.testluna.testluna.model.skeletons.transactionDisplay;
import com.testluna.testluna.model.transactions.product_transaction;
import com.testluna.testluna.model.transactions.transaction;
import com.testluna.testluna.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/purchase/all")
    public List<transactionDisplay> getAllTransactions() {
        List<transactionDisplay> displays = new ArrayList<>();
        List<transaction> transactions = transactionService.getAllTransactions();
        for(transaction x: transactions){
            transactionDisplay display = new transactionDisplay();
            display.transaction = x;
            display.totalBeforeTax =0;
            double totalTax = 0;
            for(product_transaction p: x.itemLines){
                display.totalBeforeTax+= (p.product.price* p.qty);
                totalTax += ((p.product.price* p.product.tax.rate)*p.qty);
            }
            display.total= totalTax+ display.totalBeforeTax;
            displays.add(display);
        }
        return displays;
    }

    

    @GetMapping("/purchase/{id}")
    public transactionDisplay getPurchase(@PathVariable String id, @RequestHeader String token) {
        return getTransactionById(id,1, token);
    }

    @GetMapping("/sales/{id}")
    public transactionDisplay getSales(@PathVariable String id, @RequestHeader String token) {
        return getTransactionById(id,2, token);
    }
    
    @GetMapping("/report/{year}/{month}")
    public report getReport(@PathVariable int year, @PathVariable int month, @RequestHeader String token) {
        report report = new report();
        String email = token;
        List<transaction> transactions = transactionService.getTransactionByUser(email);
        report.transactions = transactions;
        report.totalIncome=0;
        report.totalOutcome=0;
        List<transactionDisplay> displayPurchase = new ArrayList<>();
        List<transactionDisplay> displaySale = new ArrayList<>();
        for(transaction t:transactions){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(t.invoicedate);
            if(calendar.get(Calendar.YEAR)==year && (calendar.get(Calendar.MONTH)+1)==month){
                
                if(t.status==1){
                    displayPurchase.add(getTransactionById(t.invoiceno, 1, email));
                    System.err.println(t.invoiceno);
                }
                else if(t.status==2){
                    displaySale.add(getTransactionById(t.invoiceno, 2, email));
                    System.err.println(t.invoiceno);
                }
            }
        }
        for(transactionDisplay d: displaySale){
            report.totalIncome += d.total;
        }
        for(transactionDisplay d: displayPurchase){
            report.totalOutcome+=d.total;
        }
        

        return report;
    }
    

    public transactionDisplay getTransactionById(String id, int type, String email){        
        transactionDisplay display = new transactionDisplay();
        transaction transaction = transactionService.getTransaction(id);
        if(!transaction.email.equals(email)){
            return display;
        }
        if(transaction.status != type){
            return display;
        }
        display.transaction= transaction;
        display.totalBeforeTax=0;
        double totalTax=0;
        for(product_transaction items: transaction.itemLines){
            display.totalBeforeTax+= (items.product.price*items.qty);
            totalTax +=((items.product.tax.rate*items.product.price)*items.qty);
        }
        display.status = (transaction.status==1)? "Purchased": (transaction.status==2)? "Sold": (transaction.status==0)? "Voided": "Invalid";
        display.total = display.totalBeforeTax+totalTax;
        return display;
    }
    
    @PostMapping("/purchase/")
    public transaction createTransactionPurchase(@RequestBody cart cart) {
        return createTransaction(cart, 1);
    }
    public transaction createTransaction(cart cart, int type){
        
        if(cart.invoiceNo==null || cart.invoiceNo=="string"){
            cart.invoiceNo= "T-"+transactionService.getLatestDefaultTransactionID();
        }
        return transactionService.createTransaction(cart,type);
    }
    @PostMapping("/sales/")
    public transaction createTransactionSales(@RequestBody cart cart) {
        
        return createTransaction(cart, 2);
    }

    
    
}