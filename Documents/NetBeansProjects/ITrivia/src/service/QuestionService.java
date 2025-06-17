package service;

import model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public List<Question> getQuestions(String difficulty) {
        List<Question> questions = new ArrayList<>();
       
//EASY QUESTIONS >>>>> 
        if (difficulty.equalsIgnoreCase("Easy")) {
            questions.add(new Question("What programming language is named after a type of Indonesian coffee?",
                new String[]{"Python", "Java", "Ruby", "Swift"}, 1));

            questions.add(new Question("What does GUI stand for?",
                new String[]{"Graphical User Interface", "General User Input", "Graphic Utility Interface", "Global User Interaction"}, 0));

            questions.add(new Question("What slogan is associated with Java's cross-platform capabilities?",
                new String[]{"Write once, run anywhere", "Code once, run anywhere", "Compile anywhere, run once", "Portable code, stable performance"}, 0));

            questions.add(new Question("What file extension is used for Java source code files?",
                new String[]{"Python", ".js", ".class", ".java"}, 3));

            questions.add(new Question("What does \"IDE\" stand for in software development?",
                new String[]{"Integrated Debug Environment", "Internal Design Editor", "Integrated Development Environment", "Interface Design Engine"}, 2));

            questions.add(new Question("Who is known as the first computer programmer?",
                new String[]{"Charles Babbage", "Ada Lovelace", "Alan Turing", "Grace Hopper"}, 1));

            questions.add(new Question("What was the first computer virus called?",
                new String[]{"Slammer", "ILOVEYOU", "Brain", "Creeper"}, 3));

            questions.add(new Question("What symbol ends a statement in Java?",
                new String[]{"Semicolon (;)", "Colon (:)", "Period (.)", "Bracket (})"}, 0));

            questions.add(new Question("What do you call the brain of the computer?",
                new String[]{"ROM (Read-Only Memory)", "GPU (Graphic Processing Unit)", "RAM (Random Access Memory)", "CPU (Central Processing Unit)"}, 3));

            questions.add(new Question("What does HTML stand for?",
                new String[]{"HyperText Markup Level", "Hyper Transfer Markup Language", "HyperText Markup Language", "Hyper Tool Mark Language"}, 2));

            questions.add(new Question("What company owns the Java trademark today?",
                new String[]{"Microsoft", "Oracle Corporation", "Google", "Sun Microsystems"}, 1));

            questions.add(new Question("What do you use to move the pointer on the screen?",
                new String[]{"Keyboard", "Monitor", "Mouse", "Scanner"}, 2));

            questions.add(new Question("What part of the computer lets you see what you're doing?",
                new String[]{"CPU", "Printer", "Monitor", "Speaker"}, 2));

            questions.add(new Question("Which company developed the Java programming language?",
                new String[]{"IBM", "Oracle", "Microsoft", "Sun Microsystems"}, 3));

            questions.add(new Question("What are the two most commonly used tools for planning a program's logic?",
                new String[]{"Diagram and Flowcode", "Flowchart and Pseudocode", "Blueprint and Syntax", "Model and Algorithm"}, 1));

//AVERAGE QUESTIONS >>>>>
        } else if (difficulty.equalsIgnoreCase("Average")) {
            questions.add(new Question("What is the term for finding and fixing errors in code?",
                new String[]{"Compiling", "Testing", "Parsing", "Debugging"}, 3));

            questions.add(new Question("What is the process of converting source code into machine code called?",
                new String[]{"Interpretation", "Execution", "Compilation", "Linking"}, 2));

            questions.add(new Question("What Java keyword is used to inherit a class?",
                new String[]{"implements", "inherit", "extends", "super"}, 2));

            questions.add(new Question("What keyword is used to define a class in Java?",
                new String[]{"define", "class", "struct", "object"}, 1));

            questions.add(new Question("What is the main method signature in Java?",
                new String[]{"void main(String args[])", "static void main(String args[])", "public void main(String[] args)", "public static void main(String[] args)"}, 3));

            questions.add(new Question("What is the output of System.out.println(\"Hello\" + 5); in Java?",
                new String[]{"Hello", "5", "Hello5", "Error"}, 2));

            questions.add(new Question("What is the output of System.out.println(100 / 25); in Java?",
                new String[]{"25", "100", "0", "4"}, 3));

            questions.add(new Question("What is the output of System.out.println(true && false); in Java?",
                new String[]{"true", "false", "null", "error"}, 1));

            questions.add(new Question("Which programming language introduced the concept of “object-oriented programming”?",
                new String[]{"Simula", "Java", "C++", "Smalltalk"}, 0));

            questions.add(new Question("What do you call a value that is stored in an uninitialized variable?",
                new String[]{"Garbage", "Zero", "Null", "Default"}, 0));

            questions.add(new Question("A method that doesn't return any value",
                new String[]{"Returnless", "Empty", "Null", "Void"}, 3));

            questions.add(new Question("Which of the following is NOT a use case of Java in real-world applications?",
                new String[]{"Artificial Intelligence", "Mobile Applications", "Web Servers", "Scientific Applications"}, 0));

            questions.add(new Question("What is the scope of a variable declared inside a method?",
                new String[]{"Local", "Global", "Instance", "Static"}, 0));

            questions.add(new Question("What does the ++ operator do in Java?",
                new String[]{"Increments a variable by 1", "Increments a variable by 2", "Converts a variable to a positive number", "Doubles the variable's value"}, 0));

            questions.add(new Question("Which loop guarantees at least one execution of its block?",
                new String[]{"do-while", "while", "for", "foreach"}, 0));

            questions.add(new Question("Which of the following is an example of a string constant?",
                new String[]{"100", "3.14", "\"Hello World\"", "True"}, 2));

            questions.add(new Question("What does the assignment operator (=) do in programming?",
                new String[]{"Compares two values", "Multiplies two values", "Divides two numbers", "Assigns a value to a variable"}, 3));

            questions.add(new Question("Which of the following is NOT a valid variable naming convention?",
                new String[]{"snake_case", "kebab-case", "PascalCase", "camelCase"}, 1));

            questions.add(new Question("Which of the following best describes an application software?",
                new String[]{"Operating System", "ROM", "Microsoft Excel", "BIOS"}, 2));

            questions.add(new Question("In a flowchart, which symbol represents decision-making?",
                new String[]{"Rectangle", "Parallelogram", "Oval", "Diamond"}, 3));

            questions.add(new Question("What type of loop is used when the number of repetitions is already known before the loop starts?",
                new String[]{"Counter-controlled loop", "Sentinel-controlled loop", "Indefinite loop", "Infinite loop"}, 0));

            questions.add(new Question("Who were the original developers of Java?",
                new String[]{"Bill Gates, Steve Jobs, and Mark Zuckerberg", "David Lynch, Kyle MacLachlan, and Laura Dern", "Martin Scorsese, Al Pacino, and Robert De Niro", "James Gosling, Mike Sheridan, and Patrick Naughton"}, 3));

            questions.add(new Question("Which of the following is NOT a primitive data type in Java?",
                new String[]{"int", "String", "double", "boolean"}, 1));

            questions.add(new Question("What is the purpose of the Scanner class in Java?",
                new String[]{"To print output to the screen", "To perform complex mathematical operations", "To handle graphical user interface components", "To read user input from the console"}, 3));

            questions.add(new Question("What is the correct syntax to create a Scanner object to take input from the user in Java?",
                new String[]{"Scanner input = Scanner(System.in);", "Scanner input = new Scanner(System.in);", "Scanner input = new Scanner();", "Scanner = new Scanner();"}, 1));
            
//EXTREME QUESTIONS >>>>>    
        } else if (difficulty.equalsIgnoreCase("Extreme")) {
            questions.add(new Question("An abstract class has both what?",
                new String[]{"Only static methods and constructors", "Private methods and interfaces", "A regular method and an abstract method", "Constructors and final methods"}, 2));

            questions.add(new Question("Once an abstract method is declared in the super class, it is what?",
                new String[]{"Optional to override in the subclass", "Automatically implemented in all subclasses", "Converted into a static method in subclasses", "It is mandatory for all subclasses"}, 3));

            questions.add(new Question("What is an Abstract method?",
                new String[]{"A method that can only be called from a constructor", "A method that doesn't have a body", "A method that is always static and final", "A method that automatically executes at runtime without being called"}, 1));

            questions.add(new Question("In a complex system using hybrid inheritance via interface in Java, how can class D correctly inherit from interface A and both classes B and C if C and B implement different interfaces extending A?",
                new String[]{"By forcing B and C to share a common superclass and extending only one of them in D", "By making D extends both B and C directly, since Java supports multiple class inheritance through interfaces", "By using abstract classes instead of interfaces since Java handles hybrid inheritance via abstract classes", "By defining D to implement a new interface that extends both interfaces implemented by B and C"}, 3));

            questions.add(new Question("In the context of inheritance, what happens if a subclass does not override an inherited method?",
                new String[]{"The subclass will inherit and use the method from the superclass", "The compiler throws an error", "The method becomes private automatically", "The subclass becomes abstract"}, 0));

            questions.add(new Question("What is the primary benefit of polymorphism in object-oriented programming?",
                new String[]{"Allows hiding of data", "Enables code reuse through copying", "Prevents the use of constructors in derived classes", "Enables one interface to be used for a general class of actions"}, 3));

            questions.add(new Question("Which of the following best defines the IS-A relationship in Java?",
                new String[]{"A class inherits from another class", "A class has an object of another class", "A class implements multiple interfaces", "A class contains static members of another class"}, 0));

            questions.add(new Question("Which of the following statements best describes the behavior of this code segment?\nint a = 5, b = 10;\nSystem.out.println(a++ + ++b);\nSystem.out.println(\"a: \" + a + \", b: \" + b);",
                new String[]{"Output: 16, a: 6; b: 11", "Output 15, a: 5, b: 10", "Output: 17, a: 6, b: 11", "Output: Compilation error due to illegal increment syntax"}, 0));

            questions.add(new Question("Which of the following variable declarations will cause a compilation error?",
                new String[]{"float f = 3.14;", "double d = 3.14;", "char c = 'A';", "boolean b = false;"}, 0));

            questions.add(new Question("Given the expression below, what will be the result?\nboolean a = true;\nboolean b = false;\nboolean c = true;\nSystem.out.println(a && b || c);",
                new String[]{"true", "false", "Compilation error", "Runtime exception"}, 0));

            questions.add(new Question("Which of the following method declarations will be considered valid overloading of the method display in the same class?\npublic void display(int a) {...}",
                new String[]{"public void display(double a)", "public int display(int a)", "private void display(int a)", "public static void display(int a)"}, 0));

            questions.add(new Question("Which of the following best describes why logical errors are generally harder to detect than syntax errors during program development?",
                new String[]{"Logical errors do not prevent compilation but cause incorrect behavior at runtime, often requiring thorough testing to detect.", "Logical errors cause programs to fail to compile, while syntax errors only cause incorrect output.", "Logical errors can be identified easily by the compiler, but syntax errors require manual debugging.", "Logical errors only occur in low-level programming languages, unlike syntax errors."}, 0));

            questions.add(new Question("In a while loop controlled by a sentinel value, what is a potential consequence of choosing a sentinel value that could also be valid input data?",
                new String[]{"The loop might terminate prematurely, skipping valid iterations.", "The loop will never terminate and create an infinite loop.", "The program will throw a syntax error at compile time.", "The sentinel value will automatically be ignored by the loop control."}, 0));

            questions.add(new Question("Which variable naming convention is characterized by using underscores to separate words and is commonly used in languages like Python and C++?",
                new String[]{"snake_case", "camelCase", "PascalCase", "kebab-case"}, 0));

            questions.add(new Question("When modularizing a program, which of the following is NOT a primary benefit of modularization?",
                new String[]{"Automatically generating machine code without compiling.", "Providing abstraction to focus on high-level design.", "Allowing simultaneous development by multiple programmers.", "Enabling easier code reuse for customization."}, 0));

            questions.add(new Question("Which of the following is true about Java interfaces?",
                new String[]{"They can have constructors", "They can only contain abstract methods and static final variables", "They can contain instance fields", "They can be instantiated directly"}, 1));

            questions.add(new Question("What keyword is used to call a superclass constructor from a subclass in Java?",
                new String[]{"super", "this", "parent", "base"}, 0));

            questions.add(new Question("What happens if a Java class implements multiple interfaces that have methods with the same signature?",
                new String[]{"It causes a compilation error", "The class must override the method once", "The first interface's method is chosen", "Java does not support multiple interfaces"}, 1));

            questions.add(new Question("Which collection class allows you to store elements in key-value pairs and does not allow duplicate keys?",
                new String[]{"HashMap", "ArrayList", "LinkedList", "HashSet"}, 0));

            questions.add(new Question("What does the 'final' keyword mean when applied to a method?",
                new String[]{"The method cannot be overridden", "The method cannot be called", "The method is static", "The method is abstract"}, 0));

            questions.add(new Question("Which exception must be either caught or declared to be thrown in Java?",
                new String[]{"All exceptions", "Checked exceptions", "Runtime exceptions", "Errors"}, 1));

            questions.add(new Question("What does the term 'autoboxing' refer to in Java?",
                new String[]{"Automatic conversion between primitives and their wrapper classes", "Automatic memory management", "Conversion from int to float", "Automatic method overriding"}, 0));

            questions.add(new Question("What is the output of the following code?\nint x = 10;\nSystem.out.println(x++);",
                new String[]{"Runtime exception", "10", "11", "Compilation error"}, 1));

            questions.add(new Question("Which of the following statements about Java garbage collection is TRUE?",
                new String[]{"It requires manual invocation to run", "It automatically frees memory by destroying unreachable objects", "It pauses all threads while running", "It only runs when the program exits"}, 1));

            questions.add(new Question("What Java keyword is used to prevent inheritance of a class?",
                new String[]{"abstract", "final", "static", "private"}, 1));
        }
        return questions;
    }
}
