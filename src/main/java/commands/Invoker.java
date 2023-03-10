package commands;

import utils.Environment;

import java.util.HashMap;

public class Invoker {
    private Environment environment;
    private HashMap<String, ICommand> commandHashMap = new HashMap<>();
    public HashMap<String, ICommand> getCommandHashMap() {
        return commandHashMap;
    }

    public Invoker(Environment environment, ICommand[] commands){
        this.environment = environment;
        commandHashMap.put("help", new Help(this));
        commandHashMap.put("clear", new Clear(environment.getCollectionManager()));
        commandHashMap.put("info", new Info());
        commandHashMap.put("insert", new Insert(this));
        commandHashMap.put("update", new UpdateID());
        commandHashMap.put("removeKey", new RemoveKey());
        commandHashMap.put("exit", new Exit());
    }

    public void executer(String message) {
        if (message.split(" ").length > 1) {
            System.setOut(System.out);
            String[] mem = message.split(" ");
            String messageNext = "";
            for (int i = 0; i < mem.length; i++) {
                if (i > 0 && i != mem.length - 1) {
                    messageNext += mem[i] + " ";
                } else {
                    if (i > 0) {
                        messageNext += mem[i];
                    }
                }
            }
            commandHashMap.get(message.split(" ")[0]).execute(environment, messageNext);
        } else {
            System.setOut(System.out);
            commandHashMap.get(message).execute(environment,"");
        }
    }

}
