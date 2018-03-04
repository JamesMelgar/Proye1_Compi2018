
package proye1_compi2018;

import achtml.AnalizadorLexico_chtml;
import achtml.AnalizadorSintactico_chtml;
import cjs.AnalizadorLexico_cjs;
import cjs.AnalizadorSintactico_cjs;
import ccss.AnalizadorLexico_ccss;
import ccss.AnalizadorSintactico_ccss;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import acciones.reccorrer_html;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author James_PC
 */
public class navegador extends javax.swing.JPanel {

  AnalizadorLexico_chtml lexico_chtml;
  AnalizadorSintactico_chtml sintactico_chtml;
  AnalizadorLexico_cjs lexico_cjs;
  AnalizadorSintactico_cjs sintactico_cjs;
  AnalizadorLexico_ccss lexico_ccss;
  AnalizadorSintactico_ccss sintactico_ccss;
  int numero=0;
  javax.swing.JTabbedPane pestaña;
  String verror="";
  
  JPanel jp1, jp2, jp3;
  JButton jb1, jb2, jb3, jbP1, jbP2, jbP3;
  
    public navegador(int numero, javax.swing.JTabbedPane pestaña) {
        initComponents();
        this.numero=numero;
        this.pestaña = pestaña;
        panel_prueba.setLayout(null);
        panel_prueba.setPreferredSize(new Dimension(4000, 4000));
        panel_prueba.setAutoscrolls(true);
       // 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel_principal = new javax.swing.JPanel();
        bt_cargar = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        txt_ruta = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        bt_Atras = new javax.swing.JButton();
        bt_adelante = new javax.swing.JButton();
        panel_prueba = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_consola = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        label_error = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_errores = new javax.swing.JTextArea();
        Btchtml = new javax.swing.JButton();
        bt_cjs = new javax.swing.JToggleButton();
        bt_ccss = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_codigo = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();

        panel_principal.setBackground(new java.awt.Color(204, 255, 204));

        bt_cargar.setBackground(new java.awt.Color(255, 51, 51));
        bt_cargar.setIcon(new javax.swing.ImageIcon("C:\\Users\\James_PC\\Documents\\NetBeansProjects\\pr1compi2\\Proye1_Compi2018\\proye1_compi2018\\Iconos\\cargar.PNG")); // NOI18N
        bt_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cargarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Usac-web");

        txt_ruta.setText("C:\\Users\\James_PC\\Documents\\NetBeansProjects\\pr1compi2\\Proye1_Compi2018\\proye1_compi2018\\src\\Archi_entradas\\html_inicio.xml");

        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        bt_Atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proye1_compi2018/atras.PNG"))); // NOI18N
        bt_Atras.setToolTipText("");

        bt_adelante.setIcon(new javax.swing.ImageIcon("C:\\Users\\James_PC\\Documents\\NetBeansProjects\\pr1compi2\\Proye1_Compi2018\\proye1_compi2018\\Iconos\\adelante.PNG")); // NOI18N

        javax.swing.GroupLayout panel_pruebaLayout = new javax.swing.GroupLayout(panel_prueba);
        panel_prueba.setLayout(panel_pruebaLayout);
        panel_pruebaLayout.setHorizontalGroup(
            panel_pruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1159, Short.MAX_VALUE)
        );
        panel_pruebaLayout.setVerticalGroup(
            panel_pruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_principalLayout = new javax.swing.GroupLayout(panel_principal);
        panel_principal.setLayout(panel_principalLayout);
        panel_principalLayout.setHorizontalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principalLayout.createSequentialGroup()
                        .addComponent(panel_prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_principalLayout.createSequentialGroup()
                        .addComponent(bt_Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_adelante, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_principalLayout.createSequentialGroup()
                                .addGap(247, 247, 247)
                                .addComponent(jLabel1)
                                .addGap(746, 746, 746)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(3483, Short.MAX_VALUE))
                            .addGroup(panel_principalLayout.createSequentialGroup()
                                .addComponent(txt_ruta)
                                .addContainerGap())))))
        );
        panel_principalLayout.setVerticalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principalLayout.createSequentialGroup()
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(bt_Atras)
                    .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_adelante, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel_principalLayout.createSequentialGroup()
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(bt_cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_prueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(panel_principal);

        jTabbedPane1.addTab("Pagina", jScrollPane2);

        txt_consola.setColumns(20);
        txt_consola.setRows(5);
        jScrollPane3.setViewportView(txt_consola);

        jLabel2.setText("Consola");

        label_error.setText("Errores");

        txt_errores.setColumns(20);
        txt_errores.setRows(5);
        jScrollPane4.setViewportView(txt_errores);

        Btchtml.setText("Ver Codigo Html");

        bt_cjs.setText("Ver Codigo CJS");

        bt_ccss.setText("Ver Codigo CCSS");

        txt_codigo.setColumns(20);
        txt_codigo.setRows(5);
        jScrollPane5.setViewportView(txt_codigo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(Btchtml)
                                        .addGap(31, 31, 31)
                                        .addComponent(bt_cjs)
                                        .addGap(31, 31, 31)
                                        .addComponent(bt_ccss))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(label_error)))
                                .addGap(0, 872, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane3))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_error)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btchtml)
                    .addComponent(bt_cjs)
                    .addComponent(bt_ccss))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Opciones", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
