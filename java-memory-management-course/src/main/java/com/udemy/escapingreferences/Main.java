package com.udemy.escapingreferences;

public class Main {

    public static void main(String[] args) {
        CustomerRecords records = new CustomerRecords();

        records.addCustomer(new Customer("John"));
        records.addCustomer(new Customer("Simon"));

        // records.getCustomers().clear();

        for (Customer next : records.getCustomers().values()) {
            System.out.println(next);
        }

        CustomerReadOnly john = records.getCustomerByName("John");
        System.out.println(john.getName());
        // we changed Customer to CustomerReadOnly above
        // now set method now isn't able
        // john.setName("Derek");
        System.out.println(john.getName());

        for (Customer next : records.getCustomers().values()) {
            System.out.println(next);
        }
    }
}
