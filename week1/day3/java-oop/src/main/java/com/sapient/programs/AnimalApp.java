package com.sapient.programs;

import com.sapient.entity.Animal;
import com.sapient.entity.Cat;
import com.sapient.entity.Dog;
import com.sapient.entity.Lion;

public class AnimalApp {

    static void processAnimal(Animal x) {
        x.logAuthorDetails();
        x.whoAmI(); // method in the object type (not the reference type) is called
        // for the compiler x is just an Animal
        // but for the runtime, x may be a Cat or Dog or ...
        // because of this, x can only call the methods defined in Animal (otherwise
        // compiler wines)
    }

    public static void main(String[] args) {
        Animal a1;

        Cat c1 = new Cat();
        Dog d1 = new Dog();

        processAnimal(c1);
        processAnimal(d1);
        processAnimal(new Lion());

        Animal ani;
        Cat cat = new Cat();
        Dog dog = new Dog();

        ani = cat; // upcasting; implicitly done

        cat = (Cat) ani; // downcasting; has to be explicit
        cat.whoAmI();

        dog = (Dog) ani;
        dog.whoAmI();
    }
}
