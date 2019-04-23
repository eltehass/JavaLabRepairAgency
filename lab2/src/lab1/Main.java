package lab1;

import lab1.controller.Controller;
import lab1.view.View;

public class Main {

    public static void main(String[] args) {
        var controller = new Controller(System.in, new View());
        controller.startWorkWithUser();
    }

}