interface AbstractNumberInterface {
    void setInteger(int n);

    int getInteger();
}

interface AbstractSummationInterface {
    public AbstractNumberInterface add(AbstractNumberInterface a, AbstractNumberInterface b);
}

interface AbstractSubtractionInterface {
    public AbstractNumberInterface subtract(AbstractNumberInterface a, AbstractNumberInterface b);
}

interface AbstractMultiplicationInterface {
    public AbstractNumberInterface multiply(AbstractNumberInterface a, AbstractNumberInterface b);
}

interface AbstractDivisionInterface {
    public AbstractNumberInterface divide(AbstractNumberInterface a, AbstractNumberInterface b) throws Exception;
}

interface AbstractCalculatorFactoryInterface {
    public AbstractCalculatorInterface buildCalculator();
}

interface AbstractCalculatorInterface {
    public AbstractNumberInterface addNumbers(AbstractNumberInterface a, AbstractNumberInterface b);

    public AbstractNumberInterface substractNumbers(AbstractNumberInterface a, AbstractNumberInterface b);

    public AbstractNumberInterface multiplyNumbers(AbstractNumberInterface a, AbstractNumberInterface b);

    public AbstractNumberInterface divideNumbers(AbstractNumberInterface a, AbstractNumberInterface b);

}

///////////////////////////////////////////////////////////////

class ConcreteIntegerNumber implements AbstractNumberInterface {

    private int n;

    ConcreteIntegerNumber(int n) {
        this.n = n;
    }

    @Override
    public void setInteger(int n) {
        this.n = n;
    }

    @Override
    public int getInteger() {
        return this.n;
    }
}

class ConcreteIntegerSummation implements AbstractSummationInterface {
    @Override
    public AbstractNumberInterface add(AbstractNumberInterface a, AbstractNumberInterface b) {
        ConcreteIntegerNumber n = new ConcreteIntegerNumber(a.getInteger() + b.getInteger());
        return n;
    }
}

class ConcreteIntegerSubtraction implements AbstractSubtractionInterface {
    @Override
    public AbstractNumberInterface subtract(AbstractNumberInterface a, AbstractNumberInterface b) {
        ConcreteIntegerNumber n = new ConcreteIntegerNumber(a.getInteger() - b.getInteger());
        return n;
    }
}

class ConcreteIntegerMultiplication implements AbstractMultiplicationInterface {
    @Override
    public AbstractNumberInterface multiply(AbstractNumberInterface a, AbstractNumberInterface b) {
        ConcreteIntegerNumber n = new ConcreteIntegerNumber(a.getInteger() * b.getInteger());
        return n;
    }
}

class ConcreteIntegerDivision implements AbstractDivisionInterface {
    @Override
    public AbstractNumberInterface divide(AbstractNumberInterface a, AbstractNumberInterface b) throws Exception {
        if (b.getInteger() == 0) {
            throw new Exception("Error - divison by zero");
        } else {
            ConcreteIntegerNumber n = new ConcreteIntegerNumber(a.getInteger() / b.getInteger());
            return n;
        }
    }
}

class ConcreteIntegerCalculator implements AbstractCalculatorInterface {
    @Override
    public AbstractNumberInterface addNumbers(AbstractNumberInterface a, AbstractNumberInterface b) {
        ConcreteIntegerSummation integer_summation = new ConcreteIntegerSummation();
        return integer_summation.add(a, b);
    }

    @Override
    public AbstractNumberInterface substractNumbers(AbstractNumberInterface a, AbstractNumberInterface b) {
        ConcreteIntegerSubtraction integer_subtraction = new ConcreteIntegerSubtraction();
        return integer_subtraction.subtract(a, b);
    }

    @Override
    public AbstractNumberInterface multiplyNumbers(AbstractNumberInterface a, AbstractNumberInterface b) {
        ConcreteIntegerMultiplication integer_multiplication = new ConcreteIntegerMultiplication();
        return integer_multiplication.multiply(a, b);
    }

    @Override
    public AbstractNumberInterface divideNumbers(AbstractNumberInterface a, AbstractNumberInterface b) {
        ConcreteIntegerDivision integer_division = new ConcreteIntegerDivision();
        try {
            return integer_division.divide(a, b);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class ConcreteCalculatorFactory implements AbstractCalculatorFactoryInterface {
    public AbstractCalculatorInterface buildCalculator() {
        ConcreteIntegerCalculator concrete_calculator = new ConcreteIntegerCalculator();
        return concrete_calculator;
    }
}

class Main {
    public static void main(String[] args) {
        AbstractCalculatorFactoryInterface factory = new ConcreteCalculatorFactory();
        AbstractCalculatorInterface calculator = factory.buildCalculator();
        AbstractNumberInterface a = new ConcreteIntegerNumber(4);
        AbstractNumberInterface b = new ConcreteIntegerNumber(0);

        System.out.println("a + b = " + calculator.addNumbers(a, b).getInteger());
        System.out.println("a - b = " + calculator.substractNumbers(a, b).getInteger());
        System.out.println("a * b = " + calculator.multiplyNumbers(a, b).getInteger());
        System.out.println("a / b = " + calculator.divideNumbers(a, b).getInteger());
    }
}
