package antlr4;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ice
 * @date 18-12-29 下午1:58
 */
public class MathBaseVisitorTest extends MathBaseVisitor<Double> {

    Map<String, Double> memory = new HashMap<>();

    @Override
    public Double visitAssign(MathParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Double value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Double visitPrintExpr(MathParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr());

        DecimalFormat df = new DecimalFormat("#.##");
        String sValue = df.format(value);
        System.out.println(sValue);
        return 0.0;
    }

    @Override
    public Double visitPrint(MathParser.PrintContext ctx) {
        String id = ctx.ID().getText();
        Double value = 0.0;
        if (memory.containsKey(id)) {
            value = memory.get(id);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String sValue = df.format(value);
        System.out.println(sValue);
        return value;
    }

    @Override
    public Double visitNumber(MathParser.NumberContext ctx) {
        int size = ctx.getChildCount();
        if (size == 2) {
            if (ctx.sign.getType() == MathParser.SUB) {
                return -1 * Double.valueOf(ctx.getChild(1).getText());
            } else {
                return Double.valueOf(ctx.getChild(1).getText());
            }
        } else {
            return Double.valueOf(ctx.getChild(0).getText());
        }
    }

    @Override
    public Double visitId(MathParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0.0;
    }

    @Override
    public Double visitMulDiv(MathParser.MulDivContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));

        if (ctx.op.getType() == MathParser.MUL) {
            return left * right;
        } else {
            if (right == 0 || right == 0.0) {
                System.out.println("Divisor can not be zero");
                return 0.0;
            } else {
                return left / right;
            }
        }
    }

    @Override
    public Double visitAddSub(MathParser.AddSubContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        if (ctx.op.getType() == MathParser.ADD) {
            return left + right;
        }
        return left - right;
    }

    @Override
    public Double visitParens(MathParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitPower(MathParser.PowerContext ctx) {
        Double base = visit(ctx.expr(0));
        Double exponet = visit(ctx.expr(1));
        return Math.pow(base, exponet);
    }
}
