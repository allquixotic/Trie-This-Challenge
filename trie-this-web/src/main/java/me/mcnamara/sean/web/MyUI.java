package me.mcnamara.sean.web;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import me.mcnamara.sean.trie.Trie;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final Trie trie = new Trie();
        final Label lblLabel = new Label("Results are displayed below.");
        final Label label = new Label("", ContentMode.HTML);
        final TextField name = new TextField();
        name.setCaption("Type your search here:");
        name.addValueChangeListener(s -> {
        		//Display the results.
        		try {
        			String retval = String.join("<br>", trie.search(s.getValue()));
        			if(retval.replaceAll("<br>", "").trim().isEmpty()) {
        				retval = "<b>No results.</b>";
        			}
        			label.setValue(retval);
        		}
        		catch(Exception e) {
        			//Ignore because it's probably due to an empty field
        		}
        });
        
        Button button = new Button("Add To Trie");
        button.addClickListener( e -> {
        		try {
        			trie.add(name.getValue());
        		}
        		catch(Exception ex) {
        			//Ignore because it's probably due to an empty field
        		}
        });
        
        layout.addComponents(name, button, lblLabel, label);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
