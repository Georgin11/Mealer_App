package com.example.Mealer_App.structure;

import static com.example.Mealer_App.structure.userType.CLIENT;

public class Client extends NonAdmin{

    private PaymentInfo paymentInfo;
    public userType typeOfUser;

    public Client(PaymentInfo paymentInfo, String fName, String lName, String email, Address address, String username, String password) {
        super(fName, lName, email, address, username, password);
        this.paymentInfo = paymentInfo;
        typeOfUser = CLIENT;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }
}
