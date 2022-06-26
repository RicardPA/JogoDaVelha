import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaPrincipal extends JFrame {

	private JPanel TelaDoJogo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		TelaDoJogo = new JPanel();
		TelaDoJogo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(TelaDoJogo);
		
		JButton btnJogoDaVelha_00 = new JButton("");
		
		JButton btnJogoDaVelha_01 = new JButton("");
		
		JButton btnJogoDaVelha_02 = new JButton("");
		
		JButton btnJogoDaVelha_12 = new JButton("");
		
		JButton btnJogoDaVelha_11 = new JButton("");
		
		JButton btnJogoDaVelha_10 = new JButton("");
		
		JButton btnJogoDaVelha_22 = new JButton("");
		
		JButton btnJogoDaVelha_21 = new JButton("");
		
		JButton btnJogoDaVelha_20 = new JButton("");
		
		JButton btnJogoDaVelha_Reiniciar = new JButton("Reiniciar o jogo");
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
}
