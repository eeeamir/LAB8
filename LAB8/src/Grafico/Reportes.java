package Grafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Logico.Becas;
import Logico.BuscarCedula;
import Logico.BuscarBecados;
import Logico.Estudiantes;

public class Reportes extends JFrame {
    private JTextArea textAreaBecados;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private List<Estudiantes> estudiantes;

    public Reportes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
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
            }
        });

  
        JButton btnBuscarCedula = new JButton("Buscar por Cédula");
        btnBuscarCedula.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnBuscarCedula.setBounds(52, 395, 184, 34);
        contentPane.add(btnBuscarCedula);

        btnBuscarCedula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = JOptionPane.showInputDialog("Ingrese la cédula:");
                BuscarCedula buscarCedula = new BuscarCedula(estudiantes);
                Estudiantes estudiante = buscarCedula.buscarPorCedula(cedula);
                if (estudiante != null) {
                    textAreaBecados.setText("Estudiante encontrado:\n" + estudiante.getNombre());
                } else {
                    textAreaBecados.setText("Estudiante no encontrado.");
                }
            }
        });

     
        JButton btnBuscarBecados = new JButton("Buscar Becados por Carrera y Sexo");
        btnBuscarBecados.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnBuscarBecados.setBounds(246, 395, 178, 34);
        contentPane.add(btnBuscarBecados);

        btnBuscarBecados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                String[] opcionesCarreras = {
                    "Ingeniería Civil", "Ingeniería Eléctrica", "Ingeniería Industrial",
                    "Ingeniería en Sistemas", "Ingeniería Mecánica", "Ingeniería Marítima"
                };
                JComboBox<String> comboCarreras = new JComboBox<>(opcionesCarreras);
                
               
                int inputCarrera = JOptionPane.showConfirmDialog(null, comboCarreras, "Seleccione la carrera:", JOptionPane.OK_CANCEL_OPTION);
                
                if (inputCarrera == JOptionPane.OK_OPTION) {
                    String carrera = (String) comboCarreras.getSelectedItem();
                    
                    
                    String[] opcionesSexo = {"Masculino", "Femenino", "Otro"};
                    JComboBox<String> comboSexo = new JComboBox<>(opcionesSexo);
                    
                    
                    int inputSexo = JOptionPane.showConfirmDialog(null, comboSexo, "Seleccione el sexo:", JOptionPane.OK_CANCEL_OPTION);
                    
                    if (inputSexo == JOptionPane.OK_OPTION) {
                        String sexo = (String) comboSexo.getSelectedItem();
                        
                        BuscarBecados buscarBecados = new BuscarBecados(estudiantes);
                        List<Estudiantes> becados = buscarBecados.buscarBecadosPorCarreraYSexo(carrera, sexo);
                        StringBuilder sb = new StringBuilder("Estudiantes Becados:\n");
                        for (Estudiantes becado : becados) {
                            sb.append(becado.getNombre()).append("\n");
                        }
                        textAreaBecados.setText(sb.toString());
                    }
                }
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
