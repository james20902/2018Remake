package frc.team5115;
import java.io.*;
import java.net.*;

public class UDPClient extends Thread {
    DatagramSocket sock;
    DatagramPacket response;
    InetAddress hostname;
    byte[] buffer = new byte[4];
    private String lastResponse = "null";

    //create our client
    public UDPClient(String host, int port) {
        try {
            hostname = InetAddress.getByName(host);
            sock = new DatagramSocket(port);
            response = new DatagramPacket(buffer, buffer.length);
            //if something breaks
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get our response, interpret the response
    private String getAsString(){
        try {
            sock.receive(response);
            //create new string with all of our buffer info so we get more than we need to
            String packet = new String(buffer, 0, response.getLength());
            //debug, print our packet
            System.out.println(packet);
            return packet;
            //if something breaks
        } catch (IOException e) {
            e.printStackTrace();
            //just return this string instead
            return "excp";
        }
    }

    public String getLastResponse() {
        return lastResponse;
    }

    //on a loop, get run getAsString
    public void run() {
        while (true) {
            lastResponse = getAsString();
        }
    }
}