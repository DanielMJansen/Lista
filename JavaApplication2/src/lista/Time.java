package lista;

public class Time {
    private String nome;
    private int estrela;
    private Time next = null;
    public Time(){
        
    }

    public Time getNext() {
        return next;
    }

    public void setNext(Time next) {
        this.next = next;
    }
    
    public Time(String nome, int estrela) {
        this.nome = nome;
        this.estrela = estrela;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstrela() {
        return estrela;
    }

    public void setEstrela(int estrela) {
        this.estrela = estrela;
    }
}
