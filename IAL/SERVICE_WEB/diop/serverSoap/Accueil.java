/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSoap;

//import controller.Utilisateur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author diarra
 */
public class Accueil extends JFrame implements ActionListener {
    JFrame fenetre;
    JPanel panel;
    JButton ajouter,modifier,supprimer,lister,quit;
    JTextField login,password,nlogin,npassword;
    JLabel logi,pswd,nlogi,npswd,result,res;
    
    public Accueil(){
	fenetre = new JFrame();
	panel = new JPanel();
	ajouter = new JButton("Ajouter");
	modifier = new JButton("Modifier");
        supprimer = new JButton("Supprimer");
	lister = new JButton("Lister");
        login = new JTextField(20);
	password = new JTextField(20);
        nlogin = new JTextField(20);
	npassword = new JTextField(20);
        quit = new JButton("Quitter");
        logi = new JLabel("LOGIN");
        pswd = new JLabel("PASSWORD");
        nlogi = new JLabel("NOUVEAU LOGIN");
        npswd = new JLabel("NOUVEAU PASSWORD");
        result = new JLabel("");
        res = new JLabel("");
	ajouter.addActionListener(this);
	modifier.addActionListener(this);
	supprimer.addActionListener(this);
	lister.addActionListener(this);
        quit.addActionListener(this);
	
	this.setContentPane(panel);
        Box logBox = Box.createHorizontalBox();
        logBox.add(logi);
        logBox.add(login);
        Box pasBox = Box.createHorizontalBox();
        pasBox.add(pswd);
        pasBox.add(password);
        Box nlogBox = Box.createHorizontalBox();
        nlogBox.add(nlogi);
        nlogBox.add(nlogin);
        Box npasBox = Box.createHorizontalBox();
        npasBox.add(npswd);
        npasBox.add(npassword);
        Box mBox = Box.createHorizontalBox();
        mBox.add(modifier);
        
        
	panel.add(ajouter);
	panel.add(supprimer);
	panel.add(lister);
        panel.add(logBox);
	panel.add(pasBox);
        panel.add(mBox);
        panel.add(nlogBox);
        panel.add(npasBox);
        panel.add(quit);
        panel.add(result);
        panel.add(res);
        
        panel.setBackground(Color.lightGray);
	setVisible(true);
	setSize(500,500);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String log = login.getText();
        String pwd= password.getText(); 
        String nlog = nlogin.getText();
        String npwd= npassword.getText();
        
        Soap s =new SoapService().getSoapPort() ;        
        if(ae.getSource()==ajouter)
        {
            boolean res = s.addUser(log,pwd);
            if(res=true){
               result.setText("utilisateur ajouté");
                result.setVisible(true);
            }
            else{
                result.setText("echec ajout");
                result.setVisible(true);
            }
        }
        if(ae.getSource()==modifier){
            boolean res = s.modifyUser(log,pwd,nlog,npwd);
            if(res=true){
               result.setText("parametre utilisateur modifié");
                result.setVisible(true);
            }
            else{
                result.setText("echec modification");
                result.setVisible(true);
            }	
        }
        if(ae.getSource()==supprimer){
          boolean res = s.deleteUser(log,pwd);
            if(res=true){
              result.setText("utilisataeur supprimé");
                result.setVisible(true);
            }
            else{
                result.setText("echec suppression");
                result.setVisible(true);
            }
        }
        if(ae.getSource()==lister){
            res.setText("");
            ArrayList<Utilisateur> List = (ArrayList<Utilisateur>) s.listUser();
            for(Utilisateur Liste:List)
            {
               res.setText(res.getText()+"  "+Liste.getLogin()+"/"+Liste.getPassword()
                      );
            }
             this.getContentPane().add(res);
                setVisible(true);
        }
        if(ae.getSource()==quit){
            this.dispose();
        }
    }
     public static void main(String[] args) {
        // TODO Auto-generated method stub
        Accueil a = new Accueil();
    }
}
