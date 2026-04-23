package com.daniel.crud;

import javax.swing.UIManager;
import java.awt.*;
import com.daniel.crud.view.UsuarioView;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Error cargando tema");
        }

        UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));

        UIManager.put("Component.arc", 15);
        UIManager.put("Button.arc", 20);
        UIManager.put("TextComponent.arc", 15);

        new UsuarioView().setVisible(true);
    }
}