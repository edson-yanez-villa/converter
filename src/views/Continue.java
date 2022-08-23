package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Continue extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNo;
	private JButton btnYes;
	private JButton btnCancel;
	private Menu menuFrame;
	
	public Continue(Menu menu) {
		this.menuFrame = menu;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 125);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblcontinue = new JLabel("¿Desea continuar?");
		lblcontinue.setBounds(103, 11, 116, 14);
		contentPane.add(lblcontinue);
		
		btnYes = new JButton("Yes");
		btnYes.addActionListener(this);
		btnYes.setBounds(10, 46, 89, 23);
		contentPane.add(btnYes);
		
		btnNo = new JButton("No");
		btnNo.addActionListener(this);
		btnNo.setBounds(109, 46, 89, 23);
		contentPane.add(btnNo);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(208, 46, 89, 23);
		contentPane.add(btnCancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		if(e.getSource() == btnNo || e.getSource() == btnCancel){
			JOptionPane.showMessageDialog(null, "Programa Tarminado");
		}	
		if(e.getSource() == btnYes) {
			this.menuFrame.setVisible(true);
		}
	}

}
