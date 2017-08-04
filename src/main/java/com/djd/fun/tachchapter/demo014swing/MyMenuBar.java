package com.djd.fun.tachchapter.demo014swing;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.djd.fun.tachchapter.demo014swing.actions.CloseAction;

public class MyMenuBar extends JMenuBar {

  public MyMenuBar(MyPanel myPanel) {
    super.add(createFileMenu());
    super.add(createDemoMenu(myPanel));
  }

  private static JMenu createDemoMenu(MyPanel myPanel) {
    JMenu jMenuDemo = new JMenu("Demo");
    for (String componentName : myPanel.getComponentNames()) {
      JMenuItem item = new JMenuItem(componentName);
      item.addActionListener(myPanel);
      jMenuDemo.add(item);
    }
    return jMenuDemo;
  }

  private static JMenu createFileMenu() {
    JMenu jMenuFile = new JMenu("File");
    JMenuItem jMenuItemExit = new JMenuItem("Exit");
    jMenuItemExit.addActionListener(new CloseAction());
    jMenuFile.add(jMenuItemExit);
    // Set action upon the menu item is selected
    return jMenuFile;
  }
}
