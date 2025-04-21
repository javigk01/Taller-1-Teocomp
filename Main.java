import java.util.List;
import java.util.function.Function;

public class Main {

    static Function<Integer, String> unidades = (n) -> {
        List<String> digitos = List.of("cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve");
        return digitos.get(n);
    };

    static Function<Integer, String> especiales = (n) -> {
        return List.of("diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve").get(n - 10);
    };

    static Function<Integer, String> decenas = (n) -> {

    String resto = "";
    if(n % 10 != 0) {
    resto = " y " + unidades.apply(n % 10);
    }
        return List.of("", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa").get(n / 10) + resto;
    };

    public static void main(String[] args) {
        final Integer num = 34;

        if (num < 10) {
            System.out.println(unidades.apply(num));
        } else if (num >= 10 && num < 20) {
            System.out.println(especiales.apply(num));
        } else if (num >= 20 && num < 100) {
            System.out.println(decenas.apply(num));
        }
    }
}
