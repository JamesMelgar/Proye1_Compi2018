
package acciones;
import proye1_compi2018.Nodo;
import achtml.AnalizadorLexico_chtml;
import achtml.AnalizadorSintactico_chtml;
import static achtml.Simbolo.ruta;
import com.sun.xml.internal.ws.client.ContentNegotiation;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.util.Locale;
import java.util.Map;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import proye1_compi2018.Complemento;
import proye1_compi2018.Tsimbolo;
import proye1_compi2018.listahtml;
import proye1_compi2018.principal;
/**
 * @author James_PC
 */

public class reccorrer_html {
    
     int numero=0;
     javax.swing.JTabbedPane pestaña;
     Analizadores analisis = new Analizadores();
     public static Nodo Raiz_ccss = new Nodo("CCSS");
     String verror="";
     public String error_ccss="";
     public String error_cjs="";
     javax.swing.JTextArea txt_consola;
     Tsimbolo global = new Tsimbolo("global","tabla de simbolos");
     int num_id = 0;
     JPanel panel;
     listahtml lista = new listahtml("Lista","");
     int lsalto = 0;
     
     
     public reccorrer_html(){
         
     }
     
     public reccorrer_html(Nodo html, int numero, javax.swing.JTabbedPane pestaña,
         javax.swing.JTextArea txt_consola, JPanel panel){
         this.numero = numero;
         this.pestaña=pestaña;
         this.txt_consola = txt_consola;
         this.panel = panel;
         panel.setLayout(null);
         inicio_html(html);         
     }
    
    public Nodo inicio_html(Nodo html){
        if(html.getHijos().size()==2){
            Nodo encabezado = html.getHijos().get(0);
            Nodo cuerpo = html.getHijos().get(1);
            Encabezado(encabezado);
            Complemento comp = revisar_complementos(cuerpo.getHijos().get(0));
            if(comp.isB_fondo()){
                Color color = obtener_color(comp.getFondo());
                panel.setBackground(color);
            }
            Cuerpo(cuerpo, panel, 0, 0);
        }else{
            System.out.println("Incorrecto");
        }
        return null;
    }
            
    public Nodo Encabezado(Nodo encabezado){
        //recorrer encabezado
        String texto="";
        for(Nodo hijo : encabezado.getHijos()){  
            switch(hijo.getNombre()){
                case "TITULO":
                    texto=hijo.getHijos().get(1).getValor();
                    pestaña.setTitleAt(numero,texto); 
                    break;
                case "CJS":
                    texto=hijo.getValor();
                    texto=analisis.muestratexto(texto); //bien
                    if(!"".equals(texto)){
                        Nodo cjs = analisis.Analizar_cjs(texto);
                        if(cjs != null){
                            imprimir(cjs.getNombre());
                            analisis.grafica_arbol("cjs", cjs);
                            recorrer_cjs cj = new recorrer_cjs(global, txt_consola, cjs);
                            error_cjs = error_cjs + cj.error_cjs;
                        }
                        error_cjs = error_cjs + analisis.error_cjs;
                    }else{
                        error_cjs = error_cjs + "Error en lectura de archivo cjs";
                    }
                    break;
                case "CCSS":
                    texto=hijo.getValor();
                    texto=analisis.muestratexto(texto);
                    if(!"".equals(texto)){
                        Nodo ccss ;
                        ccss = analisis.Analizar_ccss(texto);
                        
                        ccss_hijos(ccss);
                        
                        analisis.grafica_arbol("CCSS", Raiz_ccss);
                        error_ccss = error_ccss + analisis.error_ccss;
                    }
                    break;
                default:
                    System.out.println("D'");
                    break;
            }
        }
        return null;
    }
    
    public listahtml Cuerpo(Nodo cuerpo, JPanel panel, int ly, int lx){
        int nsal = 0, ntemp=0;
        listahtml nuevo;
        for(Nodo hijo : cuerpo.getHijos()){
            
            switch(hijo.getNombre()){
                case "PANEL":
                    nuevo = crear_panel(panel, hijo, ly, lx);
                    JPanel tpanel = (JPanel)nuevo.getInstancia();                   
                    ntemp = tpanel.getHeight();
                    lx = lx + tpanel.getWidth();
                    break;
                case "TITULO":
                    nuevo = crear_texto(panel, hijo, ly, lx);
                    JTextField label1 = (JTextField)nuevo.getInstancia();
                    ntemp = label1.getHeight();
                    lx = lx + label1.getWidth();
                    break;
                case "IMAGEN":
                    nuevo = Crear_imagen(panel, hijo, ly, lx);
                    JLabel etiqueta = (JLabel)nuevo.getInstancia();
                    ntemp = etiqueta.getHeight();
                    lx = lx + etiqueta.getWidth();
                    break;
                case "BOTON":
                    nuevo = Crear_boton(panel, hijo, ly, lx);
                    JButton boton = (JButton)nuevo.getInstancia();
                    ntemp = boton.getHeight();
                    lx = lx + boton.getWidth();
                    break;
                case "ENLACE":
                    nuevo = crear_enlace(panel, hijo, ly, lx);
                    JTextField label2 =  (JTextField)nuevo.getInstancia();
                    ntemp = label2.getHeight();
                    lx = lx + label2.getWidth();
                    break;
                case "TEXTOA":
                    nuevo = crear_Atexto(panel, hijo, ly, lx);
                    JTextPane jpanel1 = (JTextPane)nuevo.getInstancia();
                    ntemp = jpanel1.getHeight();
                    lx = lx + jpanel1.getWidth();
                    break;
                case "SALTO":
                    ly = ly + nsal;
                    lx = 0;
                    nsal = 0;
                    ntemp = 0;
                    break;
                case "SPINNER":
                    nuevo = crear_contador(panel, hijo, ly, lx);
                    JSpinner spinner = (JSpinner)nuevo.getInstancia();
                    ntemp = spinner.getHeight();
                    lx = lx + spinner.getWidth();
                    break;
                case "CAJA_TEXTO":
                    nuevo = crear_CAJA_TEXTO(panel, hijo, ly, lx);
                    JTextField label4= (JTextField)nuevo.getInstancia();
                    ntemp = label4.getHeight();
                    lx = lx + label4.getWidth();
                    break;
                case "CAJA":
                    nuevo = crear_caja(panel, hijo, ly, lx);
                    JComboBox combo = (JComboBox)nuevo.getInstancia();
                    ntemp  = combo.getHeight();
                    lx = lx + combo.getWidth();
                    break;
            }
            nsal = cambiar_laltura(nsal, ntemp);
        }
        return null;
    }
    
