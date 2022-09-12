package com.epam.patterns.visitor;

public class XMLExportVisitor implements MyVisitor{

    public String export(Shape... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
        for (Shape shape : args) {
            sb.append(shape.accept(this)).append("\n");
        }
        return sb.toString();
    }

    public String visitDot(Dot d) {
        return "<dot>" + "\n" +
                "    <id>" + d.getId() + "</id>" + "\n" +
                "    <x>" + d.getX() + "</x>" + "\n" +
                "    <y>" + d.getY() + "</y>" + "\n" +
                "</dot>";
    }

    public String visitCircle(Circle c) {
        return "<circle>" + "\n" +
                "    <id>" + c.getId() + "</id>" + "\n" +
                "    <x>" + c.getX() + "</x>" + "\n" +
                "    <y>" + c.getY() + "</y>" + "\n" +
                "    <radius>" + c.getRadius() + "</radius>" + "\n" +
                "</circle>";
    }

    public String visitRectangle(Rectangle r) {
        return "<rectangle>" + "\n" +
                "    <id>" + r.getId() + "</id>" + "\n" +
                "    <x>" + r.getX() + "</x>" + "\n" +
                "    <y>" + r.getY() + "</y>" + "\n" +
                "    <width>" + r.getWidth() + "</width>" + "\n" +
                "    <height>" + r.getHeight() + "</height>" + "\n" +
                "</rectangle>";
    }

    @Override
    public String visitSquare(Square square) {
        return null;
    }

    @Override
    public String visitCompoundGraphic(CompoundGraphic compoundGraphic) {
        return "<compound graphic>" + "\n" +
                " <id> " + compoundGraphic.getId() + "</id>" + "\n" +
                _visitCompoundGraphic(compoundGraphic) + "\n" +
                "</compound graphic>";
    }


    private String _visitCompoundGraphic(CompoundGraphic cg){
        StringBuilder sb = new StringBuilder();
        for (Shape child : cg.children) {
            String shape = child.accept(this);
            sb.append(shape).append("\n");
        }
        return sb.toString();
    }
}
