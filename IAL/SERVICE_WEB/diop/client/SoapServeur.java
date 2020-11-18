/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.xml.ws.Endpoint;

/**
 *
 * @author diarra
 */
public class SoapServeur {
    private static String url;
    private static String wsName;

    public static void main(String[] args) {

	wsName = "GestionUser";
	url = "http://localhost:1442/";
	Endpoint.publish(url, new Soap());
	System.out.println("Le serveur a été lancé avec succés à l'adresse "+ url + wsName + "?wsdl");
    }	 
}
