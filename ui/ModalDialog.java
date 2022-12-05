package ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Window;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class ModalDialog extends JDialog {

    public ModalDialog(Window w, String title, String message, String buttonText) {
        // utility class to make modal dialogs easier to use
        super(w, title, Dialog.ModalityType.DOCUMENT_MODAL);
        this.setSize(300, 300);
        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(new JLabel(message), BorderLayout.PAGE_START);
        if(buttonText == null) {
            return;
        }
        // modal close button
        JButton ok = new JButton(buttonText);
        ModalDialog thisDialog = this;
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisDialog.setVisible(false);
            }
        });
        // add the close button at the bottom of the modal
        JPanel p = new JPanel();
        p.add(ok);
        pane.add(p, BorderLayout.PAGE_END);
    }
    
}
