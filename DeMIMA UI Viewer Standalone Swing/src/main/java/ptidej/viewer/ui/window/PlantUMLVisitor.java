package ptidej.viewer.ui.window;

import java.util.Iterator;

import padl.kernel.IAbstractModel;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IComposition;
import padl.kernel.IConstituent;
import padl.kernel.IConstructor;
import padl.kernel.IContainerAggregation;
import padl.kernel.IContainerComposition;
import padl.kernel.ICreation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IField;
import padl.kernel.IGetter;
import padl.kernel.IGhost;
import padl.kernel.IInterface;
import padl.kernel.IMemberClass;
import padl.kernel.IMemberGhost;
import padl.kernel.IMemberInterface;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.IPackage;
import padl.kernel.IPackageDefault;
import padl.kernel.IParameter;
import padl.kernel.IPrimitiveEntity;
import padl.kernel.ISetter;
import padl.kernel.IUseRelationship;
import padl.visitor.IWalker;

public class PlantUMLVisitor implements IWalker{
	
	private StringBuilder visitResult = new StringBuilder();

	@Override
	public void close(IAbstractModel anAbstractModel) {
		visitResult.append("\n@enduml");
	}

	@Override
	public void close(IClass aClass) {
		visitResult.append("\n}");
		visitResult.append("}");
		visitResult.append("\n");
		
	}

	@Override
	public void close(IConstructor aConstructor) {
		visitResult.append(")\n");
	}

	@Override
	public void close(IDelegatingMethod aDelegatingMethod) {
		this.close((IMethod) aDelegatingMethod);
	}

	@Override
	public void close(IGetter aGetter) {
		this.close((IMethod) aGetter);
	}

	@Override
	public void close(IGhost aGhost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close(IInterface anInterface) {
		visitResult.append("\n}");
		visitResult.append("}");
		visitResult.append("\n");
	}

	@Override
	public void close(IMemberClass aMemberClass) {
		visitResult.append("\n}");
		visitResult.append("}");
		visitResult.append("\n");
	}

	@Override
	public void close(IMemberGhost aMemberGhost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close(IMemberInterface aMemberInterface) {
		visitResult.append("\n}");
		visitResult.append("}");
		visitResult.append("\n");
	}

	@Override
	public void close(IMethod aMethod) {
		visitResult.append(")\n");
	}

	@Override
	public void close(IPackage aPackage) {
		visitResult.append("\n}");
		visitResult.append("}");
		visitResult.append("\n");
	}

	@Override
	public void close(IPackageDefault aPackage) {
		visitResult.append("\n}");
		visitResult.append("}");
		visitResult.append("\n");
	}

	@Override
	public void close(ISetter aSetter) {
		this.close((IMethod) aSetter);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void open(IAbstractModel anAbstractModel) {
		visitResult.append("@startuml");
	}

	@Override
	public void open(IClass aClass) {
		Iterator<IInterface> implementedInterfaceIterator = aClass.getIteratorOnImplementedInterfaces();
		
		while (implementedInterfaceIterator.hasNext()) {
			IInterface implementedInterface = implementedInterfaceIterator.next();
			visitResult.append(implementedInterface.getName());
			visitResult.append(" <|.." );
			visitResult.append(aClass.getName());
		}
		
		Iterator<IClass> inheritedClassesIterator = aClass.getIteratorOnInheritedEntities();
		
		while (inheritedClassesIterator.hasNext()) {
			IClass inheritedClass = inheritedClassesIterator.next();
			visitResult.append(inheritedClass.getName());
			visitResult.append(" <|--" );
			visitResult.append(aClass.getName());
		}
		
		visitResult.append("class ");
		visitResult.append(aClass.getName());
		visitResult.append(" {\n");
	}

	@Override
	public void open(IConstructor aConstructor) {
		char visibility = '+';
		if (aConstructor.isPrivate()) {
			visibility = '-';
		}
		visitResult.append(visibility);
		visitResult.append(aConstructor.getName());
		visitResult.append("(");
	}

	@Override
	public void open(IDelegatingMethod aDelegatingMethod) {
		this.open((IMethod) aDelegatingMethod);
	}

	@Override
	public void open(IGetter aGetter) {
		this.open((IMethod) aGetter);
	}

	@Override
	public void open(IGhost aGhost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open(IInterface anInterface) {
		Iterator<IInterface> implementedInterfaceIterator = anInterface.getIteratorOnInheritedEntities();
		
		while (implementedInterfaceIterator.hasNext()) {
			IInterface implementedInterface = implementedInterfaceIterator.next();
			visitResult.append(implementedInterface.getName());
			visitResult.append(" <|.." );
			visitResult.append(anInterface.getName());
		}
		
		visitResult.append("interface ");
		visitResult.append(anInterface.getName());
		visitResult.append(" {\n");
	}

	@Override
	public void open(IMemberClass aMemberClass) {
		this.open((IClass) aMemberClass);
	}

	@Override
	public void open(IMemberGhost aMemberGhost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open(IMemberInterface aMemberInterface) {
		this.open((IInterface) aMemberInterface);
	}

	@Override
	public void open(IMethod aMethod) {
		char visibility = '+';
		if (aMethod.isPrivate()) {
			visibility = '-';
		}
		visitResult.append(visibility);
		visitResult.append(aMethod.getName());
	}

	@Override
	public void open(IPackage aPackage) {
		visitResult.append("interface ");
		visitResult.append(aPackage.getName());
		visitResult.append(" {\n");
	}

	@Override
	public void open(IPackageDefault aPackage) {
		this.open((IPackage) aPackage);
	}

	@Override
	public void open(ISetter aSetter) {
		this.open((IMethod) aSetter);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unknownConstituentHandler(String aCalledMethodName, IConstituent aConstituent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IAggregation anAggregation) {
		this.visit((IUseRelationship) anAggregation, "o--");
	}

	@Override
	public void visit(IAssociation anAssociation) {
		this.visit((IUseRelationship) anAssociation, "<--");
	}

	@Override
	public void visit(IComposition aComposition) {
		this.visit((IUseRelationship) aComposition, "*--");
	}

	@Override
	public void visit(IContainerAggregation aContainerAggregation) {
		this.visit((IAggregation) aContainerAggregation);
	}

	@Override
	public void visit(IContainerComposition aContainerComposition) {
		this.visit((IComposition) aContainerComposition);
	}

	@Override
	public void visit(ICreation aCreation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IField aField) {
		visitResult.append(aField.getType());
		visitResult.append(" ");
		visitResult.append(aField.getName());
		visitResult.append("\n");
	}

	@Override
	public void visit(IMethodInvocation aMethodInvocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IParameter aParameter) {
		visitResult.append(aParameter.getType());
		visitResult.append(" ");
		visitResult.append(aParameter.getName());
	}

	@Override
	public void visit(IPrimitiveEntity aPrimitiveEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IUseRelationship aUse) {
		this.visit(aUse, "--");
	}
	
	private void visit(IUseRelationship aUse, String relationshipType) {
		visitResult.append(aUse.getAttachedElement().getName());
		visitResult.append(" " + relationshipType + " ");
		visitResult.append(aUse.getAttachedElement().getName());
	}

	@Override
	public Object getResult() {
		return visitResult.toString();
	}

}
