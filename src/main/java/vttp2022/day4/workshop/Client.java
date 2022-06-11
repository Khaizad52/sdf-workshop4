package vttp2022.day4.workshop;

import java.net.*;  
import java.io.*;  

public class Client{  
        public static void main(String[] args){
            System.out.println("Cookie Client");
            String[] arr =args[0].split(":");


            try{
                Socket socket =new Socket(arr[0], Integer.parseInt(arr[1]));

                InputStream is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                Console cons = System.console();
                String input = cons.readLine("Send command to server > ");
                dos.writeUTF(input);
                dos.flush();

                String response = dis.readUTF();
                if(response.contains("cookie-text")){
                    System.out.println(response);
                    String[] cookieValue = response.split(" ");
                    System.out.printf("Cookie from server >> %s\n", cookieValue[1]);

                }
                is.close();
                os.close();

                socket.close();


            } catch(NumberFormatException ex){
                System.out.println("Server not found:" + ex.getMessage());

            } catch(UnknownHostException ex){
                System.out.println("Server not found:" + ex.getMessage());

            } catch(IOException ex){

                System.out.println("in/out error:" + ex.getMessage());
            }
        }
    }
