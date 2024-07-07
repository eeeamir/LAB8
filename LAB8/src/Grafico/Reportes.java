package Grafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Logico.Becas;


public class Reportes extends JFrame {
	private JTextArea textAreaBecados;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Formulario formulario;
	
	public Reportes(Formulario formulario) {
        this.formulario = formulario;
        initComponents(); 
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setBounds(100, 100, 666, 483);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Reportes");
        lblNewLabel.setBounds(238, 10, 149, 46);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Estudiantes Becados:");
        lblNewLabel_1.setBounds(52, 106, 221, 35);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPane.add(lblNewLabel_1);

        textAreaBecados = new JTextArea(); 
        textAreaBecados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaBecados); 
        scrollPane.setBounds(52, 151, 550, 220); 
        contentPane.add(scrollPane); 

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnRegresar.setBounds(430, 395, 184, 34);
        contentPane.add(btnRegresar);

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                formulario.setVisible(true); 
            }
        });
    }

    public void mostrarBecados(Becas becas) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombres de los Estudiantes:\n");

        for (String nombre : becas.obtenerEstudiantesBecados()) {
            sb.append(nombre).append("\n");
        }

        textAreaBecados.setText(sb.toString());
    }
}