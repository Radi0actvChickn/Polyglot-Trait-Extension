package polyglot.ext.trait.types;

import polyglot.types.Flags;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem_c;

//TODO: finish this class
public class TraitTypeSystem_c extends TypeSystem_c implements TraitTypeSystem {

    public TraitTypeSystem_c() {
    }

    public boolean isTrait() {
        return false; //FIXME: should not return false
    }

    @Override
    public void checkMethodFlags(Flags f) throws SemanticException {
        super.checkMethodFlags(f);
    }

}
