package com.company.guis;

import com.company.entities.BST;
import com.company.entities.TreeNode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class AVLView extends Pane {
    private BST<Integer> tree;
    private final double RADIUS = 15; // final!!!!!!
    private final double VGAP = 75; // final!!!!!!
    private final double HGAP = 150;


    public AVLView(BST<Integer> tree, double width) {
        setWidth(width);
        this.tree = tree;
    }

    /**
     * Lager treet i et pane
     */
    public void drawTree() {
        TreeNode root = tree.getRoot();
        drawRoot(root);
        if(root.getLeft() != null) {
            drawNextNodeLeft((getWidth()/2) - RADIUS, RADIUS, root.getLeft(), HGAP);
        }
        if(root.getRight() != null) {
            drawNextNodeRight((getWidth()/2) - RADIUS, RADIUS, root.getRight(), HGAP);
        }
    }

    /**
     * Lager rooten for treet
     * @param root root noden
     */
    private void drawRoot(TreeNode root) {
        StackPane stack = new StackPane();

        //Circle
        Circle circle = new Circle(RADIUS);

        //text
        Text text = new Text(root.getElement().toString());
        text.setFill(Color.WHITE);
        text.setBoundsType(TextBoundsType.VISUAL);

        //Stack
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX((getWidth()/2) - RADIUS);
        stack.setLayoutY(RADIUS);

        //Add to main
        getChildren().add(stack);
    }

    /**
     * Lager neste node til venstre
     * @param x X til forrige node
     * @param y Y til forrige node
     * @param node Nåværende node
     * @param hGap hGap
     */
    private void drawNextNodeLeft(double x, double y, TreeNode node, double hGap) {
        Double x2 = x - hGap;
        Double y2 = y + VGAP;

        StackPane stack = createStack(node.getElement().toString(), x2, y2);

        //Add to main
        getChildren().addAll(stack, new Line((x + RADIUS) - RADIUS/2, (y + RADIUS) + RADIUS/2, x2 + RADIUS, y2 + RADIUS));

        //Draw next
        drawNext(node, x2, y2, hGap);
    }
    /**
     * Lager neste node til høyre
     * @param x X til forrige node
     * @param y Y til forrige node
     * @param node Nåværende node
     * @param hGap hGap
     */
    private void drawNextNodeRight(double x, double y, TreeNode node, double hGap) {
        Double x2 = x + hGap;
        Double y2 = y + VGAP;

        StackPane stack = createStack(node.getElement().toString(), x2, y2);

        //Add to main
        getChildren().addAll(stack, new Line((x + RADIUS) + RADIUS/2, (y + RADIUS) + RADIUS/2, x2 + RADIUS, y2 + RADIUS));

        //Draw next
        drawNext(node, x2, y2, hGap);
    }

    /**
     * Lager en StackPane til noden
     * @param number Node nummeret
     * @param x2 X til nye noden
     * @param y2 Y til nye noden
     * @return StackPane som inneholder nye noden som skal tegnes
     */
    private StackPane createStack(String number, double x2, double y2) {
        StackPane stack = new StackPane();

        //Circle
        Circle circle = new Circle(RADIUS);

        //Text
        Text text = new Text(number);
        text.setFill(Color.WHITE);
        text.setBoundsType(TextBoundsType.VISUAL);

        //Stack
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX(x2);
        stack.setLayoutY(y2);
        return stack;
    }

    /**
     * Lager neste node dersom det eksisterer
     * @param node Nåværende node
     * @param x2 X til nye noden
     * @param y2 Y til nye noden
     * @param hGap hgap
     */
    private void drawNext(TreeNode node, double x2, double y2, double hGap) {
        if(node.getRight() != null) {
            drawNextNodeRight(x2, y2,node.getRight(), hGap/2);
        }
        if(node.getLeft() != null) {
            drawNextNodeLeft(x2, y2,node.getLeft(), hGap/2);
        }
    }
}
