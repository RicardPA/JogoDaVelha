import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.WindowConstants;

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

   public static void renderizar(TelaPrincipal t, String s){
	  String auxS[] = s.split(" ");
	  
	  for(int i = 0; i < auxS.length; i++) {
		  auxS[i].replace(" ", "");
	  }
	  
      if(s.length() == 8 && auxS[0].equals("BTN")) {
    	  if(auxS[1].equals("00")) {
            t.btnJogoDaVelha_00.setText(auxS[2]);
            t.btnJogoDaVelha_00.setEnabled(false);
    	  } else if(auxS[1].equals("01")) {
    		   t.btnJogoDaVelha_01.setText(auxS[2]);
            t.btnJogoDaVelha_01.setEnabled(false);
    	  } else if(auxS[1].equals("02")) {
            t.btnJogoDaVelha_02.setText(auxS[2]);
            t.btnJogoDaVelha_02.setEnabled(false);
    	  } else if(auxS[1].equals("10")) {
            t.btnJogoDaVelha_10.setText(auxS[2]);
            t.btnJogoDaVelha_10.setEnabled(false);
    	  } else if(auxS[1].equals("11")) {
            t.btnJogoDaVelha_11.setText(auxS[2]);
            t.btnJogoDaVelha_11.setEnabled(false);
    	  } else if(auxS[1].equals("12")) {
            t.btnJogoDaVelha_12.setText(auxS[2]);
            t.btnJogoDaVelha_12.setEnabled(false);
    	  } else if(auxS[1].equals("20")) {
            t.btnJogoDaVelha_20.setText(auxS[2]);
            t.btnJogoDaVelha_20.setEnabled(false);
    	  } else if(auxS[1].equals("21")) {
            t.btnJogoDaVelha_21.setText(auxS[2]);
            t.btnJogoDaVelha_21.setEnabled(false);
    	  } else if(auxS[1].equals("22")) {
            t.btnJogoDaVelha_22.setText(auxS[2]);
            t.btnJogoDaVelha_22.setEnabled(false);
    	  }
      }
   }

   public static void resetTela(TelaPrincipal t){
      t.btnJogoDaVelha_00.setEnabled(true);
      t.btnJogoDaVelha_00.setText("");
      t.btnJogoDaVelha_01.setEnabled(true);
      t.btnJogoDaVelha_01.setText("");
      t.btnJogoDaVelha_02.setEnabled(true);
      t.btnJogoDaVelha_02.setText("");
      t.btnJogoDaVelha_10.setEnabled(true);
      t.btnJogoDaVelha_10.setText("");
      t.btnJogoDaVelha_11.setEnabled(true);
      t.btnJogoDaVelha_11.setText("");
      t.btnJogoDaVelha_12.setEnabled(true);
      t.btnJogoDaVelha_12.setText("");
      t.btnJogoDaVelha_20.setEnabled(true);
      t.btnJogoDaVelha_20.setText("");
      t.btnJogoDaVelha_21.setEnabled(true);
      t.btnJogoDaVelha_21.setText("");
      t.btnJogoDaVelha_22.setEnabled(true);
      t.btnJogoDaVelha_22.setText("");
   }

   public static void encerrarJogo(TelaPrincipal t){
      t.setVisible(false);
      t.dispose();
   }

   public static void main(String argv[]) throws Exception
   {
      String resposta = new String();
      ServerSocket socketTCP = new ServerSocket(minhaPortaServidor);
      DatagramSocket socketUDP = new DatagramSocket(minhaPortaServidor+1);
      InetAddress ipCliente;
      TelaPrincipal telaDoJogo = TelaPrincipal.AlteraTela(argv);
      telaDoJogo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      
      //inicia uma conexao TCP para garantir sincronismo com o cliente
      System.out.println("estou esperando por uma conexao");
      Socket conexao = socketTCP.accept();
      ipCliente= InetAddress.getByName(conexao.getInetAddress().getHostAddress());
      resposta = recebeMensagem(conexao);
      System.out.println("(TCP)Recebi: " + resposta);
      enviaMensagem(conexao, "INICIAR JOGO");
      System.out.println("(TCP)Enviei: INICIAR JOGO");
      
      //envio de jogadas via UDP
      do{        
         resposta="";
         while(resposta.equals("")){
            resposta=TelaPrincipal.resp;
            System.out.print("");//não remover pois é parte fundamental do código (NÃO É BRINCADEIRA)
         }
         enviaMensagem(conexao, TelaPrincipal.resp);
         TelaPrincipal.resp = "";
         
         resposta=recebeMensagem(conexao);
         System.out.println("Recebi: " + resposta);
         
         if(telaDoJogo.isVisible()){
            if(resposta.equalsIgnoreCase("RESET")){
               resetTela(telaDoJogo);
               enviaMensagem(conexao, "RESET");
            }
            else if(resposta.equalsIgnoreCase("fim do jogo")){
               encerrarJogo(telaDoJogo);
            }
            else{
               renderizar(telaDoJogo, resposta);
            }
         }
         else{
            enviaMensagem(conexao, "fim do jogo");
         }
      }while(telaDoJogo.isVisible());
      
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