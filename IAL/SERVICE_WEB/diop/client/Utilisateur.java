/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author diarra
 */
class Utilisateur {
    private String  login;
    private String pwd;
    
    public void setLogin(String log){
        this.login =log;
    }
    public void setPassword(String pass){
        this.pwd = pass;
    }
    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.pwd;
    }
}
