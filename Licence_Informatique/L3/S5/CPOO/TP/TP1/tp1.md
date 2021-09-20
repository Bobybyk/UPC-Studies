# CPOO TP1


## Ex1

1. Un même code-octet JVM est exécutable sur plusieurs plateformes physiques (x86, PPC,
ARM, ...) [x]
2. La JVM interprète du code source Java []
3. Le mot-clé this est une expression qui s’évalue comme l’objet sur lequel la méthode
courante a été appelée. [x]
4. Toute classe dispose d’un constructeur par défaut, sans paramètre. []
5. On peut écrire public devant la déclaration d’une variable locale pour qu’elle soit visible
depuis les autres classes. []
6. Dès lors qu’un objet n’est plus utilisé, il faut penser à demander à Java de libérer la
mémoire qu’il occupe. []
7. La durée de vie d’un attribut statique est liée à celle d’une instance donnée de la classe
où il est défini. []

## Ex2

```java
class Truc {
	static int v1 = 0;
	int v2 = 0;

	public int getV1() { return v1; }
	public int getV2() { return v2; }
	
	public Truc() {
		v1++; v2++;
	}
}

public class Main {
	public static void main(String args[]) {
		System.out.println(new Truc().getV1());
		System.out.println(new Truc().getV2());
		System.out.println(new Truc().getV1());
	}
}
```

La ligne **14** affichera **1**
La ligne **15** affichera **3**

## Ex3

```java
public class A{
	private int a;

	public class AA{
		private int a;
		public AA(int y){ this.a = y;}
	}

	public static class AB{
		private int b;
		public AB(int x){ this.b = x;}
	}

	public A(int a){
		this.a = a;
	}
}

public class Main{
	public static void main(String[] args){
		A unA = new A(2);
	// A.AA unAA = new A.AA(3); Incorrect
	// A.AB unAB = new A.AB(4); Correct
	// A.AA autreAA = unA.new AA(3); Correct
	// A.AB autreAB = unA.new AB(4); Incorrect
}
```

## Ex4

