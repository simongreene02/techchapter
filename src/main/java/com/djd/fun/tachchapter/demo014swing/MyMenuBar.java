package com.djd.fun.tachchapter.demo014swing;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.djd.fun.tachchapter.demo014swing.actions.CloseAction;

public class MyMenuBar extends JMenuBar {

  public MyMenuBar() {
    JMenu jMenuFile = new JMenu("File");
    JMenuItem jMenuItemExit = new JMenuItem("Exit");
    jMenuFile.add(jMenuItemExit);
    // Set action upon the menu item is selected
    jMenuItemExit.addActionListener(new CloseAction());
    super.add(jMenuFile);
  }

}
