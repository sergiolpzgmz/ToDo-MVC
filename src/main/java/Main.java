import Controller.Controller;
import View.View;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        View frame = new View();
        frame.setTitle("Todo App");
        frame.setVisible(true);
        new Controller(frame);
    }
}
