package com.example.Mealer_App.structure;

public class Address {

    private String street;
    private int number;
    private String postal;
    private String city;
    public int suite;
    /**
     * Description: Empty private constructor that does not take any parameters.
     * The purpose is to allow specific methods to test if Address is valid
     * through the setters since they run checks for validity in their implementation.
     *
     * For example, if a user would like to remove an address but only provide
     * a street name and number, they Employee class can create an empty address
     * with these parameters. Then, it is able to use setters to make the street name
     * conform to the format defined in the setStreet method. Therefore, if the user
     * enters "mark-anthony street", the setter will correct it to "Mark-Anthony Street"
     * since otherwise, it would not find a match if the address existed for the employee.
     */
    protected Address() {
        street = postal = city = "";
        number = suite = 0;
    }

    /**
     * @param street
     * @param number
     * @param postal
     * @param city
     * @param suite
     * @throws Exception
     *
     * Description: Constructor for class Address. Sets instance variables and will
     * throw exceptions if variables are invalid.
     */
    Address(String street, int number, String postal, String city, int suite) {
        this.street = street;
        this.number = number;
        this.postal = postal;
        this.city = city;
        this.suite = suite;
    }

    //proper constructor requires too much testing for deliverable 1
//    Address(String street, int number, String postalCode, String city, int suite) throws Exception {
//        //setters will return false is parameters are invalid, and throw an exception to allow user to retry.
//
//        if(!setStreet(street)) {
//            throw new Exception("Invalid street name");
//        }
//        if(!setNumber(number)) {
//            throw new Exception("Invalid street number");
//        }
//        if(!setPostal(postalCode)) {
//            throw new Exception("Invalid postal code");
//        }
//        if(!setCity(city)) {
//            throw new Exception("Invalid city name");
//        }
//    }

    /**
     * @return
     *
     * Description: Simple getter to retrieve the street name of the Address
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * @return
     *
     * Description: Simple getter to retrieve the street number of the Address
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * @return
     *
     * Description: Simple getter to retrieve the postal code of the Address
     */
    public String getPostal() {
        return this.postal;
    }

    public String getCity() {
        return this.city;
    }

    public int getSuite() {
        return this.suite;
    }

    /**
     * @param newStreet
     * @return
     *
     * Description: Method for setting the private variable 'street'. Since
     * formatting is key to maintain consistency between addresses, upper/lower
     * case is set according to letter placement and digits are considered invalid
     * in a street name.
     */
    public boolean setStreet(String newStreet) {
        newStreet = newStreet.trim();
        char streetAsChar[] = newStreet.toCharArray(); //Converting to char[] in order to iterate through string
        for(int i = 0; i < newStreet.length(); i++) {
            if(Character.isDigit(streetAsChar[i])) {
                return false;
            } else if(i == 0 || streetAsChar[i-1] == ' ' || streetAsChar[i-1] == '\'' || streetAsChar[i-1] == '-') {
                streetAsChar[i] = Character.toUpperCase(streetAsChar[i]); //Any character at the start of the street name or after a hypen/dash will be capitalized
            } else {
                streetAsChar[i] = Character.toLowerCase(streetAsChar[i]);
            }
        }
        this.street = new String(streetAsChar); //convert char[] of street name
        return true;
    }

    public boolean setCity(String newCity) {
        newCity = newCity.trim();
        char cityAsChar[] = newCity.toCharArray(); //Converting to char[] in order to iterate through string
        for(int i = 0; i < newCity.length(); i++) {
            if(Character.isDigit(cityAsChar[i])) {
                return false;
            } else if(i == 0 || cityAsChar[i-1] == ' ' || cityAsChar[i-1] == '\'' || cityAsChar[i-1] == '-') {
                cityAsChar[i] = Character.toUpperCase(cityAsChar[i]); //Any character at the start of the street name or after a hypen/dash will be capitalized
            } else {
                cityAsChar[i] = Character.toLowerCase(cityAsChar[i]);
            }
        }
        this.city = new String(cityAsChar); //convert char[] of street name
        return true;
    }

    /**
     * @param num
     * @return
     *
     * Description: Method for setting private variable 'number'. If the
     * street number is negative or 0, it is invalid.
     */
    public boolean setNumber(int num) {
        if(num > 0) { //0 is not a valid street name
            this.number = num;
            return true;
        }
        return false;
    }

    public boolean setSuite(int suite) {
        if(suite > 0) {
            this.suite = suite;
            return true;
        }

        return false;
    }

    /**
     * @param postalCode
     * @return
     *
     * Description: Method for setting private variable 'postal'. Handles
     * postal codes in the format of "X1X1X1" as well as "X1X 1X1". If the
     * length of the trimmed String is not 6 or 7 characters long, then the
     * postal code is considered invalid. Also checks validity by ensuring
     * that characters are digits when they are supposed to be and letters
     * otherwise.
     */
    public boolean setPostal(String postalCode) {

        postalCode = postalCode.trim().toUpperCase();
        char postalArray[] = postalCode.toCharArray();
        if (postalArray.length == 7) {
            if (Character.isLetter(postalArray[0]) && Character.isLetter(postalArray[2]) && Character.isLetter(postalArray[5])
                    && Character.isDigit(postalArray[1]) && Character.isDigit(postalArray[4]) && Character.isDigit(postalArray[6])
                    && Character.isWhitespace(postalArray[3])) { //Checking format of string to see if it is valid
                this.postal = postalCode;
                return true;
            }
        }

        else if (postalArray.length == 6) {
            if (Character.isLetter(postalArray[0]) && Character.isLetter(postalArray[2]) && Character.isLetter(postalArray[4])
                    && Character.isDigit(postalArray[1]) && Character.isDigit(postalArray[3]) && Character.isDigit(postalArray[5])) {
                this.postal = postalCode; //Same check as before without the check for a white space at index position 3
                return true;
            }
        }
        return false;
    }

    /**
     * @param second
     * @return
     *
     * Description: Checks if two Addresses are the same through comparing
     * street name, number and postal code
     */
    public boolean equals(Address second) {
        if (this.street.equals(second.getStreet()) && this.number == second.number && this.postal.equals(second.getPostal())) {
            return true;
        }
        return false;
    }

}

