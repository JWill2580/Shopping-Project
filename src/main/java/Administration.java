
import dao.DbManageProducts;
import gui.Mainmenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wiljo912
 */
public class Administration {
    private static DbManageProducts dao = new DbManageProducts();//possible errors
    public static void main(String[] args) {
        Mainmenu frame = new Mainmenu(dao);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
