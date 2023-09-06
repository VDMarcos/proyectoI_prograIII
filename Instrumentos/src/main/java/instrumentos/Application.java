package instrumentos;

import javax.swing.*;

// Anner Andrés Angulo Gutierrez 504530978 y Marcos Emilio Vásquez Díaz 801530366

//combo box de la pestanna de Instrumentos, no construirla a mano, si no, con la lista de instrumentos obtener el nommbre y mostrar las opciones...

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {ex.printStackTrace();}
        window = new JFrame();
        window.setContentPane(new JTabbedPane());

        instrumentos.presentation.tipos.Model tiposModel= new instrumentos.presentation.tipos.Model();
        instrumentos.presentation.tipos.View tiposView = new instrumentos.presentation.tipos.View();
        tiposController = new instrumentos.presentation.tipos.Controller(tiposView,tiposModel);

        window.getContentPane().add("Tipos de Instrumento",tiposView.getPanel());

        window.setSize(900,450);
        window.setResizable(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage((new ImageIcon(Application.class.getResource("instumentosss/presentation/icons/icon.png"))).getImage());
        window.setTitle("SILAB: Sistema de Laboratorio Industrial");
        window.setVisible(true);
    }

    public static instrumentos.presentation.tipos.Controller tiposController;

    public static JFrame window;
}