//        crear_boton();
            graficar_html();
//        JPanel jfM = new JPanel();  
//        jfM.setLayout(null);
//        jfM.setBackground(Color.orange);
//        JButton boton;
//        setLayout(null);
//        boton=new JButton("SALIR");
//        boton.setBounds(100,30,100,30);
//        jfM.setLocation(0,0);
//        jfM.setVisible(true);
//        jfM.setSize(800, 600);
//        jfM.add(boton);
//        panel_prueba.add(jfM);
//        crear_boton();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    void crear_boton(){
        for (int i = 1; i < 5; i++) {
             JButton boton;
             setLayout(null);
             boton=new JButton("SALIR"+i);
             boton.setName("mundo"+i);
             boton.setBounds(i*100,30,100,30);
             
       panel_prueba.add(boton);
        }
      
    }
    
    public void gridJP(){
 
        jp1 = new JPanel(new GridLayout(3, 1, 5, 7));//filas, columnas, espacio entre filas, espacio entre columnas
 
        jb1= new JButton("B1"); jb2= new JButton("B2"); jb3= new JButton("B3");//creamos los objetos para el panel
 
        jp1.add(jb1); jp1.add(jb2); jp1.add(jb3);//añadimos los objetos al jpanel
 
        jb1.addActionListener((ActionListener) this); 
        jb2.addActionListener((ActionListener) this); 
        jb3.addActionListener((ActionListener) this);
 
        jp1.setVisible(true);
    }
 
    public void bordJP(){
        jp2 = new JPanel(new BorderLayout(2, 3));//espacio entre las regiones, horizontal y vertical
 
        jb1= new JButton("B1"); jb2= new JButton("B2"); jb3= new JButton("B3");//añadiendo objetos al jpanel
 
        jp2.add(jb1, BorderLayout.NORTH);//boton al panel norte
        jp2.add(jb2, BorderLayout.WEST); //boton a la region oeste
        jp2.add(jb3, BorderLayout.CENTER); //boton a la region centro    
 
        jb1.addActionListener((ActionListener) this); 
        jb2.addActionListener((ActionListener) this); 
        jb3.addActionListener((ActionListener) this);
 
        jp2.setVisible(true);
    }
 
    public void flowJP(){
        jp3 = new JPanel(new FlowLayout());
 
        jb1= new JButton("B1"); jb2= new JButton("B2"); jb3= new JButton("B3");//añadiendo objetos al jpanel
 
        jp3.add(jb1); jp3.add(jb2); jp3.add(jb3);//añadimos los objetos al jpanel
 
        jb1.addActionListener((ActionListener) this); 
        jb2.addActionListener((ActionListener) this);
        jb3.addActionListener((ActionListener) this);
 
        jp3.setVisible(true);
    }
    
    private void bt_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cargarActionPerformed
        String cambiar = "texto"+numero;
        txt_consola.setText("");
        // principal.
        try {
            String entrada;
            //entrada = this.txt_texto.getText();//tomamos el texto
            entrada = this.txt_ruta.getText();
            entrada = muestraContenido(entrada);
            Analizar_html(entrada);
        } catch (IOException ex) {
            Logger.getLogger(navegador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_cargarActionPerformed

    
        
    String muestraContenido(String archivo) throws FileNotFoundException, IOException {
      String cadena;
      String texto="";
      FileReader f = new FileReader(archivo);
      try (BufferedReader b = new BufferedReader(f)) {
          while((cadena = b.readLine())!=null) {
              texto = texto + "\n" +cadena; 
          }
      }
            return texto;
    }    
    public void Analizar_html(String entrada){
        lexico_chtml = new AnalizadorLexico_chtml(new BufferedReader( new StringReader(entrada)));
        sintactico_chtml = new AnalizadorSintactico_chtml(lexico_chtml);
            reccorrer_html reco = new reccorrer_html();
        try{ sintactico_chtml.parse();
        
            reco = new reccorrer_html(sintactico_chtml.padre, numero, pestaña, txt_consola, panel_prueba);
            //reccorrer_html.inicio_html(sintactico_chtml.padre);
        }catch(Exception ex){
            System.out.println("Error "+ex);  }
            verror = lexico_chtml.Cerror;
            verror = verror + sintactico_chtml.verror;
            verror = verror + reco.error_ccss;
            verror = verror + reco.error_cjs;
            txt_errores.setText(verror); 
    }
    
    public void graficar_html(){
        if(sintactico_chtml != null){
            if(sintactico_chtml.padre != null){
                graficar(sintactico_chtml.padre);
                System.out.println("Se ha graficado con exito");
            }
        }
        else
            System.out.println("No se puede graficar!!!");
    }
    
    public void Analizar_ccss(String entrada){
        lexico_ccss = new AnalizadorLexico_ccss(new BufferedReader( new StringReader(entrada)));
        sintactico_ccss = new AnalizadorSintactico_ccss(lexico_ccss);
        
        try{ sintactico_ccss.parse();
        }catch(Exception ex){
            System.out.println("Error "+ex);  }
    }
    
    public void graficar_ccss(){
        if(sintactico_ccss != null){
            if(sintactico_ccss.padre != null){
                graficar(sintactico_ccss.padre);
                System.out.println("Se ha graficado con exito");
            }
        }
        else
            System.out.println("No se puede graficar!!!");
    }
    
     public void Analizar_cjs(String entrada){
        lexico_cjs = new AnalizadorLexico_cjs(new BufferedReader( new StringReader(entrada)));
        sintactico_cjs = new AnalizadorSintactico_cjs(lexico_cjs);
        
        try{ sintactico_cjs.parse();
        }catch(Exception ex){
            System.out.println("Error "+ex);  }
    }
     
    public void graficar_cjs(){
        if(sintactico_cjs != null){
            if(sintactico_cjs.padre != null){
                graficar(sintactico_cjs.padre);
                System.out.println("Se ha graficado con exito");
            }
        }
        else
            System.out.println("No se puede graficar!!!");
    }
     
     public void graficar(Nodo raiz){
        FileWriter archivo = null;
        PrintWriter pw = null;
        String cadena = graficarNodo(raiz);
        
        try{
            archivo = new FileWriter("arbol.dot");
            pw = new PrintWriter(archivo);
            pw.println("digraph G {node[shape=box, style=filled, color=blanchedalmond]; edge[color=chocolate3];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            archivo.close();
        }catch (Exception e) {
            System.out.println(e +" 1");
        }
        
        try {
            String cmd = "dot -Tpng arbol.dot -o arbol.png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe +" 2");
        }
        
    }
    
    public String graficarNodo(Nodo nodo){
        String cadena = "";
        for(Nodo hijos : nodo.getHijos())
        {
            cadena += "\"" + nodo.getNumNodo() + "_" + nodo.getNombre() + "=" + nodo.getValor() + "\"->\"" + hijos.getNumNodo() + "_" + hijos.getNombre() + "=" + hijos.getValor() + "\"";
            cadena += graficarNodo(hijos);
        }
        return cadena;
    }
    
    
    public void actionPerformed(ActionEvent e, JButton boton ) {
        if (e.getSource()==boton) {
           // boton.setTitle("Saldrá en 3 segundos");
        try{
            Thread.sleep(3000);
            System.exit(0);
        } catch(Exception excep) {
        System.exit(0);
        }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btchtml;
    private javax.swing.JButton bt_Atras;
    private javax.swing.JButton bt_adelante;
    private javax.swing.JToggleButton bt_cargar;
    private javax.swing.JToggleButton bt_ccss;
    private javax.swing.JToggleButton bt_cjs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel label_error;
    private javax.swing.JPanel panel_principal;
    private javax.swing.JPanel panel_prueba;
    private javax.swing.JTextArea txt_codigo;
    private javax.swing.JTextArea txt_consola;
    private javax.swing.JTextArea txt_errores;
    private javax.swing.JTextField txt_ruta;
    // End of variables declaration//GEN-END:variables
}
