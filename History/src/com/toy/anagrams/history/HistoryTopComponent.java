/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toy.anagrams.history;

import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.WindowManager;
import sun.java2d.pipe.LoopBasedPipe;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//com.toy.anagrams.history//History//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "HistoryTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "com.toy.anagrams.history.HistoryTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_HistoryAction",
preferredID = "HistoryTopComponent")
@Messages({
    "CTL_HistoryAction=History",
    "CTL_HistoryTopComponent=History Window",
    "HINT_HistoryTopComponent=This is a History window"
})
public final class HistoryTopComponent extends TopComponent implements LookupListener {

    public HistoryTopComponent() {
        initComponents();
        setName(Bundle.CTL_HistoryTopComponent());
        setToolTipText(Bundle.HINT_HistoryTopComponent());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
    Lookup.Result<String> allStringsInGlobalContext;
    
    @Override
    public void componentOpened() {
        // register to the lookup of the global context
        allStringsInGlobalContext = Utilities.actionsGlobalContext().lookupResult(String.class);
        // register to the lookup if he AnagramEditorTopComponent
        allStringsInGlobalContext = WindowManager.getDefault().findTopComponent("AnagramEditorTopComponent").getLookup().lookupResult(String.class);
        
        allStringsInGlobalContext.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        allStringsInGlobalContext.removeLookupListener(this);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public void resultChanged(LookupEvent le) {
        jTextArea1.setText("");
        for (String s : allStringsInGlobalContext.allInstances()) {
            jTextArea1.append(s + "\n");
        }
    }
}
