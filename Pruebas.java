public class Pruebas {
    public static void main(String[] args) {
        Pruebas p = new Pruebas();
        p.particion("171");
    }
    //String bi = "171.25";
    
    public void conversion(){

    }

    public void particion(String bi){
        if(Double.parseDouble(bi)%1 == 0){
            //String entero = bi.substring(0, bi.indexOf(".")); //obtengo los numeros antes del punto
            String entero = bi;
            int ent = Integer.parseInt(entero);
            System.out.println(Integer.toBinaryString(ent)+".0");
        }else{
            String entero = bi.substring(0, bi.indexOf(".")); //obtengo los numeros antes del punto
            String decimal = bi.substring(bi.indexOf(".")); //los digitos despues del puntos decial
            binarioEnt(entero, decimal); // envio el digito por separado para convertirlos
        }
        
    }

    public void binarioEnt(String ent, String ded){
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
            if(dob-res > 0 || dob-res < 0){
                puntobi+="0";
            }else{
                if(dob-res ==0){
                    dob=-dob-res;
                    puntobi+="1";
                    fin = true;
                }
            }
            i++;
        }
        System.out.println(bi + puntobi);
    }
}
