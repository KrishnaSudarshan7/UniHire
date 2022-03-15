package com.example.unihire;

public class AddressClass {
    public String AddressL1,AddressL2,Country,State,City;
    int Pincode;
    AddressClass(String AddressL1,String AddressL2,String Country,String State,String City, int Pincode){
        this.AddressL1=AddressL1;
        this.AddressL2=AddressL2;
        this.Country=Country;
        this.State=State;
        this.City=City;
        this.Pincode=Pincode;
    }
}
