import java.io.*;
import java.net.*;
import java.util.Scanner;

class Cliente
{
   private static int portaServidor = 6789;
   private static String IPServidor = "192.168.15.138";//computador desktop do Lucas tem o endereco 192.168.15.93
   private static int minhaPorta = 6700;//mudar? no servidor

   public static Scanner entrada = new Scanner(System.in);

   public static String lerString () throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      return in.readLine();
   }

   public static String recebeMensagem(Socket socketServidor){
      String resposta= new String();
      try {
         BufferedReader entradaB =  new BufferedReader(new InputStreamReader(socketServidor.getInputStream()));
         /*while(entrada.readLine().equals("")){
            entrada =  new BufferedReader(new InputStreamReader(conexao.getInputStream()));
         }*/
         resposta = entradaB.readLine();
      } catch (Exception e) {
         System.out.println("Nao recebi mensagem TCP!");
      }
      return resposta;
   }

   public static boolean enviaMensagem(Socket socketServidor, String mensagem){
      boolean deuCerto=false;
      try {
         DataOutputStream saida = new DataOutputStream(socketServidor.getOutputStream());
         saida.writeBytes(mensagem+'\n');
         deuCerto=true;
      } catch (Exception e) {
         System.out.println("Nao consegui enviar a mensagem TCP!");
      }
      return deuCerto;
   }

   public static boolean enviaMensagemUDP(DatagramSocket serverSocket, String mensagem, InetAddress ipServidor){
      byte[] sendData = new byte[1024];
      boolean deuCerto=false;
      try {
         sendData = (mensagem+'\n').getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServidor, portaServidor);
         serverSocket.send(sendPacket);
      } catch (Exception e) {
         System.out.println("Nao consegui enviar a mensagem UDP!");
      }
      return deuCerto;
   }

   public static String recebeMensagemUDP(DatagramSocket socket){
      byte[] receiveData = new byte[1024];
      String mensagem = new String();
      try {
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         socket.receive(receivePacket);
         mensagem = new String(receivePacket.getData());
      } catch (Exception e) {
         System.out.println("nao recebi mensagem UDP!");
      }
      return mensagem;
   }

   public static void main(String argv[]) throws Exception
   {
      String resposta = new String();
      //ServerSocket socketTCP = new ServerSocket(minhaPortaServidor);
      DatagramSocket socketUDP = new DatagramSocket(minhaPorta);
      InetAddress ipCliente = InetAddress.getByName(IPServidor);;
      
      //inicia uma conexao TCP para garantir sincronismo com o cliente
      System.out.println("(CLIENTE)Vou tentar me conectar");
      Socket conexao = new Socket(IPServidor, portaServidor);
      System.out.println("(CLIENTE)Consegui me conectar");
      enviaMensagem(conexao, "INICIAR JOGO");
      System.out.println("(CLIENTE)Enviei INICIAR JOGO");
      resposta = recebeMensagem(conexao);
      System.out.println("(TCP)Recebi: " + resposta);
      //conexao.close();
      //renderiza a tela *******************************************
      
      //envio de jogadas via UDP
      do{
         resposta = recebeMensagem(conexao);
         //resposta = recebeMensagemUDP(socketUDP);

         //envia confirmação
         System.out.println("Recebi: " + resposta);
         if(resposta.equalsIgnoreCase("Fim do jogo")==false){
            //renderiza a tela *******************************************
            System.out.println("Digite a sua jogada:");
            String minhaJogada = entrada.nextLine();
            enviaMensagem(conexao, minhaJogada);
            //enviaMensagemUDP(socketUDP, minhaJogada, ipCliente);
            System.out.println("Enviei: "+minhaJogada);
         }
         else{
            enviaMensagem(conexao, "fim do jogo");
         }
      }while(resposta.equalsIgnoreCase("Fim do jogo")==false);
      conexao.close();
      socketUDP.close();
      System.out.println("FIM DO PROGRAMA");
   }
   
}

//192.168.15.93 IP que o roteador me deu (IP falso)