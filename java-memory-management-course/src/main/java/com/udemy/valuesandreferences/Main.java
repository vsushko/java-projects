package com.udemy.valuesandreferences;

/**
 * @author vsushko
 */
public class Main {

    public static void main(String[] args) {
        int localValue = 5;
        calculate(localValue);
        System.out.println(localValue);

        final Customer customer = new Customer("Sally");
        renameCustomer(customer);
        System.out.println(customer.getName());
    }

    private static void calculate(int calcValue) {
        calcValue *= 100;
    }

    private static void renameCustomer(Customer customer) {
        customer.setName("Diane");
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}