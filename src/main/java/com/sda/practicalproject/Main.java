package com.sda.practicalproject;

import com.sda.practicalproject.controler.menu.MenuItem;
import com.sda.practicalproject.utils.SessionManager;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SessionManager.getSessionFactory();

        for (int i = 0; i <= 100; i++) {
            System.out.println("/////////////////////////////////");
        }
        MenuItem selectedOption = MenuItem.UNKNOWN;
        while (selectedOption != MenuItem.EXIT) {
            System.out.println();
            MenuItem.printMenuItems();
            System.out.println("Please select an option : ");
            try {
                int numericOption = Integer.parseInt(scanner.nextLine());
                selectedOption = MenuItem.searchByOption(numericOption);
            }catch (NumberFormatException e){
                System.out.println("Please use numeric value");
                selectedOption = MenuItem.UNKNOWN;
            }

            switch (selectedOption) {
                case ADD_VET:
                    System.out.println("Add vet not implemented");
                    break;
                case UPDATE_VET:
                    System.out.println("Update vet not implemented");
                    break;
                case DELETE_VET:
                    System.out.println("Delete vet not implemented");
                    break;
                case EXIT:
                    System.out.println("Good Bye and have a nice day");
                    break;
                case UNKNOWN:
                    System.out.println("Please insert a valid option");
                    break;
                default:
                    System.out.println("Option not implemented");
                    break;
            }
        }
        SessionManager.shutdown();
    }
}