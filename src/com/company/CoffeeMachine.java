package com.company;

import java.util.Scanner;

public class CoffeeMachine {
    int water;
    int coffeeBeans;
    int milk;
    int cup;
    double money;
    States state = States.CHOOSING_ACTION;
    boolean exit = false;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int cup, double money) {
        this.water = water;
        this.coffeeBeans = coffeeBeans;
        this.milk = milk;
        this.cup = cup;
        this.money = money;
    }

    public boolean userInput(String input) {
        switch (state) {
            case CHOOSING_ACTION:
                choosingAnAction(input);
                break;
            case CHOOSING_COFFEE_TYPE:
                makeCoffee(input);
                break;
            case FILL_WATER:
                fillWater(input);
                break;
            case FILL_MILK:
                fillMilk(input);
                break;
            case FILL_BEANS:
                fillBeans(input);
                break;
            case FILL_CUPS:
                fillCups(input);
                break;
        }
        return exit;
    }

    private void choosingAnAction(String input) {
        switch (input) {
            case "buy":
                state = States.CHOOSING_COFFEE_TYPE;
                message("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                break;
            case "fill":
                state = States.FILL_WATER;
                message("Write how many ml of water do you want to add:");
                break;
            case "remaining":
                displayTheNumberOfIngredients();
                state = States.CHOOSING_ACTION;
                message("Write action (buy, fill, take, remaining, exit): ");
                break;
            case "take":
                takeOut();
                state = States.CHOOSING_ACTION;
                message("Write action (buy, fill, take, remaining, exit): ");
                break;
            case "exit":
                exit = true;
                break;
        }
    }

    private void makeCoffee(String input) {
        switch (input) {
            case "1":
                buyEspresso();
                state = States.CHOOSING_ACTION;
                message("Write action (buy, fill, take, remaining, exit): ");
                break;
            case "2":
                buyLatte();
                state = States.CHOOSING_ACTION;
                message("Write action (buy, fill, take, remaining, exit): ");
                break;
            case "3":
                buyCappuccino();
                state = States.CHOOSING_ACTION;
                message("Write action (buy, fill, take, remaining, exit): ");
                break;
            case "back":
                state = States.CHOOSING_ACTION;
                message("Write action (buy, fill, take, remaining, exit): ");
                break;
            default:
                message("There is no such coffee.");
                state = States.CHOOSING_ACTION;
                message("Write action (buy, fill, take, remaining, exit): ");
                break;
        }
    }

    private void fillWater(String input) {
        water += Integer.parseInt(input);
        state = States.FILL_MILK;
        message("Write how many ml of milk do you want to add:");
    }

    private void fillMilk(String input) {
        milk += Integer.parseInt(input);
        state = States.FILL_BEANS;
        message("Write how many grams of coffee beans do you want to add:");
    }

    private void fillBeans(String input) {
        coffeeBeans += Integer.parseInt(input);
        state = States.FILL_CUPS;
        message("Write how many disposable cups of coffee do you want to add:");
    }

    private void fillCups(String input) {
        cup += Integer.parseInt(input);
        state = States.CHOOSING_ACTION;
        message("Write action (buy, fill, take, remaining, exit");
    }

    private void displayTheNumberOfIngredients() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cup + " of disposable cups");
        System.out.println(Math.round(money) + " of money");
        System.out.println();
    }

    private void buyEspresso() {
        if (water < 250) {
            message("Sorry, not enough water!");
        } else if (coffeeBeans < 16) {
            message("Sorry, not enough coffee beans!");
        } else if (cup < 0) {
            message("Sorry, not enough cups!");
        } else {
            water -= 250;
            coffeeBeans -= 16;
            money += 4;
            --cup;
            message("I have enough resources, making you a coffee!");
        }
    }

    private void buyLatte() {

        if (water < 350) {
            message("Sorry, not enough water!");
        } else if (coffeeBeans < 20) {
            message("Sorry, not enough coffee beans!");
        } else if (cup < 0) {
            message("Sorry, not enough cups!");
        } else if (milk < 75) {
            message("Sorry, not enough milk!");
        } else {
            water -= 350;
            milk -= 75;
            coffeeBeans -= 20;
            money += 7;
            --cup;
            message("I have enough resources, making you a coffee!");
        }
    }

    private void buyCappuccino() {
        if (water < 200) {
            message("Sorry, not enough water!");
        } else if (coffeeBeans < 12) {
            message("Sorry, not enough coffee beans!");
        } else if (cup < 0) {
            message("Sorry, not enough cups!");
        } else if (milk < 100) {
            message("Sorry, not enough milk!");
        } else {
            water -= 200;
            milk -= 100;
            coffeeBeans -= 12;
            money += 6;
            --cup;
            message("I have enough resources, making you a coffee!");
        }
    }

    private void message(String text) {
        System.out.println(text);
    }


    private void takeOut() {
        message("I gave you $" + Math.round(money));
        money = 0;
    }


    public static void main(String args[]) {
        // write a body here
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        machine.message("Write action (buy, fill, take, remaining, exit): ");
        while (!exit) {
            String input = sc.nextLine();
            exit = machine.userInput(input);
        }
    }
}



