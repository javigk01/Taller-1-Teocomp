import java.util.function.Function;
import java.util.List;

public class Main {

    static Function<Integer, String> unidades = (n) -> {
        List<String> digitos = List.of("cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve");
        return digitos.get(n);
    };

    static Function<Integer, String> especiales = (n) -> {
        return List.of("diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve").get(n - 10);
    };

    static Function<Integer, String> decenas = (n) -> {
        List<String> decenasList = List.of("diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa");
        if (n < 10) {
            return unidades.apply(n);
        } else if (n < 20) {
            return especiales.apply(n);
        } else {
            int decena = n / 10;
            int unidad = n % 10;
            if (unidad == 0) {
                return decenasList.get(decena - 1);
            } else {
                return decenasList.get(decena - 1) + " y " + unidades.apply(unidad);
            }
        }
    };

    static Function<Integer, String> centenas = (n) -> {
        List<String> centenasList = List.of(
                "", "ciento", "doscientos", "trescientos", "cuatrocientos",
                "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"
        );

        if (n == 100) return "cien";

        int centena = n / 100;
        int resto = n % 100;

        String resultado = "";
        if (centena > 0) {
            resultado += centenasList.get(centena);
            if (resto > 0) {
                resultado += " ";
            }
        }

        if (resto > 0) {
            if (resto < 10) {
                resultado += unidades.apply(resto);
            } else {
                resultado += decenas.apply(resto);
            }
        }

        return resultado;
    };

    public static void main(String[] args) {
        final Integer num = 345;

        if (num < 10) {
            System.out.println(unidades.apply(num));
        } else if (num < 100) {
            System.out.println(decenas.apply(num));
        } else if (num < 1000) {
            System.out.println(centenas.apply(num));
        } else {
            System.out.println("Número fuera de rango (0-999)");
        }
    }
}
