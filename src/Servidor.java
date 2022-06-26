import java.io.*;
import java.net.*;
import java.util.Scanner;

class Servidor
{
   private static int minhaPortaServidor = 6700;
   private static int portaCliente = 6500;//mudar? no cliente

   public static Scanner entrada = new Scanner(System.in);

   public static String recebeMensagem(Socket socketCliente){
      String resposta= new String();
      try {
         BufferedReader entrada =  new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
         /*while(entrada.readLine().equals("")){
            entrada =  new BufferedReader(new InputStreamReader(conexao.getInputStream()));
         }*/
         resposta = entrada.readLine();
      } catch (Exception e) {
         System.out.println("Nao recebi mensagem TCP!");
      }
      return resposta;
   }

   public static boolean enviaMensagem(Socket socketCliente, String mensagem){
      boolean deuCerto=false;
      try {
         DataOutputStream saida = new DataOutputStream(socketCliente.getOutputStream());
         saida.writeBytes(mensagem+'\n');
         deuCerto=true;
      } catch (Exception e) {
         System.out.println("Nao consegui enviar a mensagem TCP!");
      }
      return deuCerto;
   }

   public static boolean enviaMensagemUDP(DatagramSocket serverSocket, String mensagem, InetAddress ipCliente){
      byte[] sendData = new byte[1024];
      boolean deuCerto=false;
      try {
         sendData = (mensagem+'\n').getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipCliente, portaCliente);
         serverSocket.send(sendPacket);
      } catch (Exception e) {
         System.out.println("Nao consegui enviar a mensagem UDP!");
      }
      return deuCerto;
   }

   public static String recebeMensagemUDP(DatagramSocket socket){
      System.out.println("52");
      byte[] receiveData = new byte[1024];
      String mensagem = new String();
      try {
         System.out.println("56");
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         socket.receive(receivePacket);
         System.out.println("59");
         mensagem = new String(receivePacket.getData());
         System.out.println("61");
      } catch (Exception e) {
         System.out.println("nao recebi mensagem UDP!");
      }
      return mensagem;
   }

   public static boolean isJogadaValida(String s){
      boolean resposta=false;
      if(s.equalsIgnoreCase("Fim de jogo")){
         resposta=true;
      }
      else if(s.length()==3 && s.charAt(0)=='J' && s.charAt(1)==' '){
         char aux = s.charAt(2);
         if((int)aux <57 && (int)aux>47){
            resposta=true;
         }
      }
      return resposta;
   }

   public static void main(String argv[]) throws Exception
   {
      String resposta = new String();
      ServerSocket socketTCP = new ServerSocket(minhaPortaServidor);
      DatagramSocket socketUDP = new DatagramSocket(minhaPortaServidor+1);
      InetAddress ipCliente;
      TelaPrincipal telaDoJogo = TelaPrincipal.AlteraTela(argv);
      
      //inicia uma conexao TCP para garantir sincronismo com o cliente
      System.out.println("estou esperando por uma conexao");
      Socket conexao = socketTCP.accept();
      ipCliente= InetAddress.getByName(conexao.getInetAddress().getHostAddress());
      resposta = recebeMensagem(conexao);
      System.out.println("(TCP)Recebi: " + resposta);
      //renderiza a tela *******************************************
      enviaMensagem(conexao, "INICIAR JOGO");
      System.out.println("(TCP)Enviei: INICIAR JOGO");
      /*conexao.close();
      socketTCP.close();*/
      
      //envio de jogadas via UDP
      do{
         System.out.println("Digite a sua jogada:");
         String minhaJogada = entrada.nextLine();
         enviaMensagem(conexao, minhaJogada);
         //enviaMensagemUDP(socketUDP, minhaJogada, ipCliente);
         System.out.println("Enviei: "+minhaJogada);

         resposta=recebeMensagem(conexao);
         //resposta = recebeMensagemUDP(socketUDP);
         System.out.println("Recebi: " + resposta);
         if(resposta.equalsIgnoreCase("Fim do jogo")==false){
            telaDoJogo.btnJogoDaVelha_00.setText(resposta);
            //renderiza a tela *******************************************
         }
         else{
            enviaMensagem(conexao, "fim do jogo");
         }
      }while(resposta.equalsIgnoreCase("Fim do jogo")==false);
      
      conexao.close();
      socketTCP.close();
      socketUDP.close();
      
      conexao.close();
      socketTCP.close();
      socketUDP.close();
      System.out.println("FIM DO PROGRAMA");
      
   }
}


/*
   JOGO DA VELHA

   FUNCIONALIDADES:
   -No incio do programa, conectar os dois usuários atraves de TCP
   -Enviar as jogadas de cada jogador utilizando UDP
      ->Utilizar mensagens de confirmação quando um jogador receber a jogada do outro
      ->Depois de realizar uma jogada, se não receber a confirmação do outro jogador, então envia outro UDP
   -A cada jogada (feita ou recebida), atualizar o estado do jogo na maquina local


*/