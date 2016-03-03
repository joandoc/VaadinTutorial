package com.ar.ui.menu;

import com.ar.ui.HomeUI;
import com.ar.ui.views.Prueba;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by joan on 12/10/15.
 */
public class Menu extends VerticalLayout {

    public Menu() {
        setHeightUndefined();
        MenuBar barmenu = new MenuBar();

        MenuBar.MenuItem drinks = barmenu.addItem("Beverages", null, null);

        // Submenu item with a sub-submenu
        MenuBar.MenuItem hots = drinks.addItem("Hot", null, null);
        MenuBar.MenuItem tea = hots.addItem("Tea", FontAwesome.ADJUST, new MyCommand(Prueba.class));
        addComponent(barmenu);
    }
}


class MyCommand implements MenuBar.Command {

    protected Class menuClass;

    public MyCommand(Class clase) {
        menuClass = clase;
    }

    @Override
    public void menuSelected(MenuBar.MenuItem menuItem) {

        HomeUI current = (HomeUI) UI.getCurrent();

        Class<?> clazz = null;
        try {
            clazz = Class.forName(menuClass.getName());
            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();
            current.changeMenuItem((Component)instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}