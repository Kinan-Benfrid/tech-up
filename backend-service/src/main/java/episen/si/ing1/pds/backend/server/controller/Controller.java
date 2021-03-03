package episen.si.ing1.pds.backend.server.controller;

import episen.si.ing1.pds.backend.server.model.Crud;
import episen.si.ing1.pds.backend.server.pool.DataSource;
import episen.si.ing1.pds.backend.server.view.View;

import java.util.Scanner;


public class Controller {
    private final Crud crud;
    private final View view;

    public Controller(Crud crud, View view) {
        this.crud = crud;
        this.view = view;
    }

    public void control(DataSource ds) {
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
                    System.out.println("\n Lastname : ");
                    lastName = sc.nextLine();
                    crud.insert(firstName, lastName);
                    view.showView();
                    break;
                case "d":
                    System.out.println("Please, enter a firstname and lastname to delete \n");
                    System.out.println("Firstname : ");
                    firstName = sc.nextLine();
                    System.out.println("\n Lastname : ");
                    lastName = sc.nextLine();
                    crud.delete(firstName, lastName);
                    view.showView();
                    break;
                case "u":
                    System.out.println("Please, enter the firstname and lastname you want to change, then enter the new values: \n");
                    System.out.println("First Name : ");
                    firstName = sc.nextLine();
                    System.out.println("\n Last Name : ");
                    lastName = sc.nextLine();
                    System.out.println("\n New First Name : ");
                    newFirstName = sc.nextLine();
                    System.out.println("\n New Last Name : ");
                    newLastName = sc.nextLine();
                    crud.update(firstName, lastName, newFirstName, newLastName);
                    view.showView();
                    break;
            }
            response = sc.nextLine();
        }

    }


}
