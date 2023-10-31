import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class Main {
    static List<Student> students; //Lista de estudiantes

    public static void main(String[] args) throws IOException {
        cargarArchivo(); //Carga el Csv
        MostrarEstudiantesPorCarrera(); //Muestra los estudiantes por carrera
        totalMujeresPorCarrera(); //Muestra la cantidad de mujeres por carrera
        totalHombresPorCarrera();//Muestra la cantidad de hombres por carrera
        estudianteConPuntajeMasAltoPorCarrera(); //Muestra el estudiante con el puntaje más alto por carrera
        estudianteConPuntajeMasAlto(); //Muestra los estudiantes con el puntaje más alto
        puntajePromedioPorCarrera(); //Muestra el puntaje promedio por carrera
    }
    //Este método se encarga de cargar el CSV
    static void cargarArchivo() throws IOException {
        Pattern pattern = Pattern.compile(",");
        String filename = "students-scores.csv";
        Stream<String> lines = Files.lines(Path.of(filename));

        try { //Se encarga de leer el archivo y cargarlo en la lista de estudiantes
            students = lines.skip(1L).map((line) -> {
                String[] parts = pattern.split(line);
                return new Student(parts[0], parts[1], parts[2], parts[4], parts[9],Integer.parseInt(parts[10]));
            }).collect(Collectors.toList());
            students.forEach(System.out::println);
        } finally {
            if (lines != null) {
                lines.close();
            }
        }
    }
    //Este método se encarga de mostrar los estudiantes por carrera
    static void MostrarEstudiantesPorCarrera() {
        System.out.printf("%nEstudiantes por carrera:%n");
        Map<String, List<Student>> studentsByCareer = students.stream().collect(Collectors.groupingBy(Student::getCareer_aspiration));
        studentsByCareer.forEach((career, studentList) -> {
            System.out.println("Carrera: " + career);
            studentList.forEach(student -> {
                System.out.println("  ID: " + student.getId() + ", Nombre: " + student.getFirst_name() + " " + student.getLast_name() );
            });
        });
    }
    //Este método se encarga de mostrar la cantidad de mujeres por carrera
    static void totalMujeresPorCarrera() {
        System.out.printf("%nCantidad de mujeres por carrera:%n");
        Map<String, Long> mujeresPorCarrera = students.stream().filter(s -> s.getGender().equalsIgnoreCase("female")).collect(Collectors.groupingBy(Student::getCareer_aspiration, Collectors.counting()));
        mujeresPorCarrera.forEach((career, count) -> {
            System.out.println("Carrera: " + career + ", Total de mujeres: " + count);
        });
    }
    //Este método se encarga de mostrar la cantidad de hombres por carrera
    static void totalHombresPorCarrera() {
        System.out.printf("%nCantidad de hombres por carrera:%n");

        Map<String, Long> mujeresPorCarrera = students.stream().filter(s -> s.getGender().equalsIgnoreCase("male")).collect(Collectors.groupingBy(Student::getCareer_aspiration, Collectors.counting()));

        mujeresPorCarrera.forEach((career, count) -> {
            System.out.println("Carrera: " + career + ", Total de hombres: " + count);
        });
    }
    //Este método se encarga de mostrar el estudiante con el puntaje más alto por carrera
    static void estudianteConPuntajeMasAltoPorCarrera() {
        System.out.printf("%nEstudiante con el puntaje más alto por carrera:%n");
        Map<String, Optional<Student>> topStudentByCareer = students.stream().collect(Collectors.groupingBy(Student::getCareer_aspiration, Collectors.maxBy(Comparator.comparingInt(Student::getMath_score))));

        topStudentByCareer.forEach((career, student) -> {
            if (student.isPresent()) {
                System.out.println("Carrera: " + career);
                System.out.println("  ID: " + student.get().getId() + ", Nombre: " + student.get().getFirst_name() + " " + student.get().getLast_name() + ", Puntaje de matemáticas: " + student.get().getMath_score());
            }
        });
    }
    //Este método se encarga de mostrar los estudiantes con el puntaje más alto
    static void estudianteConPuntajeMasAlto() {
        System.out.printf("%nEstudiantes con el puntaje más alto:%n");
        int maxScore = students.stream()
                .mapToInt(Student::getMath_score)
                .max()
                .orElse(-1);

        List<Student> topStudents = students.stream()
                .filter(s -> s.getMath_score() == maxScore)
                .collect(Collectors.toList());

        if (!topStudents.isEmpty()) {
            topStudents.forEach(student -> {
                System.out.println("ID: " + student.getId() + ", Nombre: " + student.getFirst_name() + " " + student.getLast_name() + ", Puntaje de matemáticas: " + student.getMath_score());
            });
        } else {
            System.out.println("No hay estudiantes en la lista.");
        }
    }
    //Este método se encarga de mostrar el puntaje promedio por carrera
    static void puntajePromedioPorCarrera() {
        System.out.printf("%nPuntaje promedio por carrera:%n");
        Map<String, Double> averageScoreByCareer = students.stream()
                .collect(Collectors.groupingBy(Student::getCareer_aspiration,
                        Collectors.averagingInt(Student::getMath_score)));

        averageScoreByCareer.forEach((career, avgScore) -> {
            System.out.println("Carrera: " + career + ", Puntaje promedio de matemáticas: " + avgScore);
        });
    }
}