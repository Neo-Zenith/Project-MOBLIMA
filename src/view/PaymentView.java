package view;

import model.Payment;
import controller.PaymentManager;
import handler.InputHandler;
import view.MainView;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

public class PaymentView {

    private Payment payment;
    private String cardNumber;
    private int CCV;
    private int OTP;
    private int bankAccountNumber;
    
    public PaymentView(Payment payment){
        this.payment = payment;
    }

    public Payment getPayment(){
        return this.payment;
    }

    public void setPayment(Payment payment){
        this.payment = payment;
    }

    public String generateTransactionId(String cinemaCode){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
        LocalDateTime now = LocalDateTime.now(); 
        String transactionId = cinemaCode + dtf.format(now);
        return transactionId;
    }

    public Payment createPayment(String cinemaCode, double movieTicketPrice){

        // generate Transaction ID at the moment a new Payment is created.
        // if the entered int or String is not valid, the user is prompted to re-enter again until the system receive a valid value
        String transactionID;

        System.out.println("Payment:");
        MainView.printBoilerPlate("""
                1.  Card Payment
                2.  QRCode
                3.  Bank Transaction
                """);
        System.out.println("Please Select A Payment Method:");

        int choice = InputHandler.intHandler();
        switch(choice){
            case 1:
                System.out.println("--- Card Payment ---");
                transactionID = generateTransactionId(cinemaCode);
                this.payment = PaymentManager.createCardPayment(transactionID, movieTicketPrice);

                System.out.println("Enter Card Number:");
                cardNumber = InputHandler.stringHandler();
                while(cardNumber == null){
                    System.out.println("Please Re-Enter Card Number:");
                    cardNumber = InputHandler.stringHandler();
                }

                System.out.println("Enter CCV:");
                CCV = InputHandler.intHandler();
                while(CCV == -1){
                    System.out.println("Please Re-Enter CCV:");
                    CCV = InputHandler.intHandler();
                }

                printPaymentSuccessful();
                printReceipt("Card Payment");
                break;

            case 2:
                System.out.println("--- QRCode Payment---");
                transactionID = generateTransactionId(cinemaCode);
                this.payment = PaymentManager.createQRCodePayment(transactionID, movieTicketPrice);

                System.out.println("Please Scan the QRCode:");

                System.out.println("Enter OTP Received:");
                OTP = InputHandler.intHandler();
                while(OTP == -1){
                    System.out.println("Please Re-Enter OTP Received:");
                    OTP = InputHandler.intHandler();
                }

                printPaymentSuccessful();
                printReceipt("QRCode");
                break;

            case 3:
                System.out.println("--- Bank Transaction Payment ---");
                transactionID = generateTransactionId(cinemaCode);
                this.payment = PaymentManager.createBankTransactioPayment(transactionID, movieTicketPrice);

                System.out.println("Enter Bank Account Number:");
                bankAccountNumber = InputHandler.intHandler();
                while(bankAccountNumber == -1){
                    System.out.println("Please Re-Enter Bank Account Number:");
                    bankAccountNumber = InputHandler.intHandler();
                }

                printPaymentSuccessful();
                printReceipt("Bank Transaction");
                break;
        }

        return this.payment;
    }

    public void printPaymentSuccessful(){
        System.out.println("");
        System.out.println("Payment Processing...");
        System.out.println("");
        System.out.println("Congratulations! Payment Successful!");
        System.out.println("Here is your receipt:");
        System.out.println("");
    }

    public void printReceipt(String paymentMethod){
        // can add more criteria to be printed
        System.out.println("===================================");
        System.out.println("              MOBLIMA              ");
        System.out.println("");
        System.out.println("              RECEIPT              ");
        System.out.println("___________________________________");
        System.out.println("Payment ID      :     " + this.payment.getUUID());
        System.out.println("Transaction ID  :     " + this.payment.getTransactionID());
        System.out.println("Payment Method  :     " + paymentMethod);
        System.out.println("Total Amount ($):     " + this.payment.getMovieTicketPrice());
        System.out.println("___________________________________");
        System.out.println("");
        System.out.println("            THANK YOU!             ");
        System.out.println("          See you again!           ");
        System.out.println("===================================");
    }

}
