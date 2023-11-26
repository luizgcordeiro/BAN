/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rebeca
 */
public class ExerciciosBean {
   private int codExercicio;
   private String nome;
   
   public ExerciciosBean(String nome) {
       this.nome = nome;
   }
   
   public ExerciciosBean(int codExercicio, String nome) {
       this.codExercicio= codExercicio;
       this.nome = nome;
   }

    /**
     * @return the codExercicio
     */
    public int getCodExercicio() {
        return codExercicio;
    }

    /**
     * @param codExercicio the codExercicio to set
     */
    public void setCodExercicio(int codExercicio) {
        this.codExercicio = codExercicio;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return "Codigo: "+codExercicio+"; Nome: "+nome;
    }
}