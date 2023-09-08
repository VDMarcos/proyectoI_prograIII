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
        instrumentos.presentation.Instrumentos.Model InstrumentosModel = new instrumentos.presentation.Instrumentos.Model();
        instrumentos.presentation.Instrumentos.View InstrumentosView = new instrumentos.presentation.Instrumentos.View();

        tiposController = new instrumentos.presentation.tipos.Controller(tiposView,tiposModel);
        InstrumentosController = new instrumentos.presentation.Instrumentos.Controller(InstrumentosView,InstrumentosModel);

        window.getContentPane().add("Tipos de Instrumento",tiposView.getPanel());
        window.getContentPane().add("Instrumentos",InstrumentosView.getPanel());

        window.setSize(900,450);
        window.setResizable(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        window.setTitle("SILAB: Sistema de Laboratorio Industrial");
        window.setVisible(true);
    }

    public static instrumentos.presentation.tipos.Controller tiposController;

    public static instrumentos.presentation.Instrumentos.Controller InstrumentosController;

    public static JFrame window;
}