    public listahtml crear_panel(JPanel panel, Nodo raiz, int ly, int lx){
        int alto = 300; int ancho = 600;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        JPanel panel1 = new JPanel();  
        panel1.setLayout(null);
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }          
        listahtml nuevo = new listahtml(nombre,"JPANEL", panel1);
        
        nuevo.setId(comp.getId());
        
        nuevo.setGrupo(comp.getGrupo());
        panel1.setLocation(lx,ly);
        panel1.setVisible(true);
        panel1.setSize(ancho, alto);
        panel1.setSize(panel1.getPreferredSize());
        panel.add(panel1);
        
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
              panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
              panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        }
        
        
        buscar_panel_ccss(nuevo);
        Cuerpo(raiz, panel1,0, 0);
        return nuevo;
    }
    
    public listahtml crear_texto(JPanel panel, Nodo raiz, int ly, int lx){
        int alto = 50; int ancho = 50;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        JTextField label1 = new JTextField();
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            label1.setHorizontalAlignment(JTextField.LEFT);
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
             label1.setHorizontalAlignment(JTextField.RIGHT);
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
             label1.setHorizontalAlignment(JTextField.CENTER);
        }
        
        listahtml nuevo = new listahtml(nombre,"JTextField", label1);
        
        nuevo.setId(comp.getId());
        
        nuevo.setGrupo(comp.getGrupo());
        label1.setName(nombre);
        label1.setLocation(lx,ly);
        label1.setVisible(true); 
        label1.setText(raiz.getHijos().get(1).getValor());
        label1.setSize(label1.getPreferredSize());
        label1.setSize(ancho, alto);
        label1.setEditable(false);
        panel.add(label1);
        lx = lx + ancho;
        buscar_conjunto_ccss(nuevo, "JTEXT");
        return nuevo;
    }
        
    public Complemento revisar_complementos(Nodo raiz){
        Complemento comp =  new Complemento();
        for(Nodo hijos : raiz.getHijos()){
            String texto = hijos.getValor();
            switch(hijos.getNombre()){
                case "GRUPO":
                    comp.setGrupo(texto);
                    comp.setB_grupo(true);
                    break;
                  case "ALINEADO":
                    comp.setAlineado(texto);
                    comp.setB_ali(true);
                    break;
                case "FONDO":
                    comp.setFondo(texto);
                    comp.setB_fondo(true);
                    break;
                case "ID":
                    comp.setId(texto);
                    comp.setB_id(true);
                    break;
                case "ALTO":
                    comp.setAlto(Integer.parseInt(texto));
                    comp.setB_alto(true);
                    break;
                case "ANCHO":
                    comp.setAncho(Integer.parseInt(texto));
                    comp.setB_ancho(true);
                    break; 
                case "CLICK":
                    comp.setClick(texto);
                    break;
            }
        }
        return comp;
    }
    
    public listahtml Crear_imagen(JPanel panel, Nodo raiz, int ly, int lx){
        int alto = 50; int ancho = 50;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        ImageIcon imagen = new ImageIcon(raiz.getValor());
        JLabel etiqueta = new JLabel();
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            etiqueta.setHorizontalAlignment(JTextField.LEFT);
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
             etiqueta.setHorizontalAlignment(JTextField.RIGHT);
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
             etiqueta.setHorizontalAlignment(JTextField.CENTER);
        }
        
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(alto,ancho,Image.SCALE_DEFAULT));
        icono = new ImageIcon(imagen.getImage().getScaledInstance(ancho,alto,Image.SCALE_DEFAULT));
        etiqueta.setIcon(icono);
        
        listahtml nuevo = new listahtml(nombre,"IMAGEN", etiqueta);
        nuevo.setGrupo(comp.getGrupo());
        nuevo.setId(comp.getId());
        
        etiqueta.setName(nombre);
        etiqueta.setLocation(lx,ly);
        etiqueta.setVisible(true); 
        etiqueta.setSize(etiqueta.getPreferredSize());
        etiqueta.setSize(ancho, alto);
        panel.add(etiqueta);
        lx = lx + ancho;
        buscar_conjunto_ccss(nuevo, "JLabel");  
        return nuevo;
        }
    
    public listahtml Crear_boton(JPanel panel, Nodo raiz, int ly, int lx)
    {
        int alto = 50; int ancho = 100;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        JButton boton1;
        boton1=new JButton(raiz.getValor());
        
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            boton1.setHorizontalAlignment(JTextField.LEFT);
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
             boton1.setHorizontalAlignment(JTextField.RIGHT);
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
             boton1.setHorizontalAlignment(JTextField.CENTER);
        }
        
        listahtml nuevo = new listahtml(nombre,"Jbutton", boton1);
        nuevo.setGrupo(comp.getGrupo());
        nuevo.setId(comp.getId());
        String click = replasar_funcion(comp.getClick());
        nuevo.setFuncion(click);
        
        boton1.setName(nombre);
        boton1.setLocation(lx,ly);
        boton1.setVisible(true); 
        boton1.setSize(boton1.getPreferredSize());
        boton1.setSize(ancho, alto);
        panel.add(boton1);
        
        boton1.addActionListener((ae) -> {
            revisar_si_tiene_funcion(nuevo);

       });
        buscar_conjunto_ccss(nuevo, "JBUTTON");       
        return nuevo;
    }
    
    public listahtml revisar_si_tiene_funcion(listahtml nuevo){
        if(nuevo.getFuncion().equalsIgnoreCase("")==false){
            JOptionPane.showMessageDialog(null, nuevo.getFuncion(), "Soy un Mensaje",
                 JOptionPane.WARNING_MESSAGE);
            recorrer_cjs js ;
            for( global.
        }
        return null;
    }
    
    String replasar_funcion(String texto){
        String click = texto.replace("(", "");
        click = click.replace(")", "");
        click = click.replace(" ", "");
        return click;
    }
    
    public listahtml crear_enlace(JPanel panel, Nodo raiz, int ly, int lx){
        int alto = 100; int ancho = 100;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        JTextField label1 = new JTextField();
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            label1.setHorizontalAlignment(JTextField.LEFT);
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
             label1.setHorizontalAlignment(JTextField.RIGHT);
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
             label1.setHorizontalAlignment(JTextField.CENTER);
        }
        
        listahtml nuevo = new listahtml(nombre,"ENLACE", label1);
        nuevo.setId(comp.getId());
        
        nuevo.setGrupo(comp.getGrupo());
        label1.setName(nombre);
        label1.setLocation(lx,ly);
        label1.setVisible(true); 
        label1.setText(raiz.getValor());
        label1.setSize(label1.getPreferredSize());
        label1.setSize(ancho, alto);
        label1.setEditable(false);
        label1.setForeground(Color.BLUE);
        Font font = label1.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label1.setFont(font.deriveFont(attributes));
        panel.add(label1);
        lx = lx + ancho;
        buscar_conjunto_ccss(nuevo, "JTEXT");
        return nuevo;
    }
       
    public listahtml crear_Atexto(JPanel panel, Nodo raiz, int ly, int lx){
        int alto = 100; int ancho = 100;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        JTextPane label1 = new JTextPane();
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        StyledDocument doc = label1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
             StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }
        
        listahtml nuevo = new listahtml(nombre,"ATEXTO", label1);
        nuevo.setId(comp.getId());
        nuevo.setGrupo(comp.getGrupo());
        label1.setName(nombre);
        label1.setLocation(lx,ly);
        label1.setVisible(true); 
        label1.setText(raiz.getHijos().get(1).getValor());
        label1.setSize(label1.getPreferredSize());
        label1.setSize(ancho, alto);
        label1.setEditable(true);
        panel.add(label1);
        buscar_conjunto_ccss(nuevo, "JTextPane");
        
        lx = lx + ancho;
        
        return nuevo;
    }
    
    public void recorrer_css(Nodo temp){
        for(Nodo hijo : temp.getHijos()){  
            System.out.println(hijo.getNombre());
        }
    }
    
    public void ccss_hijos(Nodo temp){
        for(Nodo hijo : temp.getHijos()){  
            Raiz_ccss.addHijo(hijo);
        }
    }
    
    public void imprimir(String texto){
        System.out.println(texto);
    }
    
    public listahtml crear_contador(JPanel panel, Nodo raiz, int ly, int lx){
        int alto = 40; int ancho = 40;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        
        JSpinner spinner = new JSpinner();
        
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            spinner.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
            spinner.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
           spinner.setComponentOrientation(ComponentOrientation.UNKNOWN);
        }           
                
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        
        listahtml nuevo = new listahtml(nombre,"spinner", spinner);
        nuevo.setId(comp.getId());
        nuevo.setGrupo(comp.getGrupo());
        spinner.setName(nombre);
        spinner.setLocation(lx,ly);
        spinner.setVisible(true); 
        if(isNumeric(raiz.getValor())){
            int valor =  Integer.parseInt(raiz.getValor());
            spinner.setValue(valor);
        }else{
            spinner.setValue(0);
        } 
        spinner.setSize(spinner.getPreferredSize());
        spinner.setSize(ancho, alto);
        buscar_conjunto_ccss(nuevo, "JSpinner");
        panel.add(spinner);    
        return nuevo;
    }
    
    public int cambiar_laltura(int alto, int temp){
        if( alto < temp){
            alto = temp;
        }
        return alto;
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    public listahtml crear_CAJA_TEXTO(JPanel panel, Nodo raiz, int ly, int lx){
        int alto = 100; int ancho = 200;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));
        JTextField label1 = new JTextField();
        
        label1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                c = ' ';
                e.setKeyChar(c);
               // e.consume();
            }
            }
        });
        
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            label1.setHorizontalAlignment(JTextField.LEFT);
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
             label1.setHorizontalAlignment(JTextField.RIGHT);
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
             label1.setHorizontalAlignment(JTextField.CENTER);
        }
        
        listahtml nuevo = new listahtml(nombre,"JTextField", label1);
        nuevo.setId(comp.getId());
        nuevo.setGrupo(comp.getGrupo());
        label1.setName(nombre);
        label1.setLocation(lx,ly);
        label1.setVisible(true); 
        label1.setText(raiz.getValor());
        label1.setSize(label1.getPreferredSize());
        label1.setSize(ancho, alto);
        //label1.setEditable(true);
        panel.add(label1);
        lx = lx + ancho;
        buscar_conjunto_ccss(nuevo, "JTEXT");
        return nuevo;
    }
      
    public listahtml crear_caja(JPanel panel, Nodo raiz, int ly, int lx){
	int alto = 100; int ancho = 200;
        String nombre = "ID_"+num_id;
        num_id = num_id + 1;
        Complemento comp = revisar_complementos(raiz.getHijos().get(0));	
        // Creacion del JComboBox y añadir los items.
        JComboBox combo = new JComboBox();
	combo.addItem("uno");
	combo.addItem("dos");
	combo.addItem("tres");

        // Accion a realizar cuando el JComboBox cambia de item seleccionado.
	combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==combo) {
                imprimir(combo.getSelectedIndex()+" - "+combo.getSelectedItem());
            }
            }
	});
        
        if(comp.isB_alto()){
            alto = comp.getAlto(); }
        if(comp.isB_ancho()){
            ancho = comp.getAncho(); }
        if(comp.getAlineado().equalsIgnoreCase("IZQUIERDA")){
            //combo.setLayout(new BoxLayout(combo, BoxLayout.Y_AXIS));
            combo.setLayout(new FlowLayout(FlowLayout.RIGHT));
        }else if(comp.getAlineado().equalsIgnoreCase("DERECHA")){
             combo.setLayout(new FlowLayout(FlowLayout.LEFT));
        }else if(comp.getAlineado().equalsIgnoreCase("CENTRADO")){
            combo.setLayout(new FlowLayout(FlowLayout.CENTER));
        }
                
       listahtml nuevo = new listahtml(nombre,"CAJA_OPCION", combo);
        nuevo.setNombre(raiz.getValor());
        nuevo.setGrupo(comp.getGrupo());
        combo.setName(nombre);
        combo.setLocation(lx,ly);
        combo.setVisible(true); 
        combo.setSize(combo.getPreferredSize());
        combo.setSize(ancho, alto);
        //label1.setEditable(true);
        panel.add(combo);
        lx = lx + ancho;

        return nuevo;
    }

    public listahtml buscar_panel_ccss(listahtml buscar){
        //imprimir("grupo: "+buscar.getGrupo()+" IDS:"+buscar.getId()+" "+buscar.getNombre());
        for(Nodo DEFINICION : Raiz_ccss.getHijos()){
            for(Nodo grupo : DEFINICION.getHijos()){
                if(grupo.getNombre().equalsIgnoreCase("GRUPO") &&
                        grupo.getValor().equalsIgnoreCase(buscar.getGrupo()) ||
                            grupo.getNombre().equalsIgnoreCase("IDS") &&
                                grupo.getValor().equalsIgnoreCase(buscar.getId())){
                                acciones_panel_ccss(buscar, grupo);
                }   
            }        
        }
        return null;
    }    
    
    public listahtml acciones_panel_ccss(listahtml buscar, Nodo raiz){
        
        for(Nodo hijo : raiz.getHijos()){
            switch(hijo.getNombre()){
                case "ALINEADO":
                    acciones_alineado(buscar, "PANEL", hijo);
                    break;
                case "FONDOELEMENTO":
                    color_panel(buscar, hijo);
                    break;
                case "BORDE":
                    acciones_borde(buscar, "PANEL",  hijo);
                    break;
                case "OPAQUE":
                    acciones_opaque(buscar,"PANEL",hijo);
                    break;
                case "AUTO":
                    acciones_auto(buscar,"PANEL",hijo);
                    break;
            }
        }
        return null;
    }
    
    public listahtml color_panel(listahtml buscar, Nodo nodo){
        JPanel panel1 = (JPanel)buscar.getInstancia();
        Color color = obtener_color(nodo.getValor());
        panel1.setBackground(color);
        return null;
    }
    
    public void Hay_error(String error){
        error_cjs = error_cjs + " Tipo de errror semantico: "+error+"\n"; 
    }
    
    public void imprimir_nodo(Nodo imp){
        System.out.println("Nombre:"+imp.getNombre()+" Valor:"+imp.getValor());
    }
    
    public Color obtener_color(String nombre){
        Color color;
        try{
            color = Color.decode(nombre);
        }catch(NumberFormatException ex){
            if(nombre.equalsIgnoreCase("white")){
                color = Color.white;
            }else if(nombre.equalsIgnoreCase("lightGray")){
                color = Color.lightGray;
            }else if(nombre.equalsIgnoreCase("gray")){
                color = Color.gray;
            }else if(nombre.equalsIgnoreCase("drakGray")){
                color = Color.darkGray;
            }else if(nombre.equalsIgnoreCase("black")){
                color = Color.black;
            }else if(nombre.equalsIgnoreCase("red")){
                color = Color.red;
            }else if(nombre.equalsIgnoreCase("pink")){
                color = Color.pink;
            }else if(nombre.equalsIgnoreCase("orange")){
                color = Color.orange;
            }else if(nombre.equalsIgnoreCase("yellow")){
               color = Color.yellow;
            }else if(nombre.equalsIgnoreCase("green")){
               color = Color.green;
            }else if(nombre.equalsIgnoreCase("magenta")){
                color = Color.magenta;
            }else if(nombre.equalsIgnoreCase("cyan")){
                color = Color.cyan;
            }else if(nombre.equalsIgnoreCase("blue")){
                color = Color.blue;
            }else if(nombre.equalsIgnoreCase("DARK_GRAY")){
                color = Color.DARK_GRAY;
            }else{
                color = Color.green;
                Hay_error("El color no exite");
            }       
        }
        return color;
    }
    
    public listahtml acciones_auto(listahtml buscar, String tipo,Nodo nodo){
        Nodo hijo = nodo.getHijos().get(0);
        String hori =hijo.getValor();
        String valor = hijo.getNombre();
        boolean bool = Boolean.parseBoolean(valor);
        switch (tipo){
            case "PANEL":
                JPanel panel1 = (JPanel)buscar.getInstancia();
                //add(box, BorderLayout.CENTER);
                break;
        }
        return null;
    }
    
    public listahtml buscar_conjunto_ccss(listahtml buscar, String tipo ){
        //imprimir("grupo: "+buscar.getGrupo()+" IDS:"+buscar.getId()+" "+buscar.getNombre());
        for(Nodo DEFINICION : Raiz_ccss.getHijos()){
            for(Nodo grupo : DEFINICION.getHijos()){
                if(grupo.getNombre().equalsIgnoreCase("GRUPO") &&
                        grupo.getValor().equalsIgnoreCase(buscar.getGrupo()) ||
                            grupo.getNombre().equalsIgnoreCase("IDS") &&
                                grupo.getValor().equalsIgnoreCase(buscar.getId())){
                                acciones_conjunto_ccss(buscar, grupo, tipo);
                }   
            }        
        }
        return null;
    }    
    
    public listahtml acciones_conjunto_ccss(listahtml buscar, Nodo raiz, String tipo){        
        for(Nodo hijo : raiz.getHijos()){
            switch(hijo.getNombre()){
                case "ALINEADO":
                    acciones_alineado(buscar, tipo, hijo);
                    break;
                case "VISIBLE":
                    visible_objeto(buscar, tipo, hijo);
                    break;
                case "FONDOELEMENTO":
                    color_objeto(buscar, tipo, hijo);
                    break;
                case "BORDE":
                    acciones_borde(buscar, tipo,  hijo);
                    break;
                case "OPAQUE":
                    acciones_opaque(buscar,tipo,hijo);
                    break;
                case "AUTO":
                    acciones_auto(buscar,tipo,hijo);
                    break;
                case "TEXTO":
                    acciones_Texto(buscar, tipo, hijo);
                    break;
                case "FORMATO":
                    casos_formato(buscar, tipo, hijo);
                    break;
                case "LETRA":
                    acciones_letra(buscar, tipo, hijo);
                    break;
                case "TAMTEX":
                    acciones_TamTex(buscar, tipo, hijo);
                    break;
                case "COLORTEXT":
                    color_Text_objeto(buscar, tipo, hijo);
                    break;
            }
        }
        return null;
    } 
    
    public Tsimbolo expresiones(Nodo raiz, boolean estado){
        Tsimbolo temp = new Tsimbolo("", "");
        switch(raiz.getHijos().size()){
            case 2:
                Tsimbolo izq = expresiones(raiz.getHijos().get(0), estado);
                Tsimbolo der = expresiones(raiz.getHijos().get(1), estado);
                switch(raiz.getNombre()){
                    case "SUMA":                          
                        return casos_sumas(izq, der);
                    case "RESTA":
                        return casos_RESTA(izq, der);
                    case "MULTIPLICACION":
                        return casos_multiplicacion(izq, der);
                    case "DIVISION":
                        return casos_division(izq, der);
                }
            case 0:
                switch(raiz.getNombre()){
                    case "NUMERO":
                        temp.setTipo_valor("NUMERO");
                        temp.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        imprimir_nodo(raiz);
                        return temp;
                    case "CADENA":
                        Tsimbolo temp1 = new Tsimbolo("","");
                        temp1.setTipo_valor("CADENA");
                        temp1.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        return temp1;
                    case "BOOL":
                        Tsimbolo temp4 = new Tsimbolo("","");
                        temp4.setTipo_valor("BOOL");
                        temp4.setValor(raiz.getValor());
                        temp.setTipo("VARIABLE");
                        return temp4;
                }
        }
        temp.setTipo_valor("ERROR");
        return temp; 
    }
    
    public Tsimbolo casos_sumas(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 = Double.parseDouble(izq.getValor());
                   n2 = Double.parseDouble(der.getValor());
                   n3 = n1 + n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 =  Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 + n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    b3 = b1 || b2;
                    temp.setValor(bol_str(b3));
                    temp.setTipo_valor("BOOL");
                    return temp;
                 case "NUMERO":
                    n1 =  Double.parseDouble(der.getValor());
                    b2 = Boolean.parseBoolean(izq.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 + n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                 case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("CADENA")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                 case "NUMERO":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                 case "CADENA":
                    v1 = izq.getValor();
                    v2 = der.getValor();
                    v3 = v1 + v2;
                    temp.setValor(v3);
                    temp.setTipo_valor("CADENA");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp; 
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
     
    public Double bol_int(boolean valor){
        if(valor){
            return 1.0;
        }else{
            return 0.0;
        } 
    }
    
    public String  bol_str(boolean valor){
         if(valor){
            return "true";
        }else{
            return "false";
        }
    }
     
    int bol_int_str(String valor){
        if(valor.equalsIgnoreCase("TRUE")){
            return 1;
        } return 0;
    }
    
    public Tsimbolo casos_RESTA(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   n3 = n1 - n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 =  Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 - n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = n1 - n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Tsimbolo casos_multiplicacion(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        String v1, v2, v3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 =  Double.parseDouble(izq.getValor());
                   n2 =  Double.parseDouble(der.getValor());
                   n3 = n1 * n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 * n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                case "BOOL":
                    b1 = Boolean.parseBoolean(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    b3 = b1 && b2;                 
                    temp.setValor(bol_str(b3));
                    temp.setTipo_valor("BOOL");
                    return temp;
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = n1 * n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Tsimbolo casos_division(Tsimbolo izq, Tsimbolo der){
        double n1 , n2 , n3;
        boolean b1, b2, b3;
        Tsimbolo temp = new Tsimbolo("", "");
        if(izq.getTipo_valor().equalsIgnoreCase("NUMERO")){
            switch(der.getTipo_valor()){
                case "NUMERO":
                   n1 = Double.parseDouble(izq.getValor());
                   n2 = Double.parseDouble(der.getValor());
                   n3 = n1 / n2;
                   temp.setValor(String.valueOf(n3));
                   temp.setTipo_valor("NUMERO");
                   return temp;
                case "BOOL":
                    n1 = Double.parseDouble(izq.getValor());
                    b2 = Boolean.parseBoolean(der.getValor());
                    n2 = bol_int(b2);
                    n3 = n1 / n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;
            }
        }else if(izq.getTipo_valor().equalsIgnoreCase("BOOL")){
            switch(der.getTipo_valor()){
                 case "NUMERO":
                    n1 = bol_int_str(izq.getValor());
                    n2 = Double.parseDouble(der.getValor());
                    n3 = n1 / n2;
                    temp.setValor(String.valueOf(n3));
                    temp.setTipo_valor("NUMERO");
                    return temp;
                default:
                    temp.setTipo_valor("ERROR");
                    return temp;     
            }
        }else{
            temp.setTipo_valor("ERROR");
            return temp; 
        }
    }
    
    public Tsimbolo casos_formato(listahtml buscar,String tipo,Nodo nodo){
        Tsimbolo nue = expresiones(nodo.getHijos().get(0), true);
        String letra;
        int num;
        switch (tipo){
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();
                letra = label1.getFont().getFontName();
                num = label1.getFont().getSize();
                for(Nodo hijo : nodo.getHijos()){
            switch(hijo.getNombre()){
                case "NEGRILLA":
                    Font negrita = new Font(letra,Font.BOLD , num);
                    label1.setFont(negrita);
                    break;
                case "MAYUSCULA":
                    label1.setText(label1.getText().toUpperCase());
                    break;
                case "MINUSCULA":
                    label1.setText(label1.getText().toLowerCase());
                    break;
                case "CAPITAL":
                    String cap = label1.getText();
                    label1.setText(capita_t(cap));
                    break;
                case "CURSIVA":
                    Font cursiva = new Font(letra,Font.ITALIC , num);
                    label1.setFont(cursiva);
                    break;
                    }
                } break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                letra = button.getFont().getFontName();
                num = button.getFont().getSize();
                for(Nodo hijo : nodo.getHijos()){
            switch(hijo.getNombre()){
                case "NEGRILLA":
                    Font negrita = new Font(letra,Font.BOLD , num);
                    button.setFont(negrita);
                    break;
                case "MAYUSCULA":
                    button.setText(button.getText().toUpperCase());
                    break;
                case "MINUSCULA":
                    button.setText(button.getText().toLowerCase());
                    break;
                case "CAPITAL":
                    String cap = button.getText();
                    button.setText(capita_t(cap));
                    break;
                case "CURSIVA":
                    Font cursiva = new Font(letra,Font.ITALIC , num);
                    button.setFont(cursiva);
                    break;
                    }
                } break;
                
        } 
        return null;
    }
     
    public String capita_t(String cadena){
        char[] caracteres = cadena.toCharArray();
        caracteres[0] = Character.toUpperCase(caracteres[0]);

            // el -2 es para evitar una excepción al caernos del arreglo
        for (int i = 0; i < cadena.length()- 2; i++) 
            // Es 'palabra'
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
                // Reemplazamos
            caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
 
            return new String(caracteres);
        }
    
    public listahtml acciones_Texto(listahtml buscar,String tipo,Nodo nodo){
        Tsimbolo nue;
        switch (tipo){
            case "JTEXT":
                nue = expresiones(nodo.getHijos().get(0), true);
                JTextField label1 = (JTextField)buscar.getInstancia();
                if(nue.getTipo_valor().equalsIgnoreCase("ERROR")){
                    Hay_error("Expresion incorrecta");
                }else{
                    label1.setText(nue.getValor());
                }
                break;
            case "JBUTTON":
                nue = expresiones(nodo.getHijos().get(0), true);
                JButton button = (JButton)buscar.getInstancia();
                if(nue.getTipo_valor().equalsIgnoreCase("ERROR")){
                    Hay_error("Expresion incorrecta");
                }else{
                    button.setText(nue.getValor());
                }
                break;
            case "JTextPane":
                nue = expresiones(nodo.getHijos().get(0), true);
                JTextPane text = (JTextPane)buscar.getInstancia();
                if(nue.getTipo_valor().equalsIgnoreCase("ERROR")){
                    Hay_error("Expresion incorrecta");
                }else{
                    text.setText(nue.getValor());
                }
                break;
            
        }
        return null;
    }
    
    public listahtml acciones_letra(listahtml buscar,String tipo,Nodo nodo){
        int num, n1;
        String letra;
        switch (tipo){
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();
                letra = nodo.getValor();
                num = label1.getFont().getSize();
                n1 = label1.getFont().getStyle();
                try{
                    Font fon = new Font(letra,n1, num);
                    label1.setFont(fon);
                }catch(Exception ex){
                    Hay_error("No existe tipo de letra");
                }break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                letra = nodo.getValor();
                num = button.getFont().getSize();
                n1 = button.getFont().getStyle();
                try{
                    Font fon = new Font(letra,n1, num);
                    button.setFont(fon);
                }catch(Exception ex){
                    Hay_error("No existe tipo de letra");
                }
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                letra = nodo.getValor();
                num = text.getFont().getSize();
                n1 = text.getFont().getStyle();
                try{
                    Font fon = new Font(letra,n1, num);
                    text.setFont(fon);
                }catch(Exception ex){
                    Hay_error("No existe tipo de letra");
                }break;
        }
        return null;
    }
    
    public listahtml acciones_TamTex(listahtml buscar,String tipo,Nodo nodo){
        int num , n1;
        String letra;
        Font fon ;
        switch (tipo){
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();
                letra = label1.getFont().getFontName();
                num = Integer.parseInt(nodo.getValor());
                n1 = label1.getFont().getStyle();
                fon = new Font(letra,n1, num);
                label1.setFont(fon);
                break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                letra =  button.getFont().getFontName();
                num = Integer.parseInt(nodo.getValor());
                n1 =  button.getFont().getStyle();
                fon = new Font(letra,n1, num);
                button.setFont(fon);
                break;
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                letra =  text.getFont().getFontName();
                num = Integer.parseInt(nodo.getValor());
                n1 =  text.getFont().getStyle();
                fon = new Font(letra,n1, num);
                text.setFont(fon);
                break;               
        }
        return null;
    }
    
    public listahtml color_objeto(listahtml buscar,String tipo ,Nodo nodo){
        Color color = obtener_color(nodo.getValor());
        switch (tipo){
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();             
                label1.setBackground(color);
                break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                button.setBackground(color);
                break;
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                text.setBackground(color);
                break;
        }
        return null;
    }
    
     public listahtml visible_objeto(listahtml buscar,String tipo ,Nodo nodo){
        switch (tipo){
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("TRUE")){
                    label1.setVisible(true);
                }else{
                    label1.setVisible(false);
                }
                break;
             case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                 if(nodo.getValor().equalsIgnoreCase("TRUE")){
                    button.setVisible(true);
                }else{
                    button.setVisible(false);
                }
                break;
             case "JLabel":
                JLabel etiqueta = (JLabel)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("TRUE")){
                    etiqueta.setVisible(true);
                }else{
                    etiqueta.setVisible(false);
                }
                break;
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("TRUE")){
                    text.setVisible(true);
                }else{
                    text.setVisible(false);
                }
                break;
            case "JSpinner":
                JSpinner spinner = (JSpinner)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("TRUE")){
                    spinner.setVisible(true);
                }else{
                    spinner.setVisible(false);
                }
                break;
        }
        return null;
    }
     
     public listahtml color_Text_objeto(listahtml buscar,String tipo ,Nodo nodo){
        Color color = obtener_color(nodo.getValor());
        switch (tipo){
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();             
                label1.setForeground(color);
                break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                button.setForeground(color);
                break;
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                text.setForeground(color);
                break;
        }
        return null;
    }
     
     public listahtml acciones_opaque(listahtml buscar, String tipo,Nodo nodo){
        String valor = nodo.getValor();
        boolean bool = Boolean.parseBoolean(valor);
        switch (tipo){
            case "PANEL":
                JPanel panel1 = (JPanel)buscar.getInstancia();
                panel1.setOpaque(bool);
                break;
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();
                label1.setOpaque(bool);
                break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                button.setOpaque(bool);
                break;
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                text.setOpaque(bool);
                break;
            case "JSpinner":
                JSpinner spinner = (JSpinner)buscar.getInstancia();
                spinner.setOpaque(bool);
                break;
        }
        return null;
    }
    
    public listahtml acciones_borde(listahtml buscar,String tipo,Nodo nodo){
        String tcurva = nodo.getHijos().get(2).getValor();
        String tcolor = nodo.getHijos().get(1).getValor();
        String tamano = nodo.getHijos().get(0).getValor();
        Color color = obtener_color(tcolor);
        int num = Integer.parseInt(tamano);
        boolean bool = Boolean.parseBoolean(tcurva);
        javax.swing.border.Border borde1 = BorderFactory.createLoweredBevelBorder();
        javax.swing.border.Border borde2 =BorderFactory.createLineBorder(color, num, bool);
        switch (tipo){
            case "PANEL":
                JPanel panel1 = (JPanel)buscar.getInstancia();               
                panel1.setBorder(BorderFactory.createCompoundBorder(borde2, borde1));
                break;
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();
                label1.setBorder(BorderFactory.createCompoundBorder(borde2, borde1));
                break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                button.setBorder(BorderFactory.createCompoundBorder(borde2, borde1));
                break;
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                text.setBorder(BorderFactory.createCompoundBorder(borde2, borde1));
                break;
        }
        return null;
    }
         
    public listahtml acciones_alineado(listahtml buscar,String tipo,Nodo nodo){
        switch (tipo){
            case "PANEL":
                JPanel panel1 = (JPanel)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("IZQUIERDA")){
                    panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
                }else if(nodo.getValor().equalsIgnoreCase("DERECHA")){
                    panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
                }else if(nodo.getValor().equalsIgnoreCase("CENTRADO")){
                    panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
                }else if(nodo.getValor().equalsIgnoreCase("JUSTIFICADO")){
                    panel1.setLayout(new FlowLayout(FlowLayout.TRAILING));
                }
                break;
            case "JTEXT":
                JTextField label1 = (JTextField)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("IZQUIERDA")){
                    label1.setHorizontalAlignment(JTextField.LEFT);
                }else if(nodo.getValor().equalsIgnoreCase("DERECHA")){
                    label1.setHorizontalAlignment(JTextField.RIGHT);
                }else if(nodo.getValor().equalsIgnoreCase("CENTRADO")){
                    label1.setHorizontalAlignment(JTextField.CENTER);
                }else if(nodo.getValor().equalsIgnoreCase("JUSTIFICADO")){
                    label1.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                }
                break;
            case "JBUTTON":
                JButton button = (JButton)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("IZQUIERDA")){
                    button.setHorizontalAlignment(JTextField.LEFT);
                }else if(nodo.getValor().equalsIgnoreCase("DERECHA")){
                    button.setHorizontalAlignment(JTextField.RIGHT);
                }else if(nodo.getValor().equalsIgnoreCase("CENTRADO")){
                    button.setHorizontalAlignment(JTextField.CENTER);
                }else if(nodo.getValor().equalsIgnoreCase("JUSTIFICADO")){
                    button.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                }
                break;
            case "JLabel":
                JLabel etiqueta = (JLabel)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("IZQUIERDA")){
                    etiqueta.setHorizontalAlignment(JTextField.LEFT);
                }else if(nodo.getValor().equalsIgnoreCase("DERECHA")){
                   etiqueta.setHorizontalAlignment(JTextField.RIGHT);
                }else if(nodo.getValor().equalsIgnoreCase("CENTRADO")){
                    etiqueta.setHorizontalAlignment(JTextField.CENTER);
                }else if(nodo.getValor().equalsIgnoreCase("JUSTIFICADO")){
                    etiqueta.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                }
                break;
            case "JTextPane":
                JTextPane text = (JTextPane)buscar.getInstancia();
                StyledDocument doc = text.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                if(nodo.getValor().equalsIgnoreCase("IZQUIERDA")){
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                }else if(nodo.getValor().equalsIgnoreCase("DERECHA")){
                   StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                }else if(nodo.getValor().equalsIgnoreCase("CENTRADO")){
                   StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
                }else if(nodo.getValor().equalsIgnoreCase("JUSTIFICADO")){
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                } break;
            case "JSpinner":
                JSpinner spinner = (JSpinner)buscar.getInstancia();
                if(nodo.getValor().equalsIgnoreCase("IZQUIERDA")){
                    spinner.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                }else if(nodo.getValor().equalsIgnoreCase("DERECHA")){
                    spinner.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                }else{
                    spinner.setComponentOrientation(ComponentOrientation.UNKNOWN);
                }    
                break;
        }
        return null;
    }
    
    // JOptionPane.showMessageDialog(null, exp.getValor(), "Soy un Mensaje", JOptionPane.WARNING_MESSAGE);
    
    
}
