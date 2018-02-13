
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20150326 (SVN rev 63)
//----------------------------------------------------

package achtml;

import java_cup.runtime.Symbol;
import proye1_compi2018.Nodo;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20150326 (SVN rev 63) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class AnalizadorSintactico_chtml extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return Simbolo.class;
}

  /** Default constructor. */
  @Deprecated
  public AnalizadorSintactico_chtml() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public AnalizadorSintactico_chtml(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintactico_chtml(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\022\000\002\002\004\000\002\002\004\000\002\003" +
    "\004\000\002\003\003\000\002\012\002\000\002\004\005" +
    "\000\002\013\002\000\002\004\005\000\002\007\004\000" +
    "\002\007\004\000\002\010\003\000\002\011\003\000\002" +
    "\011\006\000\002\005\004\000\002\005\003\000\002\006" +
    "\011\000\002\006\011\000\002\006\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\047\000\004\004\004\001\002\000\010\005\ufffe\006" +
    "\ufffe\010\ufffe\001\002\000\010\005\013\006\014\010\012" +
    "\001\002\000\004\002\007\001\002\000\004\002\001\001" +
    "\002\000\012\011\ufffd\012\034\014\033\024\031\001\002" +
    "\000\010\005\uffff\006\uffff\010\uffff\001\002\000\012\011" +
    "\ufff3\012\ufff3\014\ufff3\024\ufff3\001\002\000\004\002\000" +
    "\001\002\000\006\017\024\026\023\001\002\000\006\007" +
    "\ufffb\025\016\001\002\000\006\007\ufff7\025\ufff7\001\002" +
    "\000\006\007\ufff9\025\ufff9\001\002\000\004\007\021\001" +
    "\002\000\010\005\ufffa\006\ufffa\010\ufffa\001\002\000\006" +
    "\007\ufff8\025\ufff8\001\002\000\004\021\025\001\002\000" +
    "\006\007\ufff6\025\ufff6\001\002\000\004\022\026\001\002" +
    "\000\004\017\027\001\002\000\006\007\ufff5\025\ufff5\001" +
    "\002\000\012\011\ufff4\012\ufff4\014\ufff4\024\ufff4\001\002" +
    "\000\012\011\ufff0\012\ufff0\014\ufff0\024\ufff0\001\002\000" +
    "\004\011\051\001\002\000\004\020\043\001\002\000\004" +
    "\020\035\001\002\000\004\021\036\001\002\000\004\022" +
    "\037\001\002\000\004\016\040\001\002\000\004\017\041" +
    "\001\002\000\004\013\042\001\002\000\012\011\ufff2\012" +
    "\ufff2\014\ufff2\024\ufff2\001\002\000\004\021\044\001\002" +
    "\000\004\022\045\001\002\000\004\016\046\001\002\000" +
    "\004\017\047\001\002\000\004\015\050\001\002\000\012" +
    "\011\ufff1\012\ufff1\014\ufff1\024\ufff1\001\002\000\010\005" +
    "\ufffc\006\ufffc\010\ufffc\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\047\000\006\002\005\003\004\001\001\000\002\001" +
    "\001\000\010\004\010\005\007\007\014\001\001\000\002" +
    "\001\001\000\002\001\001\000\006\006\027\012\031\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\004\011\021\001\001\000\006\010\016\013\017\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalizadorSintactico_chtml$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalizadorSintactico_chtml$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalizadorSintactico_chtml$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



	public String resultado="";
	public Nodo padre;
	public int contador = 1;
    @Override
    public void syntax_error(Symbol s){
        System.out.println("Error Sintactico en la Linea " + (s.right+1) +" Columna "+s.left+ ". Identificador " +s.value + " no reconocido." );
    }

    @Override
    public void unrecovered_syntax_error(Symbol s){
        //System.out.println("Error Sintactico en la Linea " + (s.right+1)+ "Columna "+s.left+". Identificador " + s.value + " no reconocido.");
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$AnalizadorSintactico_chtml$actions {




  private final AnalizadorSintactico_chtml parser;

  /** Constructor */
  CUP$AnalizadorSintactico_chtml$actions(AnalizadorSintactico_chtml parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintactico_chtml$do_action_part00000000(
    int                        CUP$AnalizadorSintactico_chtml$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintactico_chtml$parser,
    java.util.Stack            CUP$AnalizadorSintactico_chtml$stack,
    int                        CUP$AnalizadorSintactico_chtml$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalizadorSintactico_chtml$result;

      /* select the action based on the action number */
      switch (CUP$AnalizadorSintactico_chtml$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INICIO EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		RESULT = start_val;
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalizadorSintactico_chtml$parser.done_parsing();
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INICIO ::= SENTENCIAS htmlf 
            {
              Object RESULT =null;
		int stsleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).left;
		int stsright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).right;
		Object sts = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		 
        parser.padre = (Nodo) sts;
        System.out.println("DOCUMENTO OK"); 
        
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("INICIO",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // SENTENCIAS ::= SENTENCIAS MEDIO 
            {
              Object RESULT =null;
		int stsleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).left;
		int stsright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).right;
		Object sts = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		int mdleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int mdright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object md = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
		 
               Nodo temp;
               temp = (Nodo) sts;
               temp.addHijo((Nodo)md);
               RESULT=temp;
            
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("SENTENCIAS",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // SENTENCIAS ::= htmli 
            {
              Object RESULT =null;
		
                Nodo tmp = new Nodo("INICIO");
		tmp.setNumNodo(parser.contador++); 
		RESULT=tmp;
            
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("SENTENCIAS",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // NT$0 ::= 
            {
              Object RESULT =null;
		int tmpleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int tmpright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object tmp = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
 
            RESULT=tmp;
    
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("NT$0",8, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // MEDIO ::= ENCABEZADO NT$0 encabezadof 
            {
              Object RESULT =null;
              // propagate RESULT from NT$0
                RESULT = (Object) ((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		int tmpleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)).left;
		int tmpright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)).right;
		Object tmp = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)).value;

              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("MEDIO",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // NT$1 ::= 
            {
              Object RESULT =null;
		int tmpleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int tmpright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object tmp = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
 
            RESULT=tmp;
        
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("NT$1",9, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // MEDIO ::= CUERPO NT$1 cuerpof 
            {
              Object RESULT =null;
              // propagate RESULT from NT$1
                RESULT = (Object) ((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		int tmpleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)).left;
		int tmpright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)).right;
		Object tmp = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)).value;

              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("MEDIO",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // CUERPO ::= CUERPO INT_CUERPO 
            {
              Object RESULT =null;
		int encleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).left;
		int encright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).right;
		Object enc = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		int incleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int incright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object inc = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
		 
               Nodo temp;
               temp = (Nodo) enc;
               temp.addHijo((Nodo)inc);
               RESULT=temp;
           
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("CUERPO",5, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // CUERPO ::= cuerpoi FON_CUERPO 
            {
              Object RESULT =null;
		int tmpleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int tmpright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object tmp = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
		 
            RESULT=tmp; 
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("CUERPO",5, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // INT_CUERPO ::= texto 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
		
                    Nodo tmp = new Nodo("texto");
                    String str = i.toString();
                    String ncadena=str.substring(7,str.length()-11);
                    tmp.setValor(ncadena);
                    tmp.setNumNodo(parser.contador++);
                    RESULT=tmp; 
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("INT_CUERPO",6, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // FON_CUERPO ::= cierre 
            {
              Object RESULT =null;
		
                     Nodo tmp = new Nodo("Cuerpo");
                     tmp.setValor("vacio");
                     tmp.setNumNodo(parser.contador++);
                     RESULT=tmp;
               
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("FON_CUERPO",7, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // FON_CUERPO ::= fondo igual cadena cierre 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		
                     Nodo tmp = new Nodo("Cuerpo");
                    String str = i.toString();
                    String ncadena=str.substring(1,str.length()-1);
                    tmp.setValor(ncadena);
                    tmp.setNumNodo(parser.contador++);
                    RESULT=tmp;
               
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("FON_CUERPO",7, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // ENCABEZADO ::= ENCABEZADO INT_ENCABEZADO 
            {
              Object RESULT =null;
		int encleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).left;
		int encright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).right;
		Object enc = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)).value;
		int incleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int incright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object inc = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
		 
               Nodo temp;
               temp = (Nodo) enc;
               temp.addHijo((Nodo)inc);
               RESULT=temp;
           
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("ENCABEZADO",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // ENCABEZADO ::= encabezadoi 
            {
              Object RESULT =null;
		 
                    Nodo tmp = new Nodo("encabezado");
                    tmp.setNumNodo(parser.contador++);
                    RESULT=tmp;
              
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("ENCABEZADO",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // INT_ENCABEZADO ::= cjsi ruta igual cadena ptc cierre cjsf 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-3)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-3)).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-3)).value;
		 
                    Nodo tmp = new Nodo("cjsi");
                    String str = i.toString();
                    String ncadena=str.substring(1,str.length()-1);
                    tmp.setValor(ncadena);
                    tmp.setNumNodo(parser.contador++);
                    RESULT=tmp;
              
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("INT_ENCABEZADO",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-6)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // INT_ENCABEZADO ::= ccssi ruta igual cadena ptc cierre ccssf 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-3)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-3)).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-3)).value;
		 
                    Nodo tmp = new Nodo("ccss");
                    String str = i.toString();
                    String ncadena=str.substring(1,str.length()-1);
                    tmp.setValor(ncadena);
                    tmp.setNumNodo(parser.contador++);
                    RESULT=tmp;
              
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("INT_ENCABEZADO",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.elementAt(CUP$AnalizadorSintactico_chtml$top-6)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // INT_ENCABEZADO ::= titulo 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico_chtml$stack.peek()).value;
		 Nodo tmp = new Nodo("titulo");
                    String str = i.toString();
                    String ncadena=str.substring(8,str.length()-12);
                    tmp.setValor(ncadena);
                    tmp.setNumNodo(parser.contador++);
                    RESULT=tmp; 
              CUP$AnalizadorSintactico_chtml$result = parser.getSymbolFactory().newSymbol("INT_ENCABEZADO",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico_chtml$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico_chtml$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$AnalizadorSintactico_chtml$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintactico_chtml$do_action(
    int                        CUP$AnalizadorSintactico_chtml$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintactico_chtml$parser,
    java.util.Stack            CUP$AnalizadorSintactico_chtml$stack,
    int                        CUP$AnalizadorSintactico_chtml$top)
    throws java.lang.Exception
    {
              return CUP$AnalizadorSintactico_chtml$do_action_part00000000(
                               CUP$AnalizadorSintactico_chtml$act_num,
                               CUP$AnalizadorSintactico_chtml$parser,
                               CUP$AnalizadorSintactico_chtml$stack,
                               CUP$AnalizadorSintactico_chtml$top);
    }
}

}