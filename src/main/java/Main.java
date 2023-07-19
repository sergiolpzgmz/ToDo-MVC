import Controller.Controller;
import Repository.TaskRepositoryImp;
import Util.DataBaseConnection;
import View.View;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        View frame = new View();
        frame.setVisible(true);
        Controller controller = new Controller(frame);

        TaskRepositoryImp t = new TaskRepositoryImp();
        t.listTasks();
    }
}