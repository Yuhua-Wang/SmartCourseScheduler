import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;


public class For_Eyeballing {
    public static void main(String[] args) {
        Model model = new Model("testing");

        IntVar x = model.intVar("X",1,5);
        IntVar y = model.intVar("Y",new int[]{3,2,4,7});
        IntVar z = model.intVar("Z", 2,4);

        model.arithm(x,"=",y).post();
        model.arithm(y,"=",z).post();
        model.arithm(x,"=",z).post();

        List<Solution> s = model.getSolver().findAllSolutions();
        long a = model.getSolver().getSolutionCount();
        System.out.println(a);
        System.out.println(s.get(1).getIntVal(x));
        System.out.println(s.get(1).getIntVal(y));
        System.out.println(s.get(1).getIntVal(z));

    }
}

