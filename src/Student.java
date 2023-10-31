public class Student {
    private String id;
    private String first_name;
    private String last_name;
    private String gender;
    private String career_aspiration;
    private int math_score;

    public Student(String id, String first_name, String last_name, String gender, String career_aspiration, int math_score) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.career_aspiration = career_aspiration;
        this.math_score = math_score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareer_aspiration() {
        return career_aspiration;
    }

    public void setCareer_aspiration(String career_aspiration) {
        this.career_aspiration = career_aspiration;
    }

    public int getMath_score() {
        return math_score;
    }

    public void setMath_score(int math_score) {
        this.math_score = math_score;
    }
    public String toString(){
        return "Estudiante{ID='"+this.id+"',PrimerNombre='"+this.first_name+",Apellido='"+this.last_name+"',Genero='"+this.gender+"',Carrera='"+this.career_aspiration+"',PuntajeMatematicas='"+this.math_score+"'}";
    }
}