package lab1.controller;

import lab1.controller.commands.base.BaseCommand;
import lab1.controller.commands.customer_commands.CommentCommand;
import lab1.controller.commands.customer_commands.CreateCommand;
import lab1.controller.commands.manager_commands.AcceptCommand;
import lab1.controller.commands.manager_commands.UnacceptCommand;
import lab1.controller.commands.master_commands.HandleCommand;
import lab1.model.extra.RepairAgency;
import lab1.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Controller {

    private View view;
    private BufferedReader bufferedReader;

    public Controller(InputStream inputStream, View view) {
        this.view = view;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    public void startWorkWithUser() {
        view.showInfo();
        String userInputStr = "";
        BaseCommand agencyCommand = null;

        try {
            while (!userInputStr.equals("exit")) {
                view.showInputRequest();
                userInputStr = bufferedReader.readLine();

                if (userInputStr.equals("exit")) { break; }

                /** Customer **/
                if (userInputStr.toLowerCase().equals("customer")) {
                    view.showCustomerCommands();
                    view.showCommandRequest();
                    var command = bufferedReader.readLine();

                    while (!command.equals("create") && !command.equals("comment")) {
                        view.showInputCorrectCommand();
                        view.showCommandRequest();
                        command = bufferedReader.readLine();
                    }

                    if (command.equals("create")) {
                        view.showMessage("Write application body: ");
                        var message = bufferedReader.readLine();
                        agencyCommand = new CreateCommand(message);
                    }

                    if (command.equals("comment")) {
                        view.showMessage("Write UUID: ");
                        var uuid = bufferedReader.readLine();
                        view.showMessage("Write comment: ");
                        var comment = bufferedReader.readLine();
                        agencyCommand = new CommentCommand(UUID.fromString(uuid), comment);
                    }
                }

                /** Manager **/
                if (userInputStr.toLowerCase().equals("manager")) {
                    view.showManagerCommands();
                    view.showCommandRequest();
                    var command = bufferedReader.readLine();

                    while (!command.equals("accept") && !command.equals("unaccept")) {
                        view.showInputCorrectCommand();
                        view.showCommandRequest();
                        command = bufferedReader.readLine();
                    }

                    if (command.equals("accept")) {
                        view.showMessage("Write UUID: ");
                        var uuid = bufferedReader.readLine();
                        view.showMessage("Write price: ");
                        var price = Integer.parseInt(bufferedReader.readLine());
                        agencyCommand = new AcceptCommand(UUID.fromString(uuid), true, price, "");
                    }

                    if (command.equals("unaccept")) {
                        view.showMessage("Write UUID: ");
                        var uuid = bufferedReader.readLine();
                        view.showMessage("Write comment: ");
                        var comment = bufferedReader.readLine();
                        agencyCommand = new UnacceptCommand(UUID.fromString(uuid), false, 0, comment);
                    }
                }

                /** Master **/
                if (userInputStr.toLowerCase().equals("master")) {
                    view.showMasterCommands();
                    view.showCommandRequest();
                    var command = bufferedReader.readLine();

                    while (!command.equals("handle")) {
                        view.showInputCorrectCommand();
                        view.showCommandRequest();
                        command = bufferedReader.readLine();
                    }

                    view.showMessage("Write UUID: ");
                    var uuid = bufferedReader.readLine();
                    agencyCommand = new HandleCommand(UUID.fromString(uuid));
                }

                if (agencyCommand == null) {
                    view.showInputCorrectCommand();
                    continue;
                }

                view.showMessage(agencyCommand.execute());
                view.showMessage(RepairAgency.getInstance().getInvoicesInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}