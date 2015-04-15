package polyglot.ext.trait.ast;

import java.util.List;

import polyglot.ast.TypeNode;
import polyglot.types.ClassType;
import polyglot.types.MethodInstance;

public interface UseTrait extends TypeNode {

    ClassType host();

    List<MethodInstance> providedMethods();

    List<RequiredMethod> requiredMethods();

}
