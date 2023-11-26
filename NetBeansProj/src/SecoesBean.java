/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rebeca
 */
public class SecoesBean {
   private int codSecao;
   private String descricao;
   
   public SecoesBean(String descricao) {
       this.descricao= descricao;
   }

   public SecoesBean(int codSecao , String descricao) {
       this.codSecao = codSecao;
       this.descricao= descricao;
   }
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the codSecao
     */
    public int getCodSecao() {
        return codSecao;
    }

    /**
     * @param codSecao the codSecao to set
     */
    public void setCodSecao(int codSecao) {
        this.codSecao = codSecao;
    }
    
    public String toString(){
        return "Codigo: "+codSecao+"; Descricao: "+descricao;
    }
}