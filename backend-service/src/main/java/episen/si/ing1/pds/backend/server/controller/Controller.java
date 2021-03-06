package episen.si.ing1.pds.backend.server.controller;

import episen.si.ing1.pds.backend.server.model.Crud;
import episen.si.ing1.pds.backend.server.view.View;

import java.util.Scanner;


public class Controller {
    private Crud crud;
    private View view;

    public Controller(Crud crud, View view) {
        this.crud = crud;
        this.view = view;
    }

    public void control() {
        view.showView();
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        String firstName, lastName, newFirstName, newLastName;
        while (!response.equals("q")) {
            switch (response) {
                case "s":
                    System.out.println(crud.select());
                    view.showView();
                    break;
                case "i":
                    System.out.println("Please, enter a firstname and lastname to insert a line \n");
                    System.out.println("Firstname : ");
                    firstName = sc.nextLine();
                    System.out.println("\nLastname : ");
                    lastName = sc.nextLine();
                    crud.insert(firstName, lastName);
                    view.showView();
                    break;
                case "d":
                    System.out.println("Please, enter a firstname and lastname to delete \n");
                    System.out.println("Firstname : ");
                    firstName = sc.nextLine();
                    System.out.println("\nLastname : ");
                    lastName = sc.nextLine();
                    crud.delete(firstName, lastName);
                    view.showView();
                    break;
                case "u":
                    System.out.println("Please, enter the firstname and lastname you want to change, then enter the new values: \n");
                    System.out.println("First Name : ");
                    firstName = sc.nextLine();
                    System.out.println("\nLast Name : ");
                    lastName = sc.nextLine();
                    System.out.println("\nNew First Name : ");
                    newFirstName = sc.nextLine();
                    System.out.println("\nNew Last Name : ");
                    newLastName = sc.nextLine();
                    crud.update(firstName, lastName, newFirstName, newLastName);
                    view.showView();
                    break;
                case "t":

                default:
                    view.showView();
                    break;
            }
            response = sc.nextLine();
        }

    }


}
