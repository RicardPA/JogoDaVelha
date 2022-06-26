import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.WindowConstants;
//import javax.swing.text.html.parser.TagElement;

class Servidor
{
   private static int minhaPortaServidor = 6700;
   private static int portaCliente = 6500;//mudar? no cliente
   private static String jogador= "X";

   public static Scanner entrada = new Scanner(System.in);

   public static String recebeMensagem(Socket socketCliente){
      String resposta= new String();
      try {
         BufferedReader entrada =  new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
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

   public static void encerrarJogo(TelaPrincipal t){
      t.setVisible(false);
      t.dispose();
   }

   public static void main(String argv[]) throws Exception
   {
      String resposta = new String();
      ServerSocket socketTCP = new ServerSocket(minhaPortaServidor);
      DatagramSocket socketUDP = new DatagramSocket(minhaPortaServidor+1);
      //InetAddress ipCliente;
      
      //inicia uma conexao TCP para garantir sincronismo com o cliente
      System.out.println("estou esperando por uma conexao");
      Socket conexao = socketTCP.accept();
      //ipCliente= InetAddress.getByName(conexao.getInetAddress().getHostAddress());
      resposta = recebeMensagem(conexao);
      System.out.println("(TCP)Recebi: " + resposta);
      enviaMensagem(conexao, "INICIAR JOGO");
      System.out.println("(TCP)Enviei: INICIAR JOGO");

      TelaPrincipal telaDoJogo = TelaPrincipal.AlteraTela(argv);
      telaDoJogo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      telaDoJogo.jogador=jogador;

      TimeUnit.SECONDS.sleep(2);
      
      //envio de jogadas via UDP
      do{        
         resposta="";
         while(resposta.equals("") && telaDoJogo.isVisible()){
            resposta=TelaPrincipal.resp;
            System.out.print("");//não remover pois é parte fundamental do código (NÃO É BRINCADEIRA)
         }
         if(telaDoJogo.isVisible()){
        	if(!resposta.equalsIgnoreCase("RESET")) {
        		resposta+=jogador+"\n";
        	} else {
        		resposta+="\n";
        	}
            enviaMensagem(conexao, resposta);
            System.out.println("Enviei: "+ resposta);
         }
         
         TelaPrincipal.resp = "";
         
         if(telaDoJogo.isVisible()){
        	bloqueiaDesbloqueiaBotoes(telaDoJogo, true);
            resposta=recebeMensagem(conexao);
            bloqueiaDesbloqueiaBotoes(telaDoJogo, false);
            System.out.println("Recebi: " + resposta);
            
            if(resposta.equalsIgnoreCase("RESET")){
               resetTela(telaDoJogo);
            }
            else if(resposta.equalsIgnoreCase("fim do jogo")){
               encerrarJogo(telaDoJogo);
            }
            else{
               renderizar(telaDoJogo, resposta);
            }
         }
      }while(telaDoJogo.isVisible());
      enviaMensagem(conexao, "fim do jogo");
      System.out.println("Enviei: fim do jogo");

      conexao.close();
      socketTCP.close();
      socketUDP.close();

      System.out.println("FIM DO PROGRAMA");
      
   }
}