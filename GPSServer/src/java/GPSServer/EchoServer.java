/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPSServer;

/**
 *
 * @author somi
 */
import java.io.IOException;
 
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/echo") 
public class EchoServer {
    double lat, lng;
    boolean flag=false;
    Session andy,peggy;
    TCPServer mServer = new TCPServer(new TCPServer.OnMessageReceived() {
                    @Override
                    //this method declared in the interface from TCPServer class is implemented here
                    //this method is actually a callback method, because it will run every time when it will be called from
                    //TCPServer class (at while)
                    public void messageReceived(String message) {
                        String[] bits=message.split(",");
                        lat=Double.parseDouble(bits[0]);
                        lng=Double.parseDouble(bits[1]);
                        try {
                           peggy.getBasicRemote().sendText(message);
                        } catch (IOException ex) {
                           ex.printStackTrace();
                        }
                    }
                });

    
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection"); 
        if(!flag) {
            mServer.start();
            flag=true;
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
        String[] bits=message.split(",");
        if(bits[0].equals("p")) {
            peggy=session;
            lat=Double.parseDouble(bits[1]);
            lng=Double.parseDouble(bits[2]);
            try {
                session.getBasicRemote().sendText(""+lat+","+lng);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            andy=session;
            lat=Double.parseDouble(bits[0]);
            lng=Double.parseDouble(bits[1]);
        }
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
    }
}
