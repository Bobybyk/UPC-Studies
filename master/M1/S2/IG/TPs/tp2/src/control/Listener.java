package control;

import view.Window;

public class Listener {

    public Listener(Window view) {

        view.getSlate().setSelectedTool("crayon");

        view.getTools().getCrayon().addActionListener(e -> {
            System.out.println("Crayon");
            view.getSlate().setSelectedTool("crayon");
        });

        view.getTools().getPinceau().addActionListener(e -> {
            System.out.println("Pinceau");
            view.getSlate().setSelectedTool("pinceau");
        });

        view.getTools().getGomme().addActionListener(e -> {
            System.out.println("Gomme");
            view.getSlate().setSelectedTool("gomme");
        });

        view.getTools().getLigne().addActionListener(e -> {
            System.out.println("Ligne");
            view.getSlate().setSelectedTool("ligne");
        });

        view.getTools().getRectangle().addActionListener(e -> {
            System.out.println("Rectangle");
            view.getSlate().setSelectedTool("rectangle");

        });

        view.getTools().getCercle().addActionListener(e -> {
            System.out.println("Cercle");
            view.getSlate().setSelectedTool("cercle");
        });

        view.getTools().getRemplir().addActionListener(e -> {
            System.out.println("Remplir");
            view.getSlate().setSelectedTool("remplir");
        });
    }
}
