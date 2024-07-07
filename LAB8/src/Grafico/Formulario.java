package Grafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Logico.Estudiantes;
import Logico.Becas;
import Excepciones.IndiceExcepcion;
import Excepciones.NombreExcepcion;
import Excepciones.CarreraExcepcion;
import Excepciones.SexoExcepcion;
import Excepciones.CedulaExcepcion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Formulario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNombre;
    private JTextField textIndice;
    private JTextField textCedula;
    private Estudiantes estudiante;
    private JComboBox<String> comboBoxCarreras;
    private JComboBox<String> comboSexo;
    private List<Estudiantes> estudiantes;
    private Becas becas;

    public Formulario(Becas becas) {
        this.becas = becas;
        this.estudiantes = new ArrayList<>();
        initComponents();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Becas becas = new Becas();
                    Formulario frame = new Formulario(becas);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Sistema de becas para estudiantes");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(120, 10, 550, 62);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nombre:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(21, 97, 95, 22);
        contentPane.add(lblNewLabel_1);

        textNombre = new JTextField();
        textNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textNombre.setBounds(141, 102, 153, 19);
        contentPane.add(textNombre);
        textNombre.setColumns(10);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSexo.setBounds(21, 150, 95, 22);
        contentPane.add(lblSexo);

        comboSexo = new JComboBox<>();
        comboSexo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboSexo.setBounds(141, 150, 153, 25);
        comboSexo.addItem("Masculino");
        comboSexo.addItem("Femenino");
        comboSexo.addItem("Otro");
        contentPane.add(comboSexo);

        JLabel lblNewLabel_1_1 = new JLabel("Indice:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_1.setBounds(21, 239, 95, 22);
        contentPane.add(lblNewLabel_1_1);

        textIndice = new JTextField();
        textIndice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textIndice.setColumns(10);
        textIndice.setBounds(141, 240, 153, 19);
        contentPane.add(textIndice);

        JLabel lblNewLabel_1_1_1 = new JLabel("Cédula:");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_1_1.setBounds(21, 200, 95, 22);
        contentPane.add(lblNewLabel_1_1_1);

        textCedula = new JTextField();
        textCedula.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textCedula.setColumns(10);
        textCedula.setBounds(141, 202, 153, 19);
        contentPane.add(textCedula);

        JLabel lblNewLabel_1_1_2 = new JLabel("Carrera:");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_1_2.setBounds(21, 310, 95, 22);
        contentPane.add(lblNewLabel_1_1_2);

        comboBoxCarreras = new JComboBox<>();
        comboBoxCarreras.setModel(new DefaultComboBoxModel<>(new String[] {"Ingeniería Civil", "Ingeniería Eléctrica", "Ingeniería Industrial", "Ingeniería en Sistemas", "Ingeniería Mecánica", "Ingeniería Marítima"}));
        comboBoxCarreras.setBounds(141, 310, 153, 21);
        comboBoxCarreras.setSelectedIndex(-1);
        contentPane.add(comboBoxCarreras);

        JButton btnGuardar = new JButton("Guardar Datos");
        btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnGuardar.setBounds(460, 163, 184, 34);
        contentPane.add(btnGuardar);

        JButton btnReportes = new JButton("Mostrar Reportes");
        btnReportes.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnReportes.setBounds(460, 200, 184, 34);
        contentPane.add(btnReportes);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });

        btnReportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reportes reportes = new Reportes(estudiantes); 
                reportes.setVisible(true); 
            }
        });
    }

    private void guardarDatos() {
        try {
            String nombre = textNombre.getText();
            String cedula = textCedula.getText();
            double indice = Double.parseDouble(textIndice.getText());
            String carrera = (String) comboBoxCarreras.getSelectedItem();
            String sexo = (String) comboSexo.getSelectedItem();
          
            
            if (!nombre.matches("[a-zA-ZÑñáéíóúÁÉÍÓÚ]+")) {
                throw new NombreExcepcion("El nombre solo puede contener letras.");
            }
            
            if (!cedula.matches("[0-9-]+")) {
                throw new CedulaExcepcion("La cedula solo puede contener numeros y guiones");
            }
            
            if (sexo == null || sexo.isEmpty()) {
                throw new SexoExcepcion("Debe seleccionar su sexo");
            }

            if (indice < 0 || indice > 3) {
                throw new IndiceExcepcion("El índice debe estar entre 0 y 3.");
            }
            
            if (carrera == null || carrera.isEmpty()) {
                throw new CarreraExcepcion("Debe seleccionar una carrera.");
            }
            

            Estudiantes estudiante = new Estudiantes(nombre, cedula, carrera, sexo, indice);
            estudiantes.add(estudiante);
            becas.agregarEstudiante(estudiante);

            System.out.println("Información del estudiante guardada: " + estudiante.toString());

            JOptionPane.showMessageDialog(this, "Información guardada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            textNombre.setText("");
            textCedula.setText("");
            textIndice.setText("");
            comboBoxCarreras.setSelectedIndex(-1);
            comboSexo.setSelectedIndex(-1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese datos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IndiceExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NombreExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (CarreraExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (CedulaExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SexoExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

