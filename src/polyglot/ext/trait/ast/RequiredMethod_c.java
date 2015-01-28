package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.types.Flags;
import polyglot.types.MethodInstance_c;
import polyglot.types.ReferenceType;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public class RequiredMethod_c extends MethodInstance_c implements
        RequiredMethod {

    private static final long serialVersionUID = SerialVersionUID.generate();

    protected String name;
    protected Type returnType;

    protected RequiredMethod_c() {
    }

    public RequiredMethod_c(TypeSystem ts, Position pos,
            ReferenceType container, Flags flags, Type returnType, String name,
            List<? extends Type> formalTypes, List<? extends Type> excTypes) {
        super(ts,
              pos,
              container,
              flags,
              returnType,
              name,
              formalTypes,
              excTypes);

        decl = this;
    }

    protected RequiredMethod decl;

}
