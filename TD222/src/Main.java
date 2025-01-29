import enstabretagne.base.time.LogicalDateTime;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.numbers.core.Precision;

import java.awt.*;
import java.util.Vector;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
        Line.
        Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-6);
        Vector2D v1 = Vector2D.of(0, 0);
        Vector2D v2 = Vector2D.of(10,0);
        Line l  = Line.fromPoints(v1, v2, precision);
        var p = l.pointAt(3, 0);
        LinePath path = LinePath.builder(precision)
                .append(Vector2D.ZERO)
                .append(v1)
                .append(v2)
                .build();

        System.out.println(p);
    }
}