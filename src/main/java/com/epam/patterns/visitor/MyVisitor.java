package com.epam.patterns.visitor;

public interface MyVisitor {

    String visitDot(Dot dot);
    String visitRectangle(Rectangle rectangle);
    String visitCircle(Circle circle);
    String visitSquare(Square square);
    String visitCompoundGraphic(CompoundGraphic compoundGraphic);
}
