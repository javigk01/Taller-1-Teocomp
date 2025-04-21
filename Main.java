import java.util.function.Function ;

class Main {
    public static void main(String[] args) {
        Function<Integer, String> funcion = (n) -> {
            if (n % 2 == 0)
                return "El Numero" + n + "es par";
            else
                return "El Numero" + n + "es impar";
        };
        System.out.println(funcion.apply(10));
        System.out.println(funcion.apply(7));
    }
}
