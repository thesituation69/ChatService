package ClientServer;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GUI extends JFrame {


    //FINESTRA 1
    JPanel panel_1 = new JPanel();
    JPanel panel_2 = new JPanel();
    JButton Reg_Button = new JButton("Registrati");
    JButton Acc_Button = new JButton("Accedi");

    JTextField JTF_id = new JTextField();
    JTextField JTF_username = new JTextField();
    JPasswordField JPF_password = new JPasswordField();

    //  JTextArea area = new JTextArea(15,20);    per i messaggi

    //Integer id;
    String id,username,password;
    ActorSystem system = ActorSystem.create("Attore_di_Sistema");
    //ActorSystem system2 = ActorSystem.create("Attore_di_Sistema2");
    ActorRef client = system.actorOf(Props.create(Server.class), "Client");
    ActorRef server = system.actorOf(Props.create(Client.class), "Server");
    JLabel id_Label = new JLabel("ID: ");
    JLabel username_Label = new JLabel("Username: ");
    JLabel password_Label = new JLabel("Password: ");


    //FINESTRA 2
    JButton NuovaChat = new JButton("Nuova Chat");
    JButton NuovoGruppo = new JButton("Nuovo Gruppo");
    JButton CancellaArchivio = new JButton("Cancella Archivio");
    JButton RicreaArchivio = new JButton("Ricrea Archivio");

    public static void main(String args[]) {

       /* Finestra1 frame = new Finestra1();
        frame.pack();
        */
        new GUI();

    }

    public GUI() {

        setTitle("Chat Service PA.RU");
        panel_1.setLayout(new GridLayout(6, 3));
        panel_1.add(id_Label);
        panel_1.add(JTF_id);
        panel_1.add(username_Label);
        panel_1.add(JTF_username);
        panel_1.add(password_Label);
        panel_1.add(JPF_password);
        panel_2.add(Reg_Button);
        panel_2.add(Acc_Button);

        getContentPane().add(panel_1, BorderLayout.NORTH);
        getContentPane().add(panel_2, BorderLayout.SOUTH);

        ListaEventi listaEventi = new ListaEventi();
        Reg_Button.addActionListener(listaEventi);
        Acc_Button.addActionListener(listaEventi);

        this.setLocation(600, 200);
        this.setSize(300, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(GUI.EXIT_ON_CLOSE);
    }


    public class ListaEventi implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Reg_Button) {  // caso registrati

                //System.out.println("Il path di Server è: " + server);
                //System.out.println("Il path di Client è: " + client);

                //id = Integer.valueOf(JTF_id.getText());
                id = JTF_id.getText();
                username = JTF_username.getText();
                password = JPF_password.getText();
                //System.out.println(+id + " " + username + " " + password);
                client.tell(new Server.Registrazione(id, username, password,server), ActorRef.noSender());
            }

            if (e.getSource() == Acc_Button) {



                //JOptionPane.showMessageDialog(null,provatext);
                new GUI2();

            }

            if (e.getSource() == NuovaChat) {

                System.out.println("Nuova Chat!");
            }

            if (e.getSource() == NuovoGruppo) {

                System.out.println("Nuovo Gruppo!");
            }

            if (e.getSource() == CancellaArchivio) {

                System.out.println("Archivio cancellato!");
            }

            if (e.getSource() == RicreaArchivio) {

                System.out.println("Archivio Ricreato!");
            }

        }
    }

    public class GUI2 extends JFrame {

        public GUI2() {
            setTitle("Lista Chat");

            JPanel panel_3 = new JPanel();
            panel_3.setLayout(new GridLayout(6, 3));
            panel_3.add(NuovaChat);
            panel_3.add(NuovoGruppo);
            panel_3.add(CancellaArchivio);
            panel_3.add(RicreaArchivio);

            getContentPane().add(panel_3, BorderLayout.CENTER);

            ListaEventi listaEventi = new ListaEventi();
            NuovaChat.addActionListener(listaEventi);
            NuovoGruppo.addActionListener(listaEventi);
            CancellaArchivio.addActionListener(listaEventi);
            RicreaArchivio.addActionListener(listaEventi);

            setLocation(500, 0);
            setSize(300, 200);
            setVisible(true);

            //setDefaultCloseOperation(GUI2.EXIT_ON_CLOSE);
        }
    }



}

