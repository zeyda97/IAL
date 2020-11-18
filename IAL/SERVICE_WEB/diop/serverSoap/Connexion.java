package serverSoap;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import serverSoap.Authentification;
import serverSoap.SoapService;

public class Connexion extends JFrame implements ActionListener{
    /**
	 * @param args
	 */
    JFrame fenetre;
    JPanel panel;
    JButton connect,quit;
    JTextField login,password;
    JLabel logi,pswd,result;
    
    public Connexion(){
	fenetre = new JFrame();
	panel = new JPanel();
	connect = new JButton("Se connecter");
        quit = new JButton("Quitter");
        logi = new JLabel("LOGIN");
        login = new JTextField(15);
        pswd = new JLabel("PASSWORD");
	connect.addActionListener(this);
        quit.addActionListener(this);
	password = new JTextField(15);
        result = new JLabel("");
        
	this.setContentPane(panel);
        Box logBox = Box.createHorizontalBox();
        logBox.add(logi);
        logBox.add(login);
        Box pasBox = Box.createHorizontalBox();
        pasBox.add(pswd);
        pasBox.add(password);
        panel.add(logBox);
	panel.add(pasBox);
	panel.add(connect);
	panel.add(result);
        panel.add(quit);
        panel.setBackground(Color.lightGray);
	setVisible(true);
	setSize(330,270);
    }
        
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connexion c = new Connexion();
    }
        
    public void actionPerformed(ActionEvent e) {
         String log = login.getText();
        String pwd= password.getText();
        if(e.getSource()==connect)
        {
            serverSoap.Soap s =new SoapService().getSoapPort() ;
            boolean res= s.authentification(log,pwd);
            if(res==true){
                Accueil a = new Accueil();
                this.dispose();
            }
            else{
                result.setText("login ou mot de passe incorrect");
                result.setVisible(true);
            }
        }
        if(e.getSource()==quit){
            this.dispose();
        }
    }
}
