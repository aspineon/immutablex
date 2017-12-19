# ImmutableX

We all love immutability... However, in practice, 
it's pretty hard to implement it in our entire model. 
Mutable or immutable, one thing that we hate is when we pass
an instance of an object to a method, and the method suddenly changes
the state of that class. The caller method may not be expecting 
such changes!!

See the example below:

```java
// This is some regular entity
class Invoice {
  public void changeState() { ... }
}

// This is some class that plays with Invoice 
class BusinessRule1 {
  public void doSomething() {
      Invoice inv = new Invoice();
      
      new BusinessRule2().doSomething(inv);
      
      // In here, I still believe that 'inv' is in the same state
      // I left it before passing it to BusinessRule2
      
      // ...
      
  }
}

class BusinessRule2 {
    public void doSomething(Invoice inv) {
        // Oh no, we are changing the state here!!!
        // Poor BusinessRule1, it will never know it happened.
        inv.changeState();
    }
}
```

Yes, it hurts. It would be much better if the instantiated class could only
have its state changed in the same scope it was declared.

**ImmutableX** detects such violations. To use it, you have to:

- Create 
an annotation in your software with the name of `@ImmutableX`. Yes, you create it, so that
you don't have to depend on any new crazy jar.
- Annotate which classes of your system you do not allow such violation to happen. 
Just add `@ImmutableX` in the class declaration.
- That's it. Just point our tool to your source directory and see the violations. 

The tool provides the class and method that makes bad usage of the immutable class as well as
line number and invalid method invocation. 
Example:

```
Class1#method:9 -> invalid invocation: c.setName("mauricio")
Class2#otherMethod:11 -> invalid invocation: c.setName("alberto")
```

Please, do remember that this was programmed in a 2-hour pair programming session
just to experiment the idea (and for fun, obviously). We do not provide any warranties, but
we would be very happy to get your feedback!

## Authors

This project was created by:
- Maurício Aniche (@mauricioaniche)
- Alberto Souza (@alberto_souza)

## License

This software is licensed under the Apache 2.0 License.
