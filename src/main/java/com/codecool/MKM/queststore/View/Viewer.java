package com.codecool.MKM.queststore.View;

import java.util.*;

public class Viewer {




    /// create group - new table in DB must be create - rest of creepy functionality in DAO done


    public void printMenu(String[] menu) {
        printSeparateLine();

        printSeparateLine();
        for (int i = 0; i<menu.length;i++) {
            System.out.println("("+(i+1)+") "+ menu[i]);
        }
    }


    private void printSeparateLine() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
    }


    public int getIntegerInputFromUser(String communicate) {
        printSeparateLine();
        System.out.println(communicate);
        Scanner scan = new Scanner(System.in);
        int userInput = 0;
        boolean inputOk = false;

        while(!inputOk) {

            try {
                userInput = scan.nextInt();
                inputOk = true;
            } catch (InputMismatchException e) {

                scan.nextLine();
                printMessage("Bad input!");
                continue;
            }
        }
        return userInput;
    }


    public String getStringFromUser(String communicate) {
        printSeparateLine();
        System.out.println(communicate);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();

    }

    public void printMessage(String message) {
        System.out.println(message);
    }


    public void printTable(List<List<String>> listOfRecords, List<String> header) {
        printSeparateLine();
        printHeader(header);
        printSeparateLine();
        printRestOfTable(listOfRecords);
    }


    private void printHeader(List<String> header) {
        for (int i = 0; i<header.size(); i++) {
            if (i+1 != header.size()) {
                System.out.print(header.get(i));
                System.out.print("                    ");
            }
            else{
                System.out.println(header.get(i));
            }
        }
    }


    private void printRestOfTable(List<List<String>> listOfRecords) {

        for (List<String> record : listOfRecords) {
            for (int i = 0; i<record.size(); i++) {
                if (i + 1 != record.size()) {
                    System.out.print(record.get(i));
                    System.out.print("          ");
                } else {
                    System.out.println(record.get(i));
                }
            }

        }
    }
}
