package polyglot.ext.trait.ast;

import java.util.ArrayList;
import java.util.List;

import polyglot.ast.TypeNode_c;
import polyglot.ext.trait.types.RequiredMethodInstance;
import polyglot.ext.trait.types.TraitType;
import polyglot.types.ClassType;
import polyglot.types.MethodInstance;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.PrettyPrinter;

public class UseTrait_c extends TypeNode_c implements UseTrait {

    private static final long serialVersionUID = SerialVersionUID.generate();

    private List<RequiredMethod> requiredMethods;
    private List<MethodInstance> providedMethods;
    private ClassType host;

    public UseTrait_c(Position pos, TraitType trait) {
        super(pos);
        List<RequiredMethod> requiredMethods = new ArrayList<>();
        List<MethodInstance> providedMethods = new ArrayList<>();
        for (MethodInstance method : trait.methods()) {
            if (method instanceof RequiredMethodInstance == false) {
                providedMethods.add(method);
            }
        }

        this.requiredMethods = requiredMethods;
        this.providedMethods = providedMethods;
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {

    }

    public void host(ClassType host) {
        this.host = host;
    }

    @Override
    public ClassType host() {
        return host;
    }

    @Override
    public List<MethodInstance> providedMethods() {
        return providedMethods;
    }

    @Override
    public List<RequiredMethod> requiredMethods() {
        return requiredMethods;
    }

}
