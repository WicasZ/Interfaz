import javax.swing.JOptionPane;

public class trabajo1{
float nodecimal;
String [] puntoflotate;
String binario = "1010";
double de = 171.25;
int expo = 127; //numero de veces que avanza el punto decimal y debe ser sumado por 127
String[] opciones = {"Dec. a punto flotante", "Punto flotante a deci."};
    public trabajo1(){ //constructor
        menu();
    }

    public void star(int selec){
        
        if(selec == 0){
            String numero = "0";
            try {
                do {
                    numero = JOptionPane.showInputDialog(null, "Necesito el numero a convertir...", "Profe yo lo hice, encerio", JOptionPane.QUESTION_MESSAGE);
                } while (isNumber(numero));
                
                JOptionPane.showMessageDialog(null, dec_a_puntf(numero),"Profe yo lo hice, encerio", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ups, ocurrio algo "+e,"Profe yo lo hice, encerio", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            
        }
        if(selec == 1){
            String flotante = "0";
            try {
                do {
                    flotante = JOptionPane.showInputDialog(null, "Proporciona el valor punto flotante...", "Profe yo lo hice, encerio", JOptionPane.QUESTION_MESSAGE);
                    //dec_a_puntf();
                } while (isNumber(flotante) && (flotante.length()==32));//no agarra el ==32, no se porque...T_T
                System.out.println("Respuesta: " + flotante + "\nLa contidad es de: " + flotante + " :)".length());

                JOptionPane.showMessageDialog(null, "Su resultado en decimal es: \n"+divisor(flotante),"Profe yo lo hice, encerio", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ups, ocurrio algo "+e,"Profe yo lo hice, encerio", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }

    }

    public void menu(){ //ventana que pregunta como se que conversion desea
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de conversión", "Convertidor de decimal a flotante", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        star(seleccion);
    }

    public String dec_a_puntf(String bi){
        String signo;//digito para el tipo de signo
        String bin;
        String bitdecimal = "";
        if(Double.parseDouble(bi)<0){
            signo = "1";
            bi = bi.replace("-", "");
        }else{
            signo = "0";
        }
        bin = particion(bi);//se convierte el numero que da el usario en binario
        int punto = 0; //indice de la posicion del punto
        String expon = "";
        puntoflotate = new String[bin.length()];
        for(int j = 0; j<bin.length(); j++){
            if(bin.charAt(j) == '.'){ punto = j;}//System.out.println("Posicion del punto decimal "+ind);}
            puntoflotate[j] = bin.charAt(j) + "";
        }
        int induno = 0;//indice de la posicion del numero uno
        for(int c = 0; c<=puntoflotate.length; c++){
            if(!puntoflotate[c].equals(".")){
                if(Integer.parseInt(puntoflotate[c]) == 1){
                    int direccion = -1, ayuda = 1;
                    induno = c;
                    c = puntoflotate.length + 1;
                    String aux;
                    if(induno<punto || punto<induno){ //si el punto decimal no se encuantra antes del primero numero de la izquierda
                        int b = punto - induno;
                        //sav = puntoflotate[ind];
                        if(b<0){direccion=1; ayuda = 0;}
                        for(int t = (0+ayuda); t<(Math.abs(b)); t++){//metodo de busbuja para mover n cantidad de espacio
                            aux = puntoflotate[punto];
                            puntoflotate[punto] = puntoflotate[punto+direccion];
                            puntoflotate[punto+direccion] = aux;
                            punto+=direccion;
                            expo-=direccion;
                        }
                        for(int y = 0; y<puntoflotate.length; y++){
                        }
                        
                        expon = Integer.toBinaryString(expo);
                        while(expon.length()<8){ //ciclo que añade 0s si el convertidor no tiene los 8 bits
                            expon = "0" + expon;
                        };
                        bin = "";
                        for(int d = 0; d<puntoflotate.length; d++){//solo imprimo
                            bin += puntoflotate[d];
                        }
                        //AQUI!!
                        bitdecimal = bin.substring(bin.indexOf(".") + 1); //los digitos despues del puntos decial
                        while(bitdecimal.length()<23){ //ciclo que añade 0s si el convertidor no tiene los 8 bits
                            bitdecimal += "0";
                        };
                    }
                }
            }
        }
        return signo + expon + bitdecimal.substring(0,23);
    }

    public float puntf_a_dec(){
        return 0;
    }

    public String particion(String bi){
        if(Double.parseDouble(bi)%1 == 0){
            //String entero = bi.substring(0, bi.indexOf(".")); //obtengo los numeros antes del punto
            String entero = bi;
            int ent = Integer.parseInt(entero);
            return Integer.toBinaryString(ent)+".";
            //return ent + ".0";
        }else{
            String entero = bi.substring(0, bi.indexOf(".")); //obtengo los numeros antes del punto
            String decimal = bi.substring(bi.indexOf(".")); //los digitos despues del puntos decial
            return binarioEnt(entero, decimal); // envio el digito por separado para convertirlos
        }
        
    }

    public String binarioEnt(String ent, String ded){
        String puntobi = "."; //donde guardo el string binario de puntos decimal
        boolean fin = false; //variable para validar si se acaba de convertir todo el valor del puntos decial
        int i=1; //contador
        //Convierto en entreo en binario con una biblioteca de Java
        int ente = Integer.parseInt(ent);
        String bi = Integer.toBinaryString(ente);
        //Convierto el punto decimal en double para enviarlo al while
        Double dob = Double.parseDouble(ded);
        //ciclo con el cual convertimos el digito en binario
        while(fin == false){
            double res = Math.pow(2, -i);
            if((dob-res) > 0){
                puntobi+="1";
                dob=dob-res;
            }else{
                if(dob-res < 0){
                    puntobi+="0";
                }
            }
            if(dob-res == 0.0){
                dob=dob-res;
                puntobi+="1";
                fin = true;
            }
            i++;
            if(i>=23){fin = true;}
        }
        return (bi + puntobi + "");
    }

    public boolean isNumber(String men){//Solo lo uso para saber si el valor que me envian es un numerico
        try {
            Double.parseDouble(men);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public String divisor(String flotante){//en esta seccion divido el primer digito para saber el signo
        String signo = "";
        if(flotante.charAt(0) == 1){
            signo = "-";
        }
        flotante = flotante.substring(1,32);
        String bits31 = exponencial(flotante); //envio el resto a otro metodo
        return signo + bits31;//retorno un resultado
    }

    public String exponencial(String flotante31){ //en este metodo recogo los 8 digitos para obtener el exponente
        String exponencial = flotante31.substring(0,8);//extraigo los primeros ocho digitos y los elimio
        int valorexpo = Integer.parseInt(exponencial,2) - expo;//paso a decimal y le resto la constante 127
        //mando a llamar una tercera clase enviando la los ultimos 23 digitos y el valor de la exponente
        String resultado = matiza(flotante31.substring(8), valorexpo);
        return resultado;
    }

    public String matiza(String matiza, int exponecial){
        int direccion = 1;//lo uso para saber si el punto binario se movera a la izquierda o derecha
        String resultado = "1." + matiza;//añado un 1. a la matiza
        char[] mat = new char[resultado.length()];//creo una matriz para guardar y cambiar el orden, me servira para mover el punto decimal en una sola direccion (hacia la derecha)
        char aux;//para ayudarme hace el metodo de burbuja
        //creo un if para saber hacia donde ira el exponente
        if(exponecial<0){//si es menor quiere decir que se movera hacia la izquierda por lo que colocare 0´s las n veces que pide el exponente
            String ceros = "";
            for(int j = 1; j<Math.abs(exponecial); j++){//ciclo para hacerlo
                ceros += "0";
            }
            resultado = resultado.replace(".", "");//se me ocurrio en vez de hacer el metodo de burbuja solo elimino el punto y..
            resultado = "0."+ceros + resultado; // ... lo coloco al principio.
        }else{//si el valor es lo contrario...
            for(int j = 0; j<resultado.length(); j++){//creo ciclo for para guardar en el arreglo
                mat[j] = resultado.charAt(j);
            }
            for(int i = 1; i<=Math.abs(exponecial);i++){//realizo el metodo de burbuja para pocicionar el punto decimal las n veces que pide el exponenete
                aux = mat[i];
                mat[i] = mat[i+direccion];
                mat[i+direccion] = aux;
            }
            resultado = "";//elimino el valor que antes tenia mi string
            for(int f = 0; f<mat.length; f++){
                resultado += mat[f]; //y lo vuelvo a guardar..
            }
        }
        String entero = resultado.substring(0, resultado.indexOf(".")); //obtengo los numeros antes del punto
        String decimal = resultado.substring(resultado.indexOf(".")+1); //los digitos despues del puntos decial

        int valorentero = Integer.parseInt(entero, 2);//convierto el entero biario en decimal
        double valorpunto = 0;
        for(int b = 0; b<=decimal.length()-1; b++){ //ciclo para calcular el punto binario a punto decimal
            int v = Integer.parseInt(decimal.charAt(b)+"");
            valorpunto += v*Math.pow(2, -(b+1));
        }
        return valorentero + valorpunto + "";
    }

    public static void main(String[] args) {
        trabajo1 convertidor = new trabajo1();    
    }
}