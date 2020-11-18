/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.jws.WebService;

/**
 *
 * @author diarra
 */

@WebService
public class Soap{
 private Connection connect= null;
 private PreparedStatement state = null;
 private ResultSet res = null;
 
  public Soap(){
    try{
    Class.forName("com.mysql.jdbc.Driver");
    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ialproject","root","");
    }
    catch(Exception ex){
        System.out.println("connection base de donnes a echoue "+ex.getMessage());
    }
}
    public boolean addUser(String login,String pwd)
    {
        Utilisateur u = new Utilisateur();
        u.setLogin(login);
        u.setPassword(pwd);
        
        try{
            state = connect.prepareStatement("insert into utilisateur(login,password)values (?,?)");
            state.setString(1,u.getLogin());
            state.setString(2,u.getPassword());
            state.executeUpdate();
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("insertion a echoue "+ex.getMessage());
        }
        return false;
    }
    
    public boolean modifyUser(String login,String pwd,String nlog,String npass)
    {
        Utilisateur u = new Utilisateur();
        u.setLogin(login);
        u.setPassword(pwd);
        
        try{
            state = connect.prepareStatement("update utilisateur set login= ?, password=? where login=? and password=?");
            state.setString(1,nlog);
            state.setString(2,npass);
            state.setString(3,u.getLogin());
            state.setString(4,u.getPassword());
            state.executeUpdate();
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("modification echoue "+ex.getMessage());
        }
        return false;
    }
    
     public boolean deleteUser(String login,String pwd)
    {
        Utilisateur u = new Utilisateur();
        u.setLogin(login);
        u.setPassword(pwd);
        try{
            state = connect.prepareStatement("delete from utilisateur where login=? and password = ?");
            state.setString(1,u.getLogin());
            state.setString(2,u.getPassword());
            state.executeUpdate();
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("suppression echoue "+ex.getMessage());
        }
        return false;
    }
     
     public ArrayList<Utilisateur> listUser(){
         ArrayList<Utilisateur> List = new ArrayList<>();
        
         try{
            state = connect.prepareStatement("select * from utilisateur");
            res = state.executeQuery();
            while(res.next()){
            String login = res.getString("login");
            String pwd = res.getString("password");
            Utilisateur u = new Utilisateur();
            u.setLogin(login);
            u.setPassword(pwd);
            List.add(u);
            }
        }
        catch(Exception ex){
            System.out.println("liste user echoue "+ex.getMessage());
        }
         return List;
    }
     public boolean authentification (String login, String pwd){
          try{
            ArrayList<Utilisateur> List = listUser();
            for(Utilisateur Liste : List){
            if(login.equals(Liste.getLogin()) && pwd.equals(Liste.getPassword())){
                return true;
            }
            }
            }  
            catch(Exception ex){
                System.out.println("authentification echoue "+ex.getMessage());
            }
          return false;
     }
}