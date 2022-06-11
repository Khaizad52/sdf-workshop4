package vttp2022.day4.workshop;

import java.io.*;
import java.net.*;
import java.net.Socket;



public class Server {

    public static void main(String[] args) {
        
        //get server port number from first argument
        String serverPort = args[0];
        //get cookie file from second argument
        String cookieFile = args[1];

        try{
            //check if server port is correct
            System.out.println("Server is now on port: " + serverPort);
            
            //to match the port number from client
            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort));
            //getting client to connect
            Socket socket = server.accept();

            //getting input stream in bytes
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            //getting output stream in bytes
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            //read input from client
            String requestFromClient = dis.readUTF();
            System.out.printf("Client requesting: %s\n", requestFromClient);

            if(requestFromClient.equals("get-cookie")){
                System.out.printf("File -> %s\n", cookieFile);

                String randomCookie = Cookie.getRandomCookie(cookieFile);
                System.out.println(randomCookie);
                dos.writeUTF("cookie-text " + randomCookie);

            }else{
                //else return statement if not match
                dos.writeUTF("Invalid Command!");
            }
            //close input & output stream
            is.close();
            os.close();
            //close socket
            socket.close();

             }catch(IOException ex) {
                System.out.println("Server Exception:" + ex.getMessage());
                ex.printStackTrace();
            }
            catch(NumberFormatException e) {
                System.out.println("Server Exception:" + e.getMessage());
                e.printStackTrace();
            }
        
        }

    }


