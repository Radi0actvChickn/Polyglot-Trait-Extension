package polyglot.ext.trait.types;

import java.util.List;

import polyglot.types.Flags;
import polyglot.types.MethodInstance_c;
import polyglot.types.ReferenceType;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public class RequiredMethodInstance_c extends MethodInstance_c implements
        RequiredMethodInstance {
    private static final long serialVersionUID = SerialVersionUID.generate();

    /** Used for deserializing types. */
    protected RequiredMethodInstance_c() {
    }

    public RequiredMethodInstance_c(TypeSystem ts, Position pos,
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

    protected RequiredMethodInstance decl;

}
