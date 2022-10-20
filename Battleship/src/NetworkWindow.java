import java.awt.*;
import javax.swing.*;

public class NetworkWindow extends JFrame {

	JPanel netPanel;
	
	NetworkWindow()
	{
		super("Network Window");
		this.setVisible(true);
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}