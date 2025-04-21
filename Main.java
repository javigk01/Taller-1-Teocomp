import java.util.List;
import java.util.function.Function;

public class Main {

    static Function<Integer, String> unidades = (n) -> {
        List<String> digitos = List.of("cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve");
        return n < digitos.size() ? digitos.get(n) : "Número fuera de rango";
    };

    static Function<Integer, String> especiales = (n) -> {
        List<String> especiales = List.of("diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve");
        return (n >= 10 && n < 20) ? especiales.get(n - 10) : "Número fuera de rango";
    };

    static Function<Integer, String> decenas = (n) -> {
        List<String> dieces = List.of("", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa");
        String resto = "";
        if (n % 10 != 0) {
            resto = " y " + unidades.apply(n % 10);
        }
        return (n / 10 < dieces.size()) ? dieces.get(n / 10) + resto : "Número fuera de rango";
    };

    static Function<Integer, String> centenas = (n) -> {
        List<String> miles = List.of("", "cien", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos");
        String resto = "";
        if (n % 100 != 0) {
            resto = " " + decenas.apply(n % 100);
        }
        return (n / 100 < miles.size()) ? miles.get(n / 100) + resto : "Número fuera de rango";
    };

    static Function<Integer, String> miles = (n) -> {
        String resto = "";
        if (n % 1000 != 0) {
            resto = " " + centenas.apply(n % 1000);
        }
        return (n / 1000 == 1 ? "mil" : centenas.apply(n / 1000) + " mil") + resto;
    };

    static Function<Integer, String> millones = (n) -> {
        String resto = "";
        if (n % 1000000 != 0) {
            resto = " " + miles.apply(n % 1000000);
        }
        int millonesParte = n / 1000000;
        return (millonesParte == 1 ? "un millón" : centenas.apply(millonesParte) + " millones") + resto;
    };

    public static void main(String[] args) {
        final Integer num = 122;

        if (num < 10) {
            System.out.println(unidades.apply(num));
        } else if (num >= 10 && num < 20) {
            System.out.println(especiales.apply(num));
        } else if (num >= 20 && num < 100) {
            System.out.println(decenas.apply(num));
        } else if (num >= 100 && num < 1000) {
            System.out.println(centenas.apply(num));
        } else if (num >= 1000 && num < 1000000) {
            System.out.println(miles.apply(num));
        } else if (num >= 1000000 && num < 1000000000) {
            System.out.println(millones.apply(num));
        } else {
            System.out.println("Número fuera de rango");
        }
    }
}
