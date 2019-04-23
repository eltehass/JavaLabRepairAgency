package lab1.view;

public class View {

    public void showMessage(String text) {
        System.out.print(text + "\n");
    }

    public void showInfo() {
        System.out.print("Choose person type to control(customer, manager or master)\n");
    }

    public void showInputRequest() {
        System.out.print("Write person type: ");
    }

    public void showCustomerCommands() {
        System.out.print("Use commands: create or comment \n");
    }

    public void showManagerCommands() {
        System.out.print("Use commands: accept, unaccept \n");
    }

    public void showMasterCommands() {
        System.out.print("Use commands: handle \n");
    }

    public void showCommandRequest() {
        System.out.print("Write command: ");
    }

    public void showInputCorrectCommand() {
        System.out.print("Input wasn't right. Try again, please.\n");
    }

}