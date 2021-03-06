import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.WindowConstants;

class Cliente
{
   private static int portaServidor = 6700;
   private static String IPServidor = "192.168.15.93";//computador desktop do Lucas tem o endereco 192.168.15.93
   private static int minhaPorta = 6500;//mudar? no servidor
   private static String jogador = "O";

   public static Scanner entrada = new Scanner(System.in);

   public static String lerString () throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      return in.readLine();
   }

   public static String recebeMensagem(Socket socketServidor){
      String resposta= new String();
      try {
         BufferedReader entradaB =  new BufferedReader(new InputStreamReader(socketServidor.getInputStream()));
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
   
   public static void bloqueiaDesbloqueiaBotoes(TelaPrincipal t, boolean b){
	  if(b) {
	      t.btnJogoDaVelha_00.setEnabled(false);
	      t.btnJogoDaVelha_01.setEnabled(false);
	      t.btnJogoDaVelha_02.setEnabled(false);
	      t.btnJogoDaVelha_10.setEnabled(false);
	      t.btnJogoDaVelha_11.setEnabled(false);
	      t.btnJogoDaVelha_12.setEnabled(false);
	      t.btnJogoDaVelha_20.setEnabled(false);
	      t.btnJogoDaVelha_21.setEnabled(false);
	      t.btnJogoDaVelha_22.setEnabled(false);
	      t.btnJogoDaVelha_Reiniciar.setEnabled(false);
	  } else {
		  if(t.btnJogoDaVelha_00.getText().length() == 0)
			  t.btnJogoDaVelha_00.setEnabled(true);
		  if(t.btnJogoDaVelha_01.getText().length() == 0)
			  t.btnJogoDaVelha_01.setEnabled(true);
		  if(t.btnJogoDaVelha_02.getText().length() == 0)
			  t.btnJogoDaVelha_02.setEnabled(true);
		  if(t.btnJogoDaVelha_10.getText().length() == 0)
			  t.btnJogoDaVelha_10.setEnabled(true);
		  if(t.btnJogoDaVelha_11.getText().length() == 0)
			  t.btnJogoDaVelha_11.setEnabled(true);
		  if(t.btnJogoDaVelha_12.getText().length() == 0)
			  t.btnJogoDaVelha_12.setEnabled(true);
		  if(t.btnJogoDaVelha_20.getText().length() == 0)
			  t.btnJogoDaVelha_20.setEnabled(true);
		  if(t.btnJogoDaVelha_21.getText().length() == 0)
			  t.btnJogoDaVelha_21.setEnabled(true);
		  if(t.btnJogoDaVelha_22.getText().length() == 0)
			  t.btnJogoDaVelha_22.setEnabled(true);
		  t.btnJogoDaVelha_Reiniciar.setEnabled(true);
	  }
   }
   
   public static boolean verificarResposta(String s){
   	  boolean resp = false;
	  String auxS[] = s.split(" ");
	  
	  for(int i = 0; i < auxS.length; i++) {
		  auxS[i].replace(" ", "");
	  }
	  
      if(s.length() == 8 && auxS[0].equals("BTN")) {
    	  if(auxS[1].equals("00")) {
              resp = true;
    	  } else if(auxS[1].equals("01")) {
    		  resp = true;
    	  } else if(auxS[1].equals("02")) {
    		  resp = true;
    	  } else if(auxS[1].equals("10")) {
    		  resp = true;
    	  } else if(auxS[1].equals("11")) {
    		  resp = true;
    	  } else if(auxS[1].equals("12")) {
    		  resp = true;
    	  } else if(auxS[1].equals("20")) {
    		  resp = true;
    	  } else if(auxS[1].equals("21")) {
    		  resp = true;
    	  } else if(auxS[1].equals("22")) {
    		  resp = true;
    	  }
      }
      return (resp);
   }
   
   public static void encerrarJogo(TelaPrincipal t){
      t.setVisible(false);
      t.dispose();
   }

   public static void main(String argv[]) throws Exception
   {
      String resposta = new String();
      DatagramSocket socketUDP = new DatagramSocket(minhaPorta);
      //InetAddress ipCliente = InetAddress.getByName(IPServidor);
      
      //inicia uma conexao TCP para garantir sincronismo com o cliente
      Socket conexao = new Socket(IPServidor, portaServidor);
      enviaMensagem(conexao, "INICIAR JOGO");
      System.out.println("(TCP)Enviei: INICIAR JOGO");
      resposta = recebeMensagem(conexao);
      System.out.println("(TCP)Recebi:"+resposta);

      TelaPrincipal telaDoJogo = TelaPrincipal.AlteraTela(argv);
      telaDoJogo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      telaDoJogo.jogador=jogador;
      
      //envio de jogadas via UDP
      do{
    	 bloqueiaDesbloqueiaBotoes(telaDoJogo, true);
    	 do {
          resposta=recebeMensagem(conexao);
     	 } while(resposta.length()==0);
         System.out.println("Recebi: " + resposta);

         if(resposta.equalsIgnoreCase("RESET")){
            resetTela(telaDoJogo);
            enviaMensagem(conexao, "RESET");
            System.out.println("Enviei: RESET");
         }
         else if(resposta.equalsIgnoreCase("fim do jogo")){
            encerrarJogo(telaDoJogo);
         }
         else if(telaDoJogo.isVisible() && verificarResposta(resposta)) {
            renderizar(telaDoJogo, resposta);
            bloqueiaDesbloqueiaBotoes(telaDoJogo, false);
            resposta="";
            while(resposta.equals("") && telaDoJogo.isVisible()){
               resposta=TelaPrincipal.resp;
               System.out.print("");//n??o remover pois ?? parte fundamental do c??digo (N??O ?? BRINCADEIRA)
            }
            
            if(telaDoJogo.isVisible()){
               if(!resposta.equalsIgnoreCase("RESET")) {
	        	  resposta+=jogador+"\n";
	           } else {
	        	  resposta+="\n";
	           }
               enviaMensagem(conexao, resposta);
               if(TelaPrincipal.resp.equalsIgnoreCase("RESET")){
                  resetTela(telaDoJogo);
               }
               System.out.println("Enviei: " + resposta);
            }
            
            TelaPrincipal.resp = "";
         }
         resposta = "";
      }while(telaDoJogo.isVisible());
      enviaMensagem(conexao, "fim do jogo");
      System.out.println("Enviei: fim do jogo");

      conexao.close();
      socketUDP.close();
      System.out.println("FIM DO PROGRAMA");
   }
   
}