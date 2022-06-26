import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaPrincipal extends JFrame {
	String resp = "";
	
	JButton btnJogoDaVelha_00 = new JButton("");
	JButton btnJogoDaVelha_01 = new JButton("");
	JButton btnJogoDaVelha_02 = new JButton("");
	
	JButton btnJogoDaVelha_10 = new JButton("");
	JButton btnJogoDaVelha_11 = new JButton("");
	JButton btnJogoDaVelha_12 = new JButton("");
	
	JButton btnJogoDaVelha_20 = new JButton("");
	JButton btnJogoDaVelha_21 = new JButton("");
	JButton btnJogoDaVelha_22 = new JButton("");
	
	JButton btnJogoDaVelha_Reiniciar = new JButton("Reiniciar o jogo");
	
	private JPanel TelaDoJogo;

	public static TelaPrincipal AlteraTela(String[] args) {
		TelaPrincipal frame = new TelaPrincipal();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setResizable(false);
					System.out.println("OLA!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return(frame);
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		TelaDoJogo = new JPanel();
		TelaDoJogo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(TelaDoJogo);
		
		btnJogoDaVelha_00.addActionListener(new Jogada_00());
		btnJogoDaVelha_01.addActionListener(new Jogada_01());
		btnJogoDaVelha_02.addActionListener(new Jogada_02());
		
		btnJogoDaVelha_10.addActionListener(new Jogada_10());
		btnJogoDaVelha_11.addActionListener(new Jogada_11());
		btnJogoDaVelha_12.addActionListener(new Jogada_12());
		
		btnJogoDaVelha_20.addActionListener(new Jogada_20());
		btnJogoDaVelha_21.addActionListener(new Jogada_21());
		btnJogoDaVelha_22.addActionListener(new Jogada_22());
		
		btnJogoDaVelha_Reiniciar.addActionListener(new Resetar());
		
		GroupLayout gl_TelaDoJogo = new GroupLayout(TelaDoJogo);
		gl_TelaDoJogo.setHorizontalGroup(
			gl_TelaDoJogo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_TelaDoJogo.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addGroup(gl_TelaDoJogo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_TelaDoJogo.createSequentialGroup()
							.addComponent(btnJogoDaVelha_10, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnJogoDaVelha_11, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnJogoDaVelha_12, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_TelaDoJogo.createSequentialGroup()
							.addComponent(btnJogoDaVelha_00, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnJogoDaVelha_01, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnJogoDaVelha_02, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_TelaDoJogo.createSequentialGroup()
							.addComponent(btnJogoDaVelha_20, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnJogoDaVelha_21, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnJogoDaVelha_22, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnJogoDaVelha_Reiniciar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
					.addGap(104))
		);
		gl_TelaDoJogo.setVerticalGroup(
			gl_TelaDoJogo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TelaDoJogo.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_TelaDoJogo.createParallelGroup(Alignment.LEADING)
						.addComponent(btnJogoDaVelha_02, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJogoDaVelha_01, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJogoDaVelha_00, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_TelaDoJogo.createParallelGroup(Alignment.LEADING)
						.addComponent(btnJogoDaVelha_10, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJogoDaVelha_11, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJogoDaVelha_12, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_TelaDoJogo.createParallelGroup(Alignment.LEADING)
						.addComponent(btnJogoDaVelha_20, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJogoDaVelha_21, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJogoDaVelha_22, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addComponent(btnJogoDaVelha_Reiniciar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		TelaDoJogo.setLayout(gl_TelaDoJogo);
	}
	
	// Todas as jogadas possíveis
	class Jogada_00 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_00.setText("X");
			resp = "BTN 00 X\n";
		}	
	}
	
	class Jogada_01 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_01.setText("X");
			resp = "BTN 01 X\n";
		}	
	}
	
	class Jogada_02 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_02.setText("x");
			resp = "BTN 02 X\n";
		}	
	}
	
	class Jogada_10 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_10.setText("X");
			resp = "BTN 10 X\n";
		}	
	}
	
	class Jogada_11 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_11.setText("X");
			resp = "BTN 11 X\n";
		}	
	}
	
	class Jogada_12 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_12.setText("X");
			resp = "BTN 12 X";
		}	
	}
	
	class Jogada_20 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_20.setText("X");
			resp = "BTN 20 X\n";
		}	
	}
	
	class Jogada_21 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_21.setText("X");
			resp = "BTN 21 X\n";
		}	
	}
	
	class Jogada_22 implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_22.setText("X");
			resp = "BTN 22 X\n";
		}	
	}
	
	// Reiniciar o Jogo
	class Resetar implements ActionListener {
		public void actionPerformed(ActionEvent aev) {
			btnJogoDaVelha_00.setText("");
			btnJogoDaVelha_01.setText("");
			btnJogoDaVelha_02.setText("");
			
			btnJogoDaVelha_10.setText("");
			btnJogoDaVelha_11.setText("");
			btnJogoDaVelha_12.setText("");
			
			btnJogoDaVelha_20.setText("");
			btnJogoDaVelha_21.setText("");
			btnJogoDaVelha_22.setText("");
		}	
	}
}
