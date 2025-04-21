import java.util.function.Function ;
import java.util.List;



public class Main{

    static Function<Integer, String> unidades = (n) -> {

            List<String> digitos = List.of("cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho","nueve");
            return digitos.get(n);
       
    };

    static Function<Integer, String> especiales = (n) -> {


        return List.of("diez", "once", "doce", "trece", "catorce", "quince", "dieciseis", "diecisiete", "deiciocho", "diecinueve", "veinte").get(n);

};


    public static void main(String[] args) {
        final Integer num = 14;
        if(num < 10)
        System.out.println(unidades.apply(num));
        else
        System.out.println(especiales.apply(num));
    }
}
