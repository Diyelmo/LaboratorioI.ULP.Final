/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compra2;

import compra2.DataBase.CompraDataBase;
import compra2.DataBase.RelacionPaP;
import java.time.LocalDate;

/**
 *
 * @author RODRI
 */
public class Compra2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        CompraDataBase x=new CompraDataBase();
//        System.out.println(x.buscarCompraPorFecha(LocalDate.now()));
        RelacionPaP r=new RelacionPaP();
        System.out.println(  r.listarrelacionActivas());
    
    }
    
}
