/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jonjts.assistant;

import br.com.jonjts.assistant.entity.Paciente;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Jonas
 */
public class RestorePacienteTela extends Main {

    private Main main;

    public RestorePacienteTela(final Main main) {
        super();
        this.main = main;
        initComponents();
        setTitle("Restaurar Paciente");

        getBtnCarregarPaciente().setVisible(false);
        getBtnNovoPaciente().setVisible(false);
        getBtnExcluir().setText("Restaurar");
        main.setEnabled(false);
        getMbAssistant().setVisible(false);

        search();
        setResizable(true);
        setSize(912, 653);
    }

    @Override
    protected void onClose() throws HeadlessException {
        main.setEnabled(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    protected void search() {
        try {
            List<Paciente> searchByName = getPacienteBO().searchByNameExcluidos(getTxtPesquisa().getText());
            popularTabela(searchByName);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void onSelectedExcluir() throws HeadlessException {
        int[] selectedRows = getTbPacientes().getSelectedRows();
        if (JOptionPane.showConfirmDialog(null, "Deseja restaurar " + selectedRows.length + " paciente(s)?") == JOptionPane.OK_OPTION) {
            for (int i = 0; i < selectedRows.length; i++) {
                try {
                    Long valueAt = (Long) getTbPacientes().getValueAt(selectedRows[i], 0);
                    getPacienteBO().restore(valueAt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao restaurar paciente");
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            search();
            main.search();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
