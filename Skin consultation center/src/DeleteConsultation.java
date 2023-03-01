import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class DeleteConsultation extends JFrame{
    private WestminsterSkinConsultationManager manager;

    public DeleteConsultation(WestminsterSkinConsultationManager manager) {
        this.manager = manager;

        JPanel panel = new JPanel(new GridLayout(1,2,0,0));
        panel.setBounds(100,100,300,50);
        panel.setBackground(new Color(50, 162, 135));
        JLabel name = new JLabel("Patient Name:");
        name.setForeground(Color.BLACK);
        panel.add(name);

        String[] choices = new String[manager.getConsultations().size()];
        for (int i = 0; i < manager.getConsultations().size(); i++) {
            choices[i] = manager.getConsultations().get(i).getPatient().getName();
        }
        JComboBox<String> consultName = new JComboBox<String>(choices);
        panel.add(consultName);

        JButton deleteCons = new JButton("Submit");
        deleteCons.setBounds(200,200,80,30);
        deleteCons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Consultation c:
                     manager.getConsultations()) {
                    if (c.getPatient().getName().equals(Objects.requireNonNull(consultName.getSelectedItem()).toString())){
                        manager.removePatient(c.getPatient());
                        manager.removeConsultation(c);
                        JOptionPane.showMessageDialog(null, c.getPatient().getName()+" has been Deleted from the List!");
                        try {
                            StartingFrame startFrame = new StartingFrame(manager);
                            setVisible(false);
                        } catch (IOException ex) {
                            System.out.println(ex);;
                        }
                    }
                }
                for (Patient p:
                        manager.getPatients()) {
                    if (p.getName().equals(consultName.getSelectedItem().toString())){
                        manager.removePatient(p);
                    }
                }
            }
        });
        add(panel);
        add(deleteCons);

        setResizable(false);
        setLayout(null);
        setTitle("Delete Consultation");
        setSize(600,300);
        getContentPane().setBackground(new Color(50, 162, 135));
        setVisible(true);


    }
}
