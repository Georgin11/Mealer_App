package com.example.Mealer_App.structure;

import static com.example.Mealer_App.structure.UserType.CLIENT;

public class Client extends NonAdmin{

    private PaymentInfo paymentInfo;
    public UserType typeOfUser;

    public Client(PaymentInfo paymentInfo, String fName, String lName, String email, Address address, String username, String password) {
        super(fName, lName, email, address, username, password);
        this.paymentInfo = paymentInfo;
        typeOfUser = CLIENT;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo newInfo) {
        this.paymentInfo = newInfo;
    }
}
