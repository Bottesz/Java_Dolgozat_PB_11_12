package vezerlo;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modell.JatekModell;
import nezet.JatekNezet;

public class JatekVezerlo {

    private JatekNezet nezet;
    private JatekModell modell;
    private int korok = 0;
    private JButton[][] gombok;
    private Random rnd = new Random();

    public JatekVezerlo(JatekNezet nezet, JatekModell modell) {
        this.nezet = nezet;
        this.modell = modell;

        gombok = new JButton[][]{
            {nezet.getjButton2(), nezet.getjButton5(), nezet.getjButton8()},
            {nezet.getjButton3(), nezet.getjButton6(), nezet.getjButton9()},
            {nezet.getjButton4(), nezet.getjButton7(), nezet.getjButton10()}

        };

        nezet.getjButton1().addActionListener(e -> Indit());

        nezet.getFileMentesMenu().addActionListener(e -> FileMentes());
        nezet.getBetoltesMenu().addActionListener(e -> BetoltesMenu());
        nezet.getKilepesMenu().addActionListener(e -> KilepesMenu());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int sor = i;
                final int oszlop = j;
                gombok[i][j].addActionListener(e -> kattintas(sor, oszlop));
            }
        }
        nezet.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                kilepesMegerosites();
            }
        });

        frissit();

    }

    private void Indit() {
        modell.reset();

        java.util.Random rnd = new java.util.Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (rnd.nextBoolean()) {
                    modell.kapcsol(i, j);
                }
            }
        }

        korok = 0;
        nezet.getjTextField2().setText("0");
        nezet.getjTextField3().setText("");
        frissit();
    }

    private void kattintas(int sor, int oszlop) {
        modell.kapcsol(sor, oszlop);
        korok++;
        nezet.getjTextField2().setText(String.valueOf(korok));
        frissit();

        if (modell.nyert()) {
            String jatekos = nezet.getjTextField1().getText();
            nezet.getjTextField3().setText(jatekos);
            JOptionPane.showMessageDialog(nezet, "Gratulálok " + jatekos + ", nyertél!");
        }

    }

    private void frissit() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean allapot = modell.getErtek(i, j);
                gombok[i][j].setBackground(allapot ? Color.YELLOW : Color.DARK_GRAY);
            }
        }

    }

    private void kilepesMegerosites() {
        int valasz = JOptionPane.showConfirmDialog(
                nezet,
                "Biztosan ki szeretnél lépni a programból?",
                "Kilépés megerősítése",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (valasz == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void FileMentes() {
        try {
            JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
            jfc.setSelectedFile(new java.io.File("jatekallas.txt"));

            if (jfc.showSaveDialog(nezet) == JFileChooser.APPROVE_OPTION) {
                java.io.File file = jfc.getSelectedFile();
                StringBuilder sb = new StringBuilder();

                sb.append(korok).append("\n");

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        sb.append(modell.getErtek(i, j) ? "1" : "0");
                        if (j < 2) {
                            sb.append(" ");
                        }
                    }
                    sb.append("\n");
                }
                java.nio.file.Files.writeString(java.nio.file.Path.of(file.getAbsolutePath()), sb.toString());
                JOptionPane.showMessageDialog(nezet, "A Játék sikeresen mentve");

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(nezet, "Hiba a mentés során: " + ex.getMessage());
        }

    }

    
    
    private void BetoltesMenu() {
        JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
        int gomb = jfc.showOpenDialog(nezet);
        
        if (gomb == JFileChooser.APPROVE_OPTION) {
            java.io.File kivalasztottFajl = jfc.getSelectedFile();
            
            try {
                String tartalom = java.nio.file.Files.readString(kivalasztottFajl.toPath());
                System.out.println("Beolvasott tartalom:\n" + tartalom);
                
                
                String[] sorok = tartalom.strip().split("\\n");
                korok = Integer.parseInt(sorok[0]);
                nezet.getjTextField2().setText(String.valueOf(korok));
                
                modell.reset();
                for (int i = 0; i < 3; i++) {
                    String[] oszlopok = sorok[i + 1].trim().split(" ");
                    for (int j = 0; j < 3; j++) {
                        boolean allapot = oszlopok[j].equals("1");
                        if (allapot) {
                            modell.kapcsol(i,j);
                        }
                    }
                }
                
                frissit();
                JOptionPane.showMessageDialog(nezet, "A Játék sikeresen betöltve");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(nezet, "Hiba a játék betöltése során: " + ex.getMessage());
            }
        }
    }

    
    
    private void KilepesMenu() {
        String msg = "Biztosan ki szeretnél lépni?";
        String cim = "Kilépés megerősítése";
        int msgTip = JOptionPane.QUESTION_MESSAGE;
        int optTip = JOptionPane.YES_NO_OPTION;
        
        int gomb = JOptionPane.showConfirmDialog(nezet, msg, cim, optTip, msgTip);
        
        
        if (gomb == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
