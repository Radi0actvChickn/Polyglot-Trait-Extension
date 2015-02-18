package polyglot.ext.trait.types;

import polyglot.ast.Ext;
import polyglot.ast.TypeNode_c;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.PrettyPrinter;

//TODO: finish this class
public class TraitTypeNode_c extends TypeNode_c {

    private static final long serialVersionUID = SerialVersionUID.generate();

    public TraitTypeNode_c(Position pos) {
        super(pos);
    }

    public TraitTypeNode_c(Position pos, Ext ext) {
        super(pos, ext);
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {

    }

}
