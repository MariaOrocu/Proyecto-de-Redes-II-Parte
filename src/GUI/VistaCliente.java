package GUI;

import data.UsuarioData;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import data.EjemplosVarios;
import java.nio.file.Files;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class VistaCliente extends javax.swing.JFrame implements Runnable {

    String b = "Bienvenido ";
    String r = "Ruta asignada: C:/redes/";
    Login login;
    InetAddress ip;
    DefaultListModel modelo;
    boolean seguirHilo = false;
    boolean hiloIniciado = false;
    Thread hilo;
    int cont = 0;
    private static Key key;

    /**
     * Creates new form VistaCliente
     */
    public VistaCliente() {
        initComponents();

        login = new Login();
        //String b = "Bienvenido ";
        lblBienvenido.setText(b + login.nombre);
        lblRuta.setText(r + login.nombre);
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(VistaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtIp.setText(ip.getHostAddress());
        modelo = UsuarioData.llenarArchivos(login.nombre);
        jList1.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSubir = new javax.swing.JButton();
        lblBienvenido = new javax.swing.JLabel();
        lblRuta = new javax.swing.JLabel();
        btnV = new javax.swing.JButton();
        btnServidor = new javax.swing.JButton();
        txtIp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnActualizar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingrese el archivo");

        btnSubir.setText("Subir Archivo");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        lblBienvenido.setText("Bienvenido");

        lblRuta.setText("Ruta asignada: \"C:\\redes\\\"");

        btnV.setText("Volver");
        btnV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVActionPerformed(evt);
            }
        });

        btnServidor.setText("Subir a Servidor");
        btnServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServidorActionPerformed(evt);
            }
        });

        jLabel2.setText("Ip de la máquina");

        jScrollPane1.setViewportView(jList1);

        btnActualizar.setText("Actualizar Carpeta cliente");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnParar.setText("Salir");
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 74, Short.MAX_VALUE)
                .addComponent(btnSubir)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnActualizar)
                            .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnServidor))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(btnParar)))))
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btnV))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblBienvenido)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBienvenido))
                        .addGap(98, 98, 98)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubir)
                    .addComponent(btnServidor)
                    .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnV)
                    .addComponent(btnActualizar)
                    .addComponent(btnParar))
                .addGap(66, 66, 66))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void enviarACarpeta(String nombreArchivo, String nombre) {

        try {
            EjemplosVarios varios = new EjemplosVarios();
            InetAddress ip = InetAddress.getByName(txtIp.getText());
            //txtIp.setText(ip.getHostAddress());
            // Creamos el Socket con la direccion y elpuerto de comunicacion
            Socket socket = new Socket(ip, 4400);
            socket.setSoTimeout(20000);
            socket.setKeepAlive(true);

            System.out.println("yu " + nombreArchivo);
            File file = new File(nombreArchivo);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(Files.readAllBytes(file.toPath()));
            int tamañoArchivo = (int) bytes.length;

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            System.out.println("Enviando Archivo: " + nombre);

            // Enviamos el nombre del archivo
            dos.writeChar('A');
            dos.writeUTF(nombre);
            dos.writeInt(tamañoArchivo);
            dos.writeUTF(login.nombre);
            //System.out.println("Enviando Archivo: " + tamañoArchivo);

            FileInputStream fis = new FileInputStream(nombreArchivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            // Creamos un array de tipo byte con el tamaño del archivo
            byte[] buffer = new byte[tamañoArchivo];

            bis.read(buffer);

            // Realizamos el envio de los bytes que conforman el archivo
            for (int i = 0; i < bytes.length; i++) {

                bos.write(bytes[i]);
            }
            System.out.println("Archivo Enviado: " + file.getName());
            bis.close();
            bos.close();
            dos.close();
            socket.close();
            modelo = UsuarioData.llenarArchivos(login.nombre);
            jList1.setModel(modelo);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void enviarAServidor(String nombreArchivo, String nombre) {

        try {

            InetAddress ip = InetAddress.getByName(txtIp.getText());

            // Creamos el Socket con la direccion y elpuerto de comunicacion
            Socket socket = new Socket(ip, 4400);
            socket.setSoTimeout(20000);
            socket.setKeepAlive(true);

            File file = new File(nombreArchivo);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(Files.readAllBytes(file.toPath()));
            int tamañoArchivo = (int) bytes.length;

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            System.out.println("Enviando Archivo: " + nombre);

            // Enviamos el nombre del archivo
            dos.writeChar('A');
            dos.writeUTF(nombre);
            dos.writeInt(tamañoArchivo);
            dos.writeUTF("servidor");
            System.out.println("Enviando Archivo: " + tamañoArchivo);

            FileInputStream fis = new FileInputStream(nombreArchivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

            // Creamos un array de tipo byte con el tamaño del archivo
            byte[] buffer = new byte[tamañoArchivo];

            bis.read(buffer);

            // Realizamos el envio de los bytes que conforman el archivo
            for (int i = 0; i < bytes.length; i++) {
                bos.write(bytes[i]);
            }

            System.out.println("Archivo Enviado: " + file.getName());
            bis.close();
            bos.close();
            socket.close();
            modelo = UsuarioData.llenarArchivos(login.nombre);
            jList1.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        JFileChooser fChooser = new JFileChooser();
        int respuesta = fChooser.showOpenDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {

            File archivoElegido = fChooser.getSelectedFile();
            System.out.println(archivoElegido.getAbsolutePath() + "&" + archivoElegido.getName());
            try {
                iniciarEncriptacion();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(VistaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            enviarACarpeta(archivoElegido.getAbsolutePath(), archivoElegido.getName());
    }//GEN-LAST:event_btnSubirActionPerformed
    }
    private void btnVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVActionPerformed
        System.out.println("aca");
        Login log = new Login();
        log.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVActionPerformed

    private void btnServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServidorActionPerformed
        JFileChooser fChooser = new JFileChooser();
        int respuesta = fChooser.showOpenDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {

            File archivoElegido = fChooser.getSelectedFile();
            System.out.println(archivoElegido.getAbsolutePath() + "&" + archivoElegido.getName());
            try {
                iniciarEncriptacion();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(VistaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            enviarAServidor(archivoElegido.getAbsolutePath(), archivoElegido.getName());
        }
    }//GEN-LAST:event_btnServidorActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        iniciarHilo();
        if (seguirHilo) {
            pararHilo(false);
        } else {
            pararHilo(true);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    public void iniciarHilo() {
        hilo = new Thread(this);
        hilo.start();
        hiloIniciado = true;
    }

    public void iniciarEncriptacion() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");

        generator.init(128);
        key = generator.generateKey();
        //obtiene los bytes del 0 a 15
        key = new SecretKeySpec("redes2JonatMaria".getBytes(), 0, 16, "AES");

    }

    /*método para parar el hilo*/
    public void pararHilo(boolean estado) {
        seguirHilo = estado;
    }
    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnPararActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCliente().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        EjemplosVarios varios = new EjemplosVarios();

        while (seguirHilo) {
            //System.out.println(cont + " :Hola mundo desde java usando hilos");
            cont++;
            try {
                varios.enviarEjemplos(login.nombre);
            } catch (IOException ex) {
                Logger.getLogger(VistaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnParar;
    private javax.swing.JButton btnServidor;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblRuta;
    public javax.swing.JTextField txtIp;
    // End of variables declaration//GEN-END:variables

}
